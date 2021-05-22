package ru.dosport.main.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dosport.main.dto.RawUserRequest;
import ru.dosport.main.dto.UserDto;
import ru.dosport.main.dto.UserRequest;
import ru.dosport.main.entities.Authority;
import ru.dosport.main.entities.User;
import ru.dosport.main.exceptions.DataBadRequestException;
import ru.dosport.main.exceptions.DataNotFoundException;
import ru.dosport.main.mappers.UserMapper;
import ru.dosport.main.repositories.AuthorityRepository;
import ru.dosport.main.repositories.UserRepository;
import ru.dosport.main.security.JwtUser;
import ru.dosport.main.services.api.UserService;

import java.util.List;
import java.util.UUID;

import static ru.dosport.main.helpers.InformationMessages.PASSWORDS_MISMATCH;
import static ru.dosport.main.helpers.Messages.*;
import static ru.dosport.main.helpers.Roles.ROLE_USER;

/**
 * Сервис пользователей
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    // Необходимые сервисы и мапперы
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // Необходимые репозитории
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDto getDtoById(Long id) {
        return userMapper.mapEntityToDto(findById(id));
    }

    @Override
    public User getById(Long id) {
        return findById(id);
    }

    @Override
    public UserDto getDtoByAuthentication(Authentication authentication) {
        return userMapper.mapEntityToDto(findById(getUserId(authentication)));
    }

    @Override
    public JwtUser getJwtByUsername(String username) {
        return userMapper.mapEntityToJwt(findByUsername(username));
    }

    @Override
    public Long getIdByAuthentication(Authentication authentication) {
        return getUserId(authentication);
    }

    @Override
    public List<UserDto> getAllDto() {
        return userMapper.mapEntityToDto(userRepository.findAll());
    }

    @Override
    public UserDto save(UserRequest userRequest) {
        String username = userRequest.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DataBadRequestException(String.format(USER_ALREADY_EXIST, username));
        }

        User newUser = userMapper.mapDtoToEntity(userRequest);
        for (String authority : userRequest.getAuthorities()) {
            newUser.getAuthorities().add(authorityRepository.findByAuthority(authority));
        }
        return userMapper.mapEntityToDto(userRepository.save(newUser));
    }

    @Override
    public UserDto save(RawUserRequest userRequest) {
        if (!userRequest.getPassword().equals(userRequest.getPasswordConfirm())) {
            throw new DataBadRequestException(PASSWORDS_MISMATCH);
        }
        String username = userRequest.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DataBadRequestException(String.format(USER_ALREADY_EXIST, username));
        }

        User newUser = userMapper.mapDtoToEntity(userRequest);
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userMapper.mapEntityToDto(userRepository.save(newUser));
    }

    @Override
    public UserDto update(UserDto userDto, Authentication authentication) {
        User user = userMapper.update(findById(getUserId(authentication)), userDto);
        return userMapper.mapEntityToDto(userRepository.save(user));
    }

    @Override
    public boolean deleteById(Long id) {
        userRepository.deleteById(id);
        return userRepository.existsById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.mapEntityToJwt(findByUsername(username));
    }

    /**
     * Найти пользователя по id
     */
    private User findById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(String.format(USER_NOT_FOUND_BY_ID, id)));
    }

    /**
     * Найти пользователя по username
     */
    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_BY_USERNAME, username)));
    }

    /**
     * Получить id пользователя по данным аутентификации
     */
    private Long getUserId(Authentication authentication) {
        return ((JwtUser) authentication.getPrincipal()).getId();
    }
}

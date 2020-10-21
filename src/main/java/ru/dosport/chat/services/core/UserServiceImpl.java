package ru.dosport.chat.services.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dosport.chat.dto.UserDto;
import ru.dosport.chat.dto.UserRequest;
import ru.dosport.chat.entities.Authority;
import ru.dosport.chat.entities.User;
import ru.dosport.chat.exceptions.DataBadRequestException;
import ru.dosport.chat.exceptions.DataNotFoundException;
import ru.dosport.chat.mappers.UserMapper;
import ru.dosport.chat.repositories.AuthorityRepository;
import ru.dosport.chat.repositories.UserRepository;
import ru.dosport.chat.security.JwtUser;
import ru.dosport.chat.services.api.UserService;

import java.util.List;

import static ru.dosport.chat.helpers.Messages.*;
import static ru.dosport.chat.helpers.Roles.ROLE_USER;

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
    public User getByUsername(String username) {
        return findByUsername(username);
    }

    @Override
    public UserDto getDtoByUsername(String username) {
        return userMapper.mapEntityToDto(findByUsername(username));
    }

    @Override
    public JwtUser getJwtByUsername(String username) {
        return userMapper.mapEntityToJwt(findByUsername(username));
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
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Authority authority = authorityRepository.findByAuthority(ROLE_USER);
        newUser.getAuthorities().add(authority);
        return userMapper.mapEntityToDto(userRepository.save(newUser));
    }

    @Override
    public UserDto update(UserDto userDto, String username) {
        User user = userMapper.update(findByUsername(username), userDto);
        return userMapper.mapEntityToDto(userRepository.save(user));
    }

    @Override
    public UserDto update(UserDto userDto, Long id) {
        User user = userMapper.update(findById(id), userDto);
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
}

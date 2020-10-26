package ru.dosport.chat.services.api;

import org.springframework.security.core.Authentication;
import ru.dosport.chat.dto.UserDto;
import ru.dosport.chat.dto.UserRequest;
import ru.dosport.chat.security.JwtUser;

import java.util.List;

/**
 * Сервис пользователей
 */
public interface UserService {

    /*
     * СОГЛАШЕНИЕ О НАИМЕНОВАНИИ МЕТОДОВ СЕРВИСОВ
     * User getById(Long id) найти объект по параметру
     * UserDto getDtoById(Long id) найти Dto объект по параметру
     * List<User> getAll() найти все объекты
     * List<UserDto> getAllDto() найти все Dto объекты
     * List<UserDto> getAllDtoByObject(ObjectDto objectDto) найти все Dto объекты по параметру
     * UserDto update(UserDto userDto) изменить объект
     * UserDto save(UserDto userDto) сохранить объект
     * List<UserDto> saveAll(List<UserDto> userDtoList) сохранить список объектов
     * void delete(UserDto userDto) удалить конкретный объект
     * void deleteById(Long id) удалить объект по параметру
     * void deleteAll(List<UserDto> userDtoList) удалить список объектов
     */

    /**
     * Найти пользователя по его идентификатору
     *
     * @param id идентификатор пользователя
     * @return пользователь
     */
    UserDto getDtoById(Long id);

    /**
     * Найти пользователя по данным авторизации
     *
     * @param authentication данные авторизации
     * @return пользователь
     */
    UserDto getDtoByAuthentication(Authentication authentication);

    /**
     * Найти Jwt пользователя
     *
     * @param username логин пользователя
     * @return Jwt Пользователь
     */
    JwtUser getJwtByUsername(String username);

    /**
     * Получить id пользователя по авторизации
     *
     * @param authentication данные авторизации
     * @return id Пользователя
     */
    Long getIdByAuthentication(Authentication authentication);

    /**
     * Найти всех пользователей
     *
     * @return список пользователей
     */
    List<UserDto> getAllDto();

    /**
     * Создать нового пользователя
     *
     * @param userRequest запрос с данными пользователя
     * @return новый пользователь, сохраненный в репозитории
     */
    UserDto save(UserRequest userRequest);

    /**
     * Изменить данные пользователя
     *
     * @param userDto пользователь с измененными данными
     * @param authentication данные авторизации
     */
    UserDto update(UserDto userDto, Authentication authentication);

    /**
     * Удалить пользователя по его идентификатору
     *
     * @param id идентификатор пользователя
     * @return удален ли пользователь
     */
    boolean deleteById(Long id);
}

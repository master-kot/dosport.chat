package ru.dosport.chat.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dosport.chat.dto.UserDto;
import ru.dosport.chat.services.api.UserService;

import static ru.dosport.chat.helpers.Roles.ROLE_ADMIN;
import static ru.dosport.chat.helpers.Roles.ROLE_USER;

/**
 * Контроллер Пользователя.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class UserController {

    // Тип данных
    private final String DATA_TYPE = "application/json";

    // Необходимые сервисы
    private final UserService userService;

    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @ApiOperation(value = "Выводит данные пользователя")
    @GetMapping(value = "", produces = DATA_TYPE)
    public ResponseEntity<UserDto> readUser(Authentication authentication) {
        return new ResponseEntity<>(userService.getDtoByAuthentication(authentication), HttpStatus.OK);
    }
}

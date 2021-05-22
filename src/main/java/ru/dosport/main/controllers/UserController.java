package ru.dosport.main.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.dosport.main.dto.ErrorDto;
import ru.dosport.main.dto.RawUserRequest;
import ru.dosport.main.dto.UserDto;
import ru.dosport.main.dto.UserRequest;
import ru.dosport.main.services.api.UserService;

import javax.validation.Valid;
import java.util.stream.Collectors;

import static ru.dosport.main.helpers.InformationMessages.BAD_REQUEST;
import static ru.dosport.main.helpers.InformationMessages.SUCCESSFUL_REQUEST;
import static ru.dosport.main.helpers.Roles.ROLE_ADMIN;
import static ru.dosport.main.helpers.Roles.ROLE_USER;

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

    @PostMapping(value = "/register")
    @ApiOperation(value = "Создает новый профиль пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = SUCCESSFUL_REQUEST),
            @ApiResponse(code = 400, message = BAD_REQUEST, response = ErrorDto.class)
    })
    public ResponseEntity<UserDto> createUser(
            @ApiParam("Запрос для регистрации нового Пользователя") @Valid @RequestBody RawUserRequest userRequest
    ) {
        return ResponseEntity.ok(userService.save(userRequest));
    }
}

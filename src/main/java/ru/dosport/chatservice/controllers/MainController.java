package ru.dosport.chatservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Главный контроллер. Обеспечивает работоспособность фронтенд части web-приложения
 */
@Controller
@RequestMapping("")
@Api(tags = {"Контроллер главной страницы"})
@RequiredArgsConstructor
public class MainController {

    /**
     * Перехват запроса главной страницы
     */
    @GetMapping("")
    @ApiOperation(value = "Отображает главную страницу")
    public String getHomePage() {
        return "index";
    }
}

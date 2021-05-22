package ru.dosport.chatservice.controllers;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dosport.chatservice.dtos.MessageRequest;
import ru.dosport.chatservice.dtos.MessageResponse;
import ru.dosport.chatservice.services.api.EventMessageService;
import ru.dosport.chatservice.services.api.UserMessageService;
import ru.dosport.main.helpers.InformationMessages;

import javax.validation.Valid;
import java.util.List;

import static ru.dosport.main.helpers.Roles.ROLE_ADMIN;
import static ru.dosport.main.helpers.Roles.ROLE_USER;
import static ru.dosport.main.helpers.SwaggerMessages.*;

@CrossOrigin
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(tags = {"Контроллер Сообщений чатов"})
public class ChatController {

    private final String PATH = "/api/v1";

    private final SimpMessagingTemplate messagingTemplate;
    private final UserMessageService userMessageService;
    private final EventMessageService eventMessageService;

    @MessageMapping("/user")
    public void processUserMessage(@Payload MessageRequest request,
                                   Authentication authentication
    ) {
        MessageResponse savedMessage = userMessageService.save(request.getRecipientId(), request, authentication);
        messagingTemplate.
                convertAndSendToUser(request.getRecipientId().toString(), "/queue/messages", savedMessage);
    }

    @MessageMapping("/event")
    public void processEventMessage(@Payload MessageRequest request,
                                    Authentication authentication
    ) {
        MessageResponse savedMessage = eventMessageService.save(request.getRecipientId(), request, authentication);
        messagingTemplate.convertAndSend("/event", savedMessage);
    }

//    @MessageExceptionHandler
//    @SendToUser("/queue/messages")
//    public String handleException(Exception exception) {
//        return exception.getMessage();
//    }

    /*
     * Методы, работающие с сообщениями пользователей
     */

    @ApiOperation(value = "Отображает все сообщения для получателя по данным авторизации")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = InformationMessages.SUCCESSFUL_REQUEST)
    })
    @GetMapping("/messages")
    public ResponseEntity<?> getAllMessagesByAuthentication(Authentication authentication) {
        return ResponseEntity.ok(userMessageService.getAllByAuthentication(authentication));
    }

    @ApiOperation(value = "Отображает сообщение с заданным идентификатором сообщения")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = InformationMessages.SUCCESSFUL_REQUEST)
    })
    @GetMapping("/messages/{id}")
    public ResponseEntity<?> getMessageById(@ApiParam(value = "Id сообщения") @PathVariable Long id) {
        return ResponseEntity.ok(userMessageService.getDtoById(id));
    }

    /*
     * Методы, работающие с сообщениями мероприятий
     */

    @ApiOperation(value = "Отображает список сообщений мероприятия")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @GetMapping("/events/{eventId}/messages")
    public ResponseEntity<List<MessageResponse>> readMessages(
            @ApiParam(value = PAR_EVENT_ID) @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(eventMessageService.getAllDtoByEventId(eventId));
    }

    @ApiOperation(value = "Добавляет сообщение мероприятия")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PostMapping("/events/{eventId}/messages")
    public ResponseEntity<MessageResponse> createMessage(
            @ApiParam(value = PAR_EVENT_ID) @PathVariable Long eventId,
            @ApiParam(value = PAR_MESSAGE_DTO) @Valid @RequestBody MessageRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(eventMessageService.save(eventId, request, authentication));
    }

    @ApiOperation(value = "Обновляет данные сообщения")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @PutMapping("/events/{eventId}/messages/{messageId}")
    public ResponseEntity<MessageResponse> updateMessage(
            @ApiParam(value = PAR_EVENT_ID) @PathVariable Long eventId,
            @ApiParam(value = PAR_MESSAGE_ID) @PathVariable Long messageId,
            @ApiParam(value = PAR_MESSAGE_DTO) @Valid @RequestBody MessageRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(eventMessageService.update(eventId, messageId, request, authentication));
    }

    @ApiOperation(value = "Удаляет сообщение")
    @Secured(value = {ROLE_USER, ROLE_ADMIN})
    @DeleteMapping("/events/{eventId}/messages/{messageId}")
    public ResponseEntity<MessageResponse> deleteMessage(
            @ApiParam(value = PAR_EVENT_ID) @PathVariable Long eventId,
            @ApiParam(value = PAR_MESSAGE_ID) @PathVariable Long messageId,
            Authentication authentication
    ) {
        return eventMessageService.deleteById(eventId, messageId, authentication) ?
                ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}

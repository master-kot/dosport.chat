package ru.dosport.chatservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static ru.dosport.main.helpers.InformationMessages.DATA_NOT_BLANK;
import static ru.dosport.main.helpers.SwaggerMessages.*;

/**
 * Запрос для создания нового Сообщения
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Запрос для создания нового Сообщения")
public class MessageRequest {

   @NotNull(message = DATA_NOT_BLANK + PAR_MESSAGE_SENDER)
   @ApiModelProperty(notes = PAR_MESSAGE_SENDER,
           dataType = "Long", required = true, position = 0)
   private Long senderId;

   @NotNull(message = DATA_NOT_BLANK + PAR_MESSAGE_RECIPIENT)
   @ApiModelProperty(notes = PAR_MESSAGE_RECIPIENT,
           dataType = "Long", required = true, position = 0)
   private Long recipientId;

   @Size(min = 1, max = 500)
   @NotBlank(message = DATA_NOT_BLANK + PAR_MESSAGE_TEXT)
   @ApiModelProperty(notes = PAR_MESSAGE_TEXT + "от 1 до 500 символов",
           dataType = "String", required = true, position = 0)
   private String content;
}

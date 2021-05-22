package ru.dosport.chatservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import static ru.dosport.main.helpers.Patterns.*;
import static ru.dosport.main.helpers.SwaggerMessages.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Сообщение чата")
public class MessageResponse {

    @ApiModelProperty(notes = PAR_MESSAGE_ID,
            dataType = "Long", example = "1", required = true, position = 0)
    private Long messageId;

    @ApiModelProperty(notes = PAR_MESSAGE_SENDER,
            dataType = "Long", example = "1", required = true, position = 1)
    private Long senderId;

    @ApiModelProperty(notes = PAR_USERNAME,
            dataType = "String", example = "Nickname", required = true, position = 2)
    private String username;

    @ApiModelProperty(notes = PAR_USER_PHOTO,
            dataType = "String", example = "myphoto.png", position = 3)
    private String photoLink;

    @ApiModelProperty(notes = PAR_MESSAGE_RECIPIENT,
            dataType = "Long", example = "1", required = true, position = 4)
    private Long recipientId;

    @ApiModelProperty(notes = PAR_MESSAGE_TEXT,
            dataType = "String", required = true, position = 5)
    private String content;

    @DateTimeFormat(pattern=LOCAL_DATE_TIME_PATTERN)
    @ApiModelProperty(notes = PAR_CREATION_DATE_FORMAT,
            dataType = LOCAL_DATE_TIME_TYPE, example = LOCAL_DATE_EXAMPLE_1, required = true, position = 6)
    private String creationDateTime;
}

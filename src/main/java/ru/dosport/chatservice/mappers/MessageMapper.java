package ru.dosport.chatservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import ru.dosport.chatservice.dtos.MessageResponse;
import ru.dosport.chatservice.entities.EventMessage;
import ru.dosport.chatservice.entities.UserMessage;
import ru.dosport.main.mappers.UserMapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserMapper.class})
public interface MessageMapper {

    @Mappings({
            @Mapping(target = "messageId", source = "entity.id"),
            @Mapping(target = "senderId", source = "entity.sender.id"),
            @Mapping(target = "username", source = "entity.sender.username"),
            @Mapping(target = "photoLink", source = "entity.sender.photoLink"),
            @Mapping(target = "content", source = "entity.content"),
            @Mapping(target = "recipientId", source = "entity.eventId")
    })
    MessageResponse mapEntityToDto(EventMessage entity);

    List<MessageResponse> mapEventMessageToDto(List<EventMessage> entities);

    @Mappings({
            @Mapping(target = "messageId", source = "entity.id"),
            @Mapping(target = "senderId", source = "entity.sender.id"),
            @Mapping(target = "username", source = "entity.sender.username"),
            @Mapping(target = "photoLink", source = "entity.sender.photoLink"),
            @Mapping(target = "content", source = "entity.content"),
            @Mapping(target = "recipientId", source = "entity.recipientId")
    })
    MessageResponse mapEntityToDto(UserMessage entity);

    List<MessageResponse> mapUserMessageToDto(List<UserMessage> entities);
}

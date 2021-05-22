package ru.dosport.chatservice.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

/**
 * Конфигурация WebSocket.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    /**
     * Конфигурирует простой ин-мемори брокер сообщений с соответствующими префиксами места назначения.
     * Префикс /app используется для входящих сообщений, обрабатывающихся методами, аннотированными @MessageMapping.
     * UserDestinationPrefix используется методом ConvertAndSendToUser.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.setApplicationDestinationPrefixes("/app");
        config.enableSimpleBroker( "/user", "/event");
        config.setUserDestinationPrefix("/user");
    }

    /**
     * Регистрирует точку доступа с префиксом /ws, используемую для
     * подключения клиентов к STOMP серверу и разрешает использование SockJS.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
//                .setHandshakeHandler(new CustomHandshakeHandler())
                .withSockJS();
    }

    /**
     * Настраивает конвертер сообщений JSON, используемый для преобразования сообщений чата из / в JSON.
     */
    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

//    @Bean
//    public UserDestinationMessageHandler userDestinationMessageHandler() {
//        UserDestinationMessageHandler handler =
//                new UserDestinationMessageHandler(clientInboundChannel(), brokerChannel(), userDestinationResolver());
//        String destination = getBrokerRegistry().getUserDestinationBroadcast();
//        handler.setBroadcastDestination(destination);
//        return handler;
//    }
}

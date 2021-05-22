package ru.dosport.chatservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dosport.chatservice.enums.MessageStatus;
import ru.dosport.main.entities.User;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Сущность Сообщение пользователя
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_messages")
public class UserMessage {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Long id;

   @ManyToOne
   @JoinColumn(name = "sender_id", nullable = false)
   private User sender;

   @Column(name = "recipient_id")
   private Long recipientId;

   @Column(name = "content", length = 500)
   private String content;

   @Column(name = "creation_date")
   private LocalDateTime creationDate;

   @Column(name = "status")
   @Enumerated(EnumType.ORDINAL)
   private MessageStatus status;
}

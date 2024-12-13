package com.sparta.slack.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Slack {

    @Id
    @GeneratedValue
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "recipient_id", nullable = false, unique = true)
    private UUID recipientId;

    @Column(name = "message_content", nullable = false, columnDefinition = "TEXT")
    private String messageContent;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    private Slack(UUID recipientId, String messageContent) {
        this.recipientId = recipientId;
        this.messageContent = messageContent;
        this.sentAt = LocalDateTime.now();
    }

    public static Slack create(UUID recipientId, String messageContent) {
        return new Slack(recipientId, messageContent);
    }
}

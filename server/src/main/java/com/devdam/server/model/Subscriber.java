package com.devdam.server.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.devdam.server.enums.SubscriberType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table(name = "subscribers")
public class Subscriber {
    @Id
    private Long id;

    private String email;

    @Builder.Default
    private SubscriberType type = SubscriberType.NEWSLETTER;

    private Boolean isInformed;

    private String unsubscribeCode;

    @DateTimeFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime dateSubscribed;
}

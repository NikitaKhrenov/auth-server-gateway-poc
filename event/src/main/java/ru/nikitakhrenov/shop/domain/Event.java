package ru.nikitakhrenov.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
public class Event {

    @Id
    private String id;
    private String title;
    private String location;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String description;
    private boolean viewPublic;
}

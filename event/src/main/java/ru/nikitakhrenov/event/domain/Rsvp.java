package ru.nikitakhrenov.event.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Rsvp {

    @Id
    private String id;
    private String userId;
    private String eventId;
    private boolean attending;
    private int guests;
    private String comments;

}

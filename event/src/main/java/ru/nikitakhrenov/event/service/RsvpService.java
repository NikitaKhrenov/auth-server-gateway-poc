package ru.nikitakhrenov.event.service;

import ru.nikitakhrenov.event.domain.Rsvp;

import java.util.List;

public interface RsvpService {

    List<Rsvp> findByEventId(String eventId);
}

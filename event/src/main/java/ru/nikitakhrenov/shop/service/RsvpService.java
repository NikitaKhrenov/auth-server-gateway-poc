package ru.nikitakhrenov.shop.service;

import ru.nikitakhrenov.shop.domain.Rsvp;

import java.util.List;

public interface RsvpService {

    List<Rsvp> findByEventId(String eventId);
}

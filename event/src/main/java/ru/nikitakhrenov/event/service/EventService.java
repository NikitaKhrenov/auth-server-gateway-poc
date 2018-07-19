package ru.nikitakhrenov.event.service;

import ru.nikitakhrenov.event.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> findPublicInFuture();

    List<Event> find();

    Event findById(String id);
}

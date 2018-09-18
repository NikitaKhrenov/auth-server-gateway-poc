package ru.nikitakhrenov.shop.service;

import ru.nikitakhrenov.shop.domain.Event;

import java.util.List;

public interface EventService {

    List<Event> findPublicInFuture();

    List<Event> find();

    Event findById(String id);
}

package ru.nikitakhrenov.event.repository.custom;

import ru.nikitakhrenov.event.domain.Event;

import java.util.Optional;

public interface CustomEventRepository {

    Optional<Event> findOneCustom(String id);
}

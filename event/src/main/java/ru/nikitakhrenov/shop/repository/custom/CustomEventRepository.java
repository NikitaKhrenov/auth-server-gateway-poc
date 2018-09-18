package ru.nikitakhrenov.shop.repository.custom;

import ru.nikitakhrenov.shop.domain.Event;

import java.util.Optional;

public interface CustomEventRepository {

    Optional<Event> findOneCustom(String id);
}

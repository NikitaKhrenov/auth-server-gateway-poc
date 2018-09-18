package ru.nikitakhrenov.shop.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.nikitakhrenov.shop.domain.Event;

import java.util.Optional;

public class CustomEventRepositoryImpl implements CustomEventRepository {

    @Autowired
    private MongoOperations operations;

    @Override
    public Optional<Event> findOneCustom(String id) {
        return Optional.empty();
    }
}

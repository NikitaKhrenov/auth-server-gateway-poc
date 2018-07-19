package ru.nikitakhrenov.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitakhrenov.event.domain.Event;
import ru.nikitakhrenov.event.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository repository;

    @Override
    public List<Event> findPublicInFuture() {
        return repository.findPublicInFuture(LocalDateTime.now());
    }

    @Override
    public List<Event> find() {
        return repository.findAll();
    }

    @Override
    public Event findById(String id) {
        return repository.findOne(id);
    }
}

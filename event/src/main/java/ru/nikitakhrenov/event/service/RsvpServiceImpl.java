package ru.nikitakhrenov.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitakhrenov.event.domain.Rsvp;
import ru.nikitakhrenov.event.repository.RsvpRepository;

import java.util.List;

@Service
public class RsvpServiceImpl implements RsvpService {

    @Autowired
    private RsvpRepository repository;

    @Override
    public List<Rsvp> findByEventId(String eventId) {
        return repository.findByEventId(eventId);
    }
}

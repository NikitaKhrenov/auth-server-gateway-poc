package ru.nikitakhrenov.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitakhrenov.event.domain.Rsvp;
import ru.nikitakhrenov.event.service.RsvpService;

import java.util.List;

@RestController
public class RsvpController {

    @Autowired
    private RsvpService service;

    @RequestMapping(value = "/{eventId}/rsvps")
    public List<Rsvp> findByEventId(@PathVariable String eventId) {
        return service.findByEventId(eventId);
    }

}

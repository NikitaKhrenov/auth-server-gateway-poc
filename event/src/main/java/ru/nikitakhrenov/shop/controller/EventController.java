package ru.nikitakhrenov.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitakhrenov.shop.domain.Event;
import ru.nikitakhrenov.shop.service.EventService;

import java.util.List;

@RestController
public class EventController {

    @Autowired
    private EventService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Event> findPublicInFuture() {
        return service.findPublicInFuture();
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    //@PreAuthorize("")
    public List<Event> find() {
        return service.find();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Event findById(@PathVariable String id) {
        return service.findById(id);
    }
}

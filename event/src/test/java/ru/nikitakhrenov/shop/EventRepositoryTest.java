package ru.nikitakhrenov.shop;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.nikitakhrenov.shop.domain.Event;
import ru.nikitakhrenov.shop.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventRepositoryTest {

    @Autowired
    EventRepository repository;

    @Test
    public void findPublicInFuture() {
        List<Event> events = repository.findPublicInFuture(LocalDateTime.now());
        Assert.assertEquals(1, events.size());
    }

    @Test
    public void findPublic() {
        List<Event> events = repository.findAll();
        Assert.assertEquals(3, events.size());
    }

}

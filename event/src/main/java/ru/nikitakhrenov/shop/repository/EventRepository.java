package ru.nikitakhrenov.shop.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nikitakhrenov.shop.domain.Event;
import ru.nikitakhrenov.shop.repository.custom.CustomEventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event, String>, CustomEventRepository {

    String EVENT_LIST_PROJECTION = "{title : 1, startDateTime: 1, endDateTime: 1, viewPublic: 1, _id: 0}";

    @Query(value = "{viewPublic: true, startDateTime: { $gte: ?0 }}", fields = EVENT_LIST_PROJECTION)
    List<Event> findPublicInFuture(LocalDateTime now);

    //@Query(fields = EVENT_LIST_PROJECTION)
    //List<Event> findAll();

    /*Event findOne(String id);*/
}

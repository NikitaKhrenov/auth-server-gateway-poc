package ru.nikitakhrenov.shop.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nikitakhrenov.shop.domain.Rsvp;

import java.util.List;

@Repository
public interface RsvpRepository extends CrudRepository<Rsvp, String> {

    @Query(value = "{eventId: ?0}")
    List<Rsvp> findByEventId(String eventId);
}

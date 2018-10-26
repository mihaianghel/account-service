package digital.and.accountservice.service;

import digital.and.accountservice.model.domain.Event;

import java.time.LocalDateTime;
import java.util.Collection;

public interface EventService {

    Collection<Event> getEvents(Long id, LocalDateTime dateTime);

    Long saveEvent(Event event);
}

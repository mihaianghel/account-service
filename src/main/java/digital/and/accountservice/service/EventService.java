package digital.and.accountservice.service;

import digital.and.accountservice.model.domain.Event;

import java.util.Collection;

public interface EventService {

    Collection<Event> getEvents(Long id);

    Long saveEvent(Event event);
}

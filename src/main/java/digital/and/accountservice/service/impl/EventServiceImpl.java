package digital.and.accountservice.service.impl;

import digital.and.accountservice.model.data.EventDao;
import digital.and.accountservice.model.domain.Event;
import digital.and.accountservice.repository.EventRepository;
import digital.and.accountservice.service.EventService;
import digital.and.accountservice.util.EventType;
import digital.and.accountservice.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final Random RANDOM = new Random();

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private Mapper mapper;


    @Override
    public Collection<Event> getEvents(Long id) {
        return eventRepository.findByAccountId(id)
                .stream()
                .map(e -> mapper.transformEventDaoToDomainEvent(e))
                .collect(Collectors.toList());
    }

    @Override
    public Long saveEvent(Event event) {

        if (EventType.BANK_ACCOUNT_CREATED.equals(event.getType())) {
            Long accountId = Long.valueOf(RANDOM.nextInt(100000000) + 1);
            event.setAccountId(accountId);
            event.setAmount(0);
        }

        EventDao eventDao = mapper.transformDomainEventToEventDao(event);
        EventDao savedEvent = eventRepository.save(eventDao);

        return savedEvent.getAccountId();
    }
}

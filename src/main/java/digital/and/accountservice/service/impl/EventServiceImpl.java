package digital.and.accountservice.service.impl;

import digital.and.accountservice.exception.GenericException;
import digital.and.accountservice.model.data.EventDao;
import digital.and.accountservice.model.domain.Account;
import digital.and.accountservice.model.domain.Event;
import digital.and.accountservice.repository.EventRepository;
import digital.and.accountservice.service.AccountService;
import digital.and.accountservice.service.EventService;
import digital.and.accountservice.util.EventType;
import digital.and.accountservice.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private static final Random RANDOM = new Random();

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    @Override
    public Collection<Event> getEvents(Long id, LocalDateTime dateTime) {
        return eventRepository.findByAccountIdAndAllWithCreationDateTimeBefore(id, dateTime)
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

        validateEvent(event);

        EventDao eventDao = mapper.transformDomainEventToEventDao(event);
        EventDao savedEvent = eventRepository.save(eventDao);

        return savedEvent.getAccountId();
    }

    private void validateEvent(Event event) {
        if (EventType.BANK_ACCOUNT_CREATED.equals(event.getType())) {

            validateOwner(event);

        } else {

            validateBankAccountIsOpen(event);

            Account account = accountService.getAccountState(event.getAccountId(), LocalDateTime.now());

            switch (event.getType()) {
                case DEPOSIT_PERFORMED:
                    validateAmount(event); break;
                case OWNER_CHANGED:
                    validateOwner(event); break;
                case WITHDRAWAL_PERFORMED:
                    validateAmountForWithdrawal(event, account); break;
                case BANK_ACCOUNT_CLOSED:
                    validateNotes(event);
            }
        }
    }

    private void validateBankAccountIsOpen(Event event) {
        Collection<EventDao> events = eventRepository.findOpenAndClosedEventsForAccountId(event.getAccountId());

        List<EventDao> openAccountEvents = new ArrayList<>();
        List<EventDao>  closedAccountEvents = new ArrayList<>();

        events.stream()
                .forEach(eventDao -> {
                    if (EventType.BANK_ACCOUNT_CLOSED.equals(eventDao.getType())) {
                        closedAccountEvents.add(eventDao);
                    } else {
                        openAccountEvents.add(eventDao);
                    }
                });

        if (!closedAccountEvents.isEmpty()) {
            throw new GenericException("Error. Account is closed !");
        }
        if (openAccountEvents.size() != 1) {
            throw new GenericException("Error. Account is not open !");
        }
    }

    private void validateOwner(Event event) {
        if (event.getOwner() == null || event.getOwner().isEmpty()) {
            throw new GenericException("Invalid owner name");
        }
    }

    private void validateAmount(Event event) {
        if (event.getAmount() == 0) {
            throw new GenericException("Invalid amount for deposit");
        }
    }

    private void validateAmountForWithdrawal(Event event, Account account) {
        if (event.getAmount() == 0 || event.getAmount() > account.getBalance()) {
            throw new GenericException("Invalid amount for withdrawal");
        }
    }

    private void validateNotes(Event event) {
        if (event.getNotes() == null || event.getNotes().isEmpty()) {
            throw new GenericException("Invalid reason");
        }
    }
}

package digital.and.accountservice.util;

import digital.and.accountservice.event.AbstractEvent;
import digital.and.accountservice.model.data.EventDao;
import digital.and.accountservice.model.domain.Account;
import digital.and.accountservice.model.domain.Event;
import digital.and.accountservice.model.transfer.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Event transformEventToDomainEvent(AbstractEvent event) {

        Event domainEvent = new Event();

        domainEvent.setAccountId(event.getAccountId());
        domainEvent.setOwner(event.getOwner());
        domainEvent.setEventDate(event.getEventDate());
        domainEvent.setType(event.getType());
        domainEvent.setAmount(event.getAmount());
        domainEvent.setNotes(event.getReason());

        return domainEvent;
    }


    public EventDao transformDomainEventToEventDao(Event event) {

        EventDao eventDao = new EventDao();

        eventDao.setAccountId(event.getAccountId());
        eventDao.setAmount(event.getAmount());
        eventDao.setOwner(event.getOwner());
        eventDao.setType(event.getType());
        eventDao.setEventDate(event.getEventDate());
        eventDao.setNotes(event.getNotes());

        return eventDao;
    }

    public Event transformEventDaoToDomainEvent(EventDao eventDao) {

        Event event = new Event();

        event.setAccountId(eventDao.getAccountId());
        event.setAmount(eventDao.getAmount());
        event.setOwner(eventDao.getOwner());
        event.setType(eventDao.getType());
        event.setEventDate(eventDao.getEventDate());
        event.setNotes(eventDao.getNotes());

        return event;
    }

    public AccountDto transformAccountToAccountDto(Account account) {

        AccountDto accountDto = new AccountDto();

        accountDto.setAccountId(account.getAccountId());
        accountDto.setOwner(account.getOwner());
        accountDto.setBalance(account.getBalance());
        accountDto.setStatus(account.getStatus());

        return accountDto;
    }
}

package digital.and.accountservice.controller;

import digital.and.accountservice.event.AbstractEvent;
import digital.and.accountservice.model.domain.Account;
import digital.and.accountservice.model.domain.Event;
import digital.and.accountservice.model.transfer.AccountDto;
import digital.and.accountservice.service.AccountService;
import digital.and.accountservice.service.EventService;
import digital.and.accountservice.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private Mapper mapper;

    @GetMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountDto getAccount(@PathVariable("id") Long id,
                                 @RequestParam(value = "t", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy-HH:mm:ss") LocalDateTime dt) {

        LocalDateTime dateTime = (dt == null) ? LocalDateTime.now() : dt;

        Account account = accountService.getAccountState(id, dateTime);

        return mapper.transformAccountToAccountDto(account);
    }

    @PostMapping(value = "/events", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveEvent(@RequestBody AbstractEvent event) {

        LOGGER.info(String.format("Received event [%s]", event));

        Event domainEvent = mapper.transformEventToDomainEvent(event);

        Long accountId = eventService.saveEvent(domainEvent);

        return "Event saved successfully for accountId: " + accountId;
    }
}

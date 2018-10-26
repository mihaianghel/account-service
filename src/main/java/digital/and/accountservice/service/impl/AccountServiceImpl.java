package digital.and.accountservice.service.impl;

import digital.and.accountservice.model.domain.Account;
import digital.and.accountservice.model.domain.Event;
import digital.and.accountservice.service.AccountService;
import digital.and.accountservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private EventService eventService;

    @Override
    public Account getAccountState(Long accountId, LocalDateTime dateTime) {

        Account account = Account.createAccount();

        Collection<Event> events = eventService.getEvents(accountId, dateTime);

        events.stream()
                .forEach(event -> {
                    switch(event.getType()) {
                        case BANK_ACCOUNT_CREATED:
                            account.setAccountId(event.getAccountId());
                            account.setOwner(event.getOwner());
                            return;
                        case OWNER_CHANGED:
                            account.setOwner(event.getOwner());
                            return;
                        case DEPOSIT_PERFORMED:
                            account.performDeposit(event.getAmount());
                            return;
                        case WITHDRAWAL_PERFORMED:
                            account.performWithdrawal(event.getAmount());
                            return;
                        case BANK_ACCOUNT_CLOSED:
                            account.closeAccount();
                            break;
                    }
                });

        return account;
    }
}

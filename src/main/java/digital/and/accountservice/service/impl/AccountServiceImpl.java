package digital.and.accountservice.service.impl;

import digital.and.accountservice.model.domain.Account;
import digital.and.accountservice.model.domain.Event;
import digital.and.accountservice.service.AccountService;
import digital.and.accountservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private EventService eventService;

    @Override
    public Account getAccountState(Long accountId) {

        Account account = Account.createAccount();

        Collection<Event> events = eventService.getEvents(accountId);

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
                        default: return;
                    }
                });

        return account;
    }
}

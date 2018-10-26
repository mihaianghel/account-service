package digital.and.accountservice.service;

import digital.and.accountservice.model.domain.Account;

import java.time.LocalDateTime;

public interface AccountService {

    Account getAccountState(Long accountId, LocalDateTime dateTime);
}

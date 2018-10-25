package digital.and.accountservice.service;

import digital.and.accountservice.model.domain.Account;

public interface AccountService {

    Account getAccountState(Long accountId);
}

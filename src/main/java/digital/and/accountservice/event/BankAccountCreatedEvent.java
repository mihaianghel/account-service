package digital.and.accountservice.event;

import digital.and.accountservice.util.EventType;

public class BankAccountCreatedEvent extends AbstractEvent {

    private EventType type = EventType.BANK_ACCOUNT_CREATED;
}

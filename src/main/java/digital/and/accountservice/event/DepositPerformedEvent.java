package digital.and.accountservice.event;

import digital.and.accountservice.util.EventType;

public class DepositPerformedEvent extends AbstractEvent {

    private EventType type = EventType.DEPOSIT_PERFORMED;
}

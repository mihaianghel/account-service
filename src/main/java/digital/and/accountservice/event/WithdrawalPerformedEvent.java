package digital.and.accountservice.event;

import digital.and.accountservice.util.EventType;

public class WithdrawalPerformedEvent extends AbstractEvent {

    private EventType type = EventType.WITHDRAWAL_PERFORMED;
}

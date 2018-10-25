package digital.and.accountservice.event;

import digital.and.accountservice.util.EventType;

public class OwnerChangedEvent extends AbstractEvent {

    private EventType type = EventType.OWNER_CHANGED;
}

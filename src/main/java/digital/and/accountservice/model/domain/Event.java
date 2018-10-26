package digital.and.accountservice.model.domain;

import digital.and.accountservice.util.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event {

    private EventType type;
    private Long accountId;
    private String owner;
    private int amount;
    private String notes;
    private LocalDateTime eventDate;
}

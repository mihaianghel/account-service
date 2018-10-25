package digital.and.accountservice.model.domain;

import digital.and.accountservice.util.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Event {

    private EventType type;
    private Long accountId;
    private String owner;
    private Integer amount;
    private Date eventDate;
}

package digital.and.accountservice.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import digital.and.accountservice.util.EventType;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Getter
@JsonInclude(value = Include.NON_NULL)
public class AbstractEvent {

    private EventType type;
    private Long accountId;
    private String owner;
    private int amount;
    private String reason;
    private LocalDateTime eventDate = LocalDateTime.now();
}

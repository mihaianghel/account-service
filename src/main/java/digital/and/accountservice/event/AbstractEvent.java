package digital.and.accountservice.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import digital.and.accountservice.util.EventType;
import lombok.Getter;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@Getter
@JsonInclude(value = Include.NON_NULL)
public class AbstractEvent {

    private EventType type;
    private Long accountId;
    private String owner;
    private Integer amount;
    private Date eventDate = new Date();

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", accountId=" + accountId +
                ", owner='" + owner + '\'' +
                ", amount=" + amount +
                '}';
    }
}

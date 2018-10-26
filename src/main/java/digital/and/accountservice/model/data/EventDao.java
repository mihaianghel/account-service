package digital.and.accountservice.model.data;

import digital.and.accountservice.util.EventType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "EVENTS")
public class EventDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "EVENT_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType type;

    @Column(name = "ACCOUNT_ID", nullable = false)
    private Long accountId;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "EVENT_DATE", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "NOTES")
    private String notes;
}

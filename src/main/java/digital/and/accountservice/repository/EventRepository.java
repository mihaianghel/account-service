package digital.and.accountservice.repository;

import digital.and.accountservice.model.data.EventDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface EventRepository extends CrudRepository<EventDao, Long> {

    @Query("SELECT a FROM EVENTS a WHERE (a.type = 'BANK_ACCOUNT_CREATED' OR a.type = 'BANK_ACCOUNT_CLOSED') AND a.accountId = :accountId")
    Collection<EventDao> findOpenAndClosedEventsForAccountId(
            @Param("accountId") Long accountId);

    @Query("SELECT a FROM EVENTS a WHERE a.eventDate <= :eventDate AND a.accountId = :accountId")
    Collection<EventDao> findByAccountIdAndAllWithCreationDateTimeBefore(
            @Param("accountId") Long accountId,
            @Param("eventDate") LocalDateTime eventDate);
}

package digital.and.accountservice.repository;

import digital.and.accountservice.model.data.EventDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EventRepository extends CrudRepository<EventDao, Long> {

    Collection<EventDao> findByAccountId(Long accountId);

}

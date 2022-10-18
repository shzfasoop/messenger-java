package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.Chat;
import com.kgaft.michatmessengerserver.Database.Entity.Message;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findMessagesByTargetOrderByDateAsc(Chat target);
    List<Message> findMessagesBySenderOrderByDateAsc(User sender);
    List<Message> findMessagesByDateBetweenAndTargetOrderByDateAsc(long start, long end, Chat target);

    boolean existsByDateBetweenAndTarget(long start, long end, Chat target);

}

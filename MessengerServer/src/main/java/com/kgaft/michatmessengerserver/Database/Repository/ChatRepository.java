package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.Chat;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface ChatRepository extends CrudRepository<Chat, Long> {
    List<Chat> findChatsByChatUsersContaining(User user);
    List<Chat> findChatsByCreationDateBetweenAndChatUsersContaining(long start, long end, User user);
}

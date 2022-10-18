package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UsersRepository extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
    boolean existsByLogin(String login);
    User findByNumber(String number);
}

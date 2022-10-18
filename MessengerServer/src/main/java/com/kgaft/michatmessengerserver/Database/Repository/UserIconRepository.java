package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.User;
import com.kgaft.michatmessengerserver.Database.Entity.UserIcon;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserIconRepository extends CrudRepository<UserIcon, Long> {
    UserIcon findUserIconByOwner(User owner);
}

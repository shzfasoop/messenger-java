package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.UsersPassword;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UsersPasswordRepository extends CrudRepository<UsersPassword, Long> {
    UsersPassword findUsersPasswordByUserId(long userId);
}

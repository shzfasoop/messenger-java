package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.UserEncryptionKeysPinCode;
import com.kgaft.michatmessengerserver.Database.Entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface UsersEncryptionKeysPinCodeRepository extends CrudRepository<UserEncryptionKeysPinCode, Long> {
    List<UserEncryptionKeysPinCode> findUserEncryptionKeysPinCodeByUser(User user);
    void deleteByUser(User user);
}

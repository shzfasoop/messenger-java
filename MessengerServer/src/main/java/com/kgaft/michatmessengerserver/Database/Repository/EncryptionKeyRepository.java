package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.SecureInfo.EncryptionKey;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface EncryptionKeyRepository extends CrudRepository<EncryptionKey, Long> {
    EncryptionKey findEncryptionKeysByChatId(long chatId);


}

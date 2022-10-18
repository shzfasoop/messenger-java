package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.FileEncrypted;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface FileEncryptedRepository extends CrudRepository<FileEncrypted, Long> {
}

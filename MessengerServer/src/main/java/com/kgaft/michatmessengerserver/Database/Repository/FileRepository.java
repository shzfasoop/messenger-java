package com.kgaft.michatmessengerserver.Database.Repository;

import com.kgaft.michatmessengerserver.Database.Entity.File;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface FileRepository extends CrudRepository<File, Long> {
}

package com.bytmasoft.dss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.FileDB;

@Transactional(readOnly = true)
@Repository
public interface FileDBRepository extends JpaRepository<FileDB, Long>{

	
}

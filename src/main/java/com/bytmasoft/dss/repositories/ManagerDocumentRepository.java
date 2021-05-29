/**
 * 
 */
package com.bytmasoft.dss.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bytmasoft.domain.models.ManagerDocument;

/**
 * @author Mahamat Abakar
 * created on 13.11.2020 at 09:54:58
 */
@Transactional(readOnly = true)
@Repository
public interface ManagerDocumentRepository extends JpaRepository<ManagerDocument , Long> {

	
}

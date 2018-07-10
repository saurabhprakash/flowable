package org.flowableSpringboot.bootstrap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationDataRepository extends CrudRepository<ApplicationData, Long> {

	
}

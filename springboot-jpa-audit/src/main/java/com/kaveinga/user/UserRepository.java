package com.kaveinga.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true, value="DELETE FROM user WHERE id = :id")
	int remove(@Param("id") Long id);
}

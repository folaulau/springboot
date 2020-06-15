package com.folaukaveinga.testing.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByLastName(String lastName);

    User findByEmail(String email);

}

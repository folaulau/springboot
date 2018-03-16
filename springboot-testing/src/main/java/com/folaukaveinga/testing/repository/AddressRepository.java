package com.folaukaveinga.testing.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.folaukaveinga.testing.domain.Address;
import java.lang.String;
import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>{
		public List<Address> findByState(String state);
}

package com.folaukaveinga.testing.service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.testing.domain.Address;
import com.folaukaveinga.testing.repository.AddressRepository;

@Service
public class AddressService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AddressRepository addressRepository;
	
	public Address save(Address address){
		address = addressRepository.save(address);
		log.info("address saved");
		log.info(address.toString());
		return address;
	}
	
	public Address get(long id){
		log.info("get address by id: {}",id);
		return addressRepository.getOne(id);
	}
	
	public List<Address> getByState(String state){
		log.info("get address by state: {}",state);
		return addressRepository.findByState(state);
	}
	
	public List<Address> getAll(){
		return addressRepository.findAll();
	}

	public Address update(Address address) {
		return addressRepository.save(address);
	}
	
	public boolean remove(long id) {
		try {
			addressRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}

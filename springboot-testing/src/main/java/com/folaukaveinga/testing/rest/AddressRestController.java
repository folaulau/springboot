package com.folaukaveinga.testing.rest;


import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.folaukaveinga.testing.domain.Address;
import com.folaukaveinga.testing.domain.User;
import com.folaukaveinga.testing.service.AddressService;
import com.folaukaveinga.testing.service.UserService;


@RestController
@RequestMapping("api/addresses")
public class AddressRestController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value={"/",""}, method=RequestMethod.POST)
	public ResponseEntity<Address> create(@RequestBody Address address){
		log.info("create address: "+address.toString());
		return new ResponseEntity<>(addressService.save(address), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/{id}","/{id}/"}, method=RequestMethod.GET)
	public ResponseEntity<Address> get(@PathVariable("id")int id){
		log.info("get address by id: {}", id);
		return new ResponseEntity<>(addressService.get(id), HttpStatus.OK);
	}
	
	@RequestMapping(value={"","/"}, method=RequestMethod.GET)
	public ResponseEntity<List<Address>> getAll(){
		log.info("get all addresses");
		return new ResponseEntity<>(addressService.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/update","/update/"}, method=RequestMethod.PATCH)
	public ResponseEntity<Address> update(@RequestBody Address address){
		log.info("patching address: "+address.toString());
		return new ResponseEntity<>(addressService.update(address), HttpStatus.OK);
	}
	
	@RequestMapping(value={"/remove/{id}","/remove/{id}/"}, method=RequestMethod.DELETE)
	public ResponseEntity<Boolean> remove(@PathVariable long id){
		log.info("remove address with id: {}",id);
		return new ResponseEntity<>(addressService.remove(id), HttpStatus.OK);
	}

}

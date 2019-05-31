package com.lovemesomecoding.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.model.User;

@Service
public class UserItemProcessor implements ItemProcessor<User, User> {

	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public User process(User item) throws Exception {
		//log.debug("process(..)");
		
		final String firstName = item.getFirstName().toUpperCase();
        final String lastName = item.getLastName().toUpperCase();

        final User transformedPerson = new User(firstName, lastName);
        
		return transformedPerson;
	}

}

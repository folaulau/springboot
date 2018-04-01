package com.folaukaveinga.validator;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.folaukaveinga.model.User;

@Service
public class UserValidator implements Validator {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		log.info("validating User...\n");
		//ValidationUtils.rejectIfEmpty(errors, "name", "user.name.empty");
		
		Locale spanish = new Locale("es");
		
		ValidationUtils.rejectIfEmpty(errors, "email", "Email", messageSource.getMessage("Email", null, spanish));
		ValidationUtils.rejectIfEmpty(errors, "name", "NotBlank", "Not empty");
		ValidationUtils.rejectIfEmpty(errors, "age", "Positive", "Not a postive number");
		ValidationUtils.rejectIfEmpty(errors, "role", "NotBlank", "Not blank");
		
		User user = (User)obj;
		
//		if(user.getRole().isEmpty()) {
//			errors.rejectValue("role", "NotBlank");
//		}
//		
//		if(user.getAge()<=0) {
//			errors.rejectValue("age", "Min");
//		}
		
		if(errors.hasErrors()) {
			log.info("user has errors");
		}
		
	}

}

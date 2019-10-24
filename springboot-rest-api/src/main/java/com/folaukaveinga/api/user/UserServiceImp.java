package com.folaukaveinga.api.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.folaukaveinga.api.client.RestService;
import com.folaukaveinga.api.utility.ObjectUtils;


@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private List<User> users = new ArrayList<>();
	
	
	@Autowired
	private RestService restService;
	
	@PostConstruct
	public void init() {
		for(int i=0;i<10;i++) {
			users.add(new User((i+1), UUID.randomUUID(), RandomStringUtils.randomAlphabetic(15), RandomStringUtils.randomAlphabetic(15)+"@gmail.com", RandomStringUtils.randomAlphabetic(5)));
		}
	}
	
	@Override
	public User create(User user) {
		user.setId(users.size()+1);
		user.setUuid(UUID.randomUUID());
		users.add(user);
		
		
		
		return user;
	}

	@Override
	public User update(User user) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(user.getId()==u.getId()) {
				u.setEmail(user.getEmail());
				u.setName(user.getName());
				u.setType(user.getType());
				u.setUuid(user.getUuid());
				break;
			}
		}
		return user;
	}

	@Override
	public User getById(Integer id) {
		

//		CompletableFuture<ArrayNode> plUsers = restService.getPlaceHolderTestingList(RestService.PLACEHOLDER_USER);
//		
//		CompletableFuture<ArrayNode> plTodos = restService.getPlaceHolderTestingList(RestService.PLACEHOLDER_TODO);
//		
//		CompletableFuture<ArrayNode> plComments = restService.getPlaceHolderTestingList(RestService.PLACEHOLDER_COMMENT);
//		
//		CompletableFuture<ArrayNode> plPhotos = restService.getPlaceHolderTestingList(RestService.PLACEHOLDER_PHOTO);
//		
//		CompletableFuture.allOf(plUsers,plTodos,plComments,plPhotos).join();
//		
//		try {
//			log.info("plUsers={}",ObjectUtils.toJson(plUsers.get()));
//			log.info("plTodos={}",ObjectUtils.toJson(plTodos.get()));
//			log.info("plComments={}",ObjectUtils.toJson(plComments.get()));
//			log.info("plPhotos={}",ObjectUtils.toJson(plPhotos.get()));
//			
//		} catch (InterruptedException | ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		List<String> placeHolders = Arrays.asList(RestService.PLACEHOLDER_USER,RestService.PLACEHOLDER_TODO);
		
		CompletableFuture<ArrayNode>[] futures = new CompletableFuture[placeHolders.size()];
		
		for(int i=0;i<futures.length;i++) {
			CompletableFuture<ArrayNode> pl = restService.getPlaceHolderTestingList(placeHolders.get(i));
			futures[i] = pl;
		}
		
		CompletableFuture.allOf(futures).join();
		
		try {
			for(int i=0;i<futures.length;i++) {
				ArrayNode plhds = futures[0].get();
				log.info("plhds={}",ObjectUtils.toJson(plhds));
			}
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(id==u.getId()) {
				log.info("found user!");
				return u;
			}
		}
		return null;
	}

	@Override
	public User getByUuid(UUID uuid) {
		Iterator<User> it = users.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(uuid==u.getUuid()) {
				return u;
			}
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return users;
	}

}

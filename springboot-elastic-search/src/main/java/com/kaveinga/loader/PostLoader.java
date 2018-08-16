package com.kaveinga.loader;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kaveinga.post.Post;
import com.kaveinga.post.PostRepository;
import com.kaveinga.user.User;
import com.kaveinga.user.UserService;

@Repository
public class PostLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@PostConstruct
	public void init() {
		log.info("Userloader.init()");
		for (int i = 1; i <= 50 ; i++) {
			Post post = new Post();
			post.setContent("content "+i);
			post = postRepository.saveAndFlush(post);
			log.info(post.toString());
		}
	}
}

package com.lovemesomecoding.user;

import java.util.Date;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedParallelScanList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.lovemesomecoding.utils.RandomGeneratorUtils;

@Repository
public class UserRepositoryImp implements UserRepository {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	
	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	@Override
	public User create(User user) {
		user.setUuid(RandomGeneratorUtils.getUserUuid());
		user.setCreatedAt(new Date());
		dynamoDBMapper.save(user);
		return getById(user.getUuid());
	}

	@Override
	public User getById(String id) {
		User user = dynamoDBMapper.load(User.class, id);
		return user;
	}



	@Override
	public List<User> getAllUser() {
		PaginatedScanList<User> users = dynamoDBMapper.scan(User.class, new DynamoDBScanExpression());
		
		return (users!=null) ? users.subList(0, users.size()) : null;
	}
	
	@Override
	public boolean createTable() {
		
		
		// check if table has been created
		try {
			DescribeTableResult describeTableResult = amazonDynamoDB.describeTable("user");
			
			if(describeTableResult.getTable()!=null){
				log.debug("user table has been created already!");
				return true;
			}
		} catch (Exception e) {
			
		}
		
		// table hasn't been created so start a createTableRequest
		CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(User.class);
		createTableRequest.withProvisionedThroughput(new ProvisionedThroughput(5L,5L));
		
		// create table
		CreateTableResult createTableResult = amazonDynamoDB.createTable(createTableRequest);
		
		long count = createTableResult.getTableDescription().getItemCount();
		
		log.debug("item count={}",count);
		
		return false;
	}

}

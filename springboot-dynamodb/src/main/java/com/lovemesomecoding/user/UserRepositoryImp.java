package com.lovemesomecoding.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ConditionCheck;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.DeleteTableResult;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.Put;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.ReturnConsumedCapacity;
import com.amazonaws.services.dynamodbv2.model.ReturnValuesOnConditionCheckFailure;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItem;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItemsRequest;
import com.amazonaws.services.dynamodbv2.model.TransactWriteItemsResult;
import com.amazonaws.services.dynamodbv2.model.TransactionCanceledException;
import com.amazonaws.services.dynamodbv2.model.Update;
import com.lovemesomecoding.utils.ObjectUtils;
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

		return (users != null) ? users.subList(0, users.size()) : null;
	}

	/**
	 * https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/WorkingWithTables.Basics.html
	 */
	@Override
	public boolean createTable() {

		// check if table has been created
		try {
			DescribeTableResult describeTableResult = amazonDynamoDB.describeTable("user");

			if (describeTableResult.getTable() != null) {
				log.debug("user table has been created already!");
				return true;
			}
		} catch (Exception e) {

		}

		// table hasn't been created so start a createTableRequest
		CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(User.class);

		// set provision through put if table is set to be on provision
		// createTableRequest.withProvisionedThroughput(new
		// ProvisionedThroughput(5L,5L));

		// set billing mode PAY_PER_REQUEST to make table to be on demand
		createTableRequest.withBillingMode("PAY_PER_REQUEST");
		// create table
		CreateTableResult createTableResult = amazonDynamoDB.createTable(createTableRequest);

		long count = createTableResult.getTableDescription().getItemCount();

		log.debug("item count={}", count);

		return false;
	}

	@Override
	public boolean deleteTable() {
		DeleteTableResult deleteTableResult = amazonDynamoDB.deleteTable("user");
		return (deleteTableResult != null) ? true : false;
	}
	
	/**
	 * Transaction
	 */

	@Override
	public boolean tranferBalance(double amount, String userIdA, String userIdB) {
		User userA = this.getById(userIdA);
		User userB = this.getById(userIdB);

		return tranferBalance(amount,userA,userB);
	}

	@Override
	public boolean tranferBalance(double amount, User userA, User userB) {
		final String USER_TABLE_NAME = "user";
		final String USER_PARTITION_KEY = "userid";
		
		try {
			// user A
			HashMap<String, AttributeValue> userAKey = new HashMap<>();
			userAKey.put(USER_PARTITION_KEY, new AttributeValue(userA.getUuid()));
			
			ConditionCheck checkUserAValid = new ConditionCheck()
			        .withTableName(USER_TABLE_NAME)
			        .withKey(userAKey)
			        .withConditionExpression("attribute_exists(" + USER_PARTITION_KEY + ")");
			
			Map<String, AttributeValue> expressionAttributeValuesA = new HashMap<>();
			expressionAttributeValuesA.put(":balance", new AttributeValue().withN("" + (userA.getBalance() - amount)));

			Update withdrawFromA = new Update().withTableName(USER_TABLE_NAME).withKey(userAKey)
					.withUpdateExpression("SET balance = :balance")
					.withExpressionAttributeValues(expressionAttributeValuesA);

		    log.debug("user A setup!");
			
			// user B
			HashMap<String, AttributeValue> userBKey = new HashMap<>();
			userAKey.put(USER_PARTITION_KEY, new AttributeValue(userB.getUuid()));

			ConditionCheck checkUserBValid = new ConditionCheck()
			        .withTableName(USER_TABLE_NAME)
			        .withKey(userBKey)
			        .withConditionExpression("attribute_exists(" + USER_PARTITION_KEY + ")");
			
			Map<String, AttributeValue> expressionAttributeValuesB = new HashMap<>();
			expressionAttributeValuesB.put(":balance", new AttributeValue().withN("" + (userB.getBalance() + amount)));
			
			Update depositToB = new Update().withTableName(USER_TABLE_NAME).withKey(userBKey)
					.withUpdateExpression("SET balance = :balance")
					.withExpressionAttributeValues(expressionAttributeValuesB);
			
			log.debug("user B setup!");
			
			
			HashMap<String, AttributeValue> withdrawItem = new HashMap<>();
			withdrawItem.put(USER_PARTITION_KEY, new AttributeValue(userA.getUuid()));
			withdrawItem.put("balance", new AttributeValue("100"));

		    Put createOrder = new Put()
		      .withTableName(USER_TABLE_NAME)
		      .withItem(withdrawItem)
		      .withConditionExpression("attribute_not_exists(" + USER_PARTITION_KEY + ")");
			
			// actions
			Collection<TransactWriteItem> actions = Arrays.asList(
					new TransactWriteItem().withConditionCheck(checkUserAValid),
					new TransactWriteItem().withUpdate(withdrawFromA),
					new TransactWriteItem().withPut(createOrder));
			
			log.debug("actions setup!");

			// transaction request
			TransactWriteItemsRequest withdrawTransaction = new TransactWriteItemsRequest()
					.withTransactItems(actions)
					.withReturnConsumedCapacity(ReturnConsumedCapacity.TOTAL);

			log.debug("transaction request setup!");
			
			// Execute the transaction and process the result.

			TransactWriteItemsResult transactWriteItemsResult = amazonDynamoDB.transactWriteItems(withdrawTransaction);
			log.debug("consumed capacity={}",ObjectUtils.toJson(transactWriteItemsResult.getConsumedCapacity()));
			return (transactWriteItemsResult.getConsumedCapacity()!=null) ? true : false;
		} catch (ResourceNotFoundException e) {
			log.error("One of the table involved in the transaction is not found " + e.getMessage());
		} catch (InternalServerErrorException e) {
			log.error("Internal Server Error " + e.getMessage());
		} catch (TransactionCanceledException e) {
			log.error("Transaction Canceled " + e.getMessage());
		} catch (Exception e) {
			log.error("Exception, msg={}",e.getLocalizedMessage());
		}

		return false;
	}

}

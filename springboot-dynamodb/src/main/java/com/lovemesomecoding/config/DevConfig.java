package com.lovemesomecoding.config;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovemesomecoding.utils.ObjectUtils;

@Profile("dev")
@Configuration
public class DevConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;
	
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = ObjectUtils.getObjectMapper();
		// Date and Time Format
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US));
		return objectMapper;
	}
	
	@Bean
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return DefaultAWSCredentialsProviderChain.getInstance();
	}
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB client =  AmazonDynamoDBClientBuilder.standard()
				.withCredentials(amazonAWSCredentialsProvider())
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, Regions.US_WEST_2.getName()))
				.build();
		return client;
	}
	
	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB());
	}

}

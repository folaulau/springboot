package com.lovemesomecoding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Profile(value = {"dev"})
@Configuration
public class DevConfig {
    private Logger                 log = LoggerFactory.getLogger(this.getClass());
    
	@Bean
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		
		return DefaultAWSCredentialsProviderChain.getInstance();
	}
	
	@Bean
	public AmazonSNS amazonSNS() {
		AmazonSNS sns = AmazonSNSClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.US_WEST_2).build();
		return sns;
	}
	
	@Bean
	public AmazonSQS amazonSQS() {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.US_WEST_2).build();
		return sqs;
	}
   
}

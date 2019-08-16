package com.lovemesomecoding.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

/**
 * @author folaukaveinga
 *
 */
@Profile("local")
@Configuration
public class LocalConfig {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Bean
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new ProfileCredentialsProvider("folauk100-dev");
	}
	
	@Bean
	public AmazonSQS amazonSQSMain() {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard().withCredentials(amazonAWSCredentialsProvider())
				.withRegion(Regions.US_WEST_2).build();
		return sqs;
	}
}

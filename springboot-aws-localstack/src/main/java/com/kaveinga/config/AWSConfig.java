package com.kaveinga.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
public class AWSConfig {
//	@Value("${amazon.aws.accesskey}")
//	private String amazonAWSAccessKey;
//
//	@Value("${amazon.aws.secretkey}")
//	private String amazonAWSSecretKey;

	// AWS Credentials
	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials("accesskey", "secretkey");
	}

	@Bean
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}

	// SQS
	@Bean
	public AmazonSQS amazonSQSMain() {
		AmazonSQS sqs = AmazonSQSClientBuilder.standard()
				.withCredentials(amazonAWSCredentialsProvider())
				.withEndpointConfiguration(new EndpointConfiguration("http://localhost:4576", "us-east-1"))
				.build();
		return sqs;
	}
	
	@Bean
	public AmazonSNS amazonSNS() {
		return AmazonSNSClientBuilder.standard()
				.withCredentials(amazonAWSCredentialsProvider())
				.withEndpointConfiguration(new EndpointConfiguration("http://localhost:4575", "us-east-1"))
				.build();
	}
}

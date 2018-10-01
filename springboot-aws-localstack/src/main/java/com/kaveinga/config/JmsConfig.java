package com.kaveinga.config;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
@EnableJms
public class JmsConfig {
	
	@Autowired
	private AWSCredentialsProvider awsCredentialsProvider;
	
	@Bean
    public SQSConnectionFactory sqsConnectionFactory() {

		SQSConnectionFactory sqsConnectionFactory =  new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
                	.withCredentials(awsCredentialsProvider)
                	.withEndpointConfiguration(new EndpointConfiguration("http://localhost:4576", "us-east-1"))
                );
		
		return sqsConnectionFactory;
    }
	

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =  new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.sqsConnectionFactory());
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate() {
    	JmsTemplate jmsTemplate =  new JmsTemplate(this.sqsConnectionFactory());
    	return jmsTemplate;
    }
}

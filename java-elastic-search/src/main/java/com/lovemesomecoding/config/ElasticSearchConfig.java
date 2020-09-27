package com.lovemesomecoding.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * ElasticSearchConfig
 * 
 * @author folaukaveinga
 * 
 *         <br>
 * 
 *         Elastic Search bean is configured in properties files but basePackage
 *         is required
 */
// @Profile("!local")
@Configuration
public class ElasticSearchConfig extends AbstractFactoryBean<RestHighLevelClient> {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private RestHighLevelClient restHighLevelClient;

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNode;

	@Value("${elasticsearch.cluster-nodes.http-type}")
	private String clusterHttpType;

	@Value("${elasticsearch.cluster-nodes.http-port}")
	private int clusterHttpPort;

	@Override
	public void destroy() {
		try {
			if (restHighLevelClient != null) {
				restHighLevelClient.close();
			}
			log.info("ElasticSearch rest high level client closed");
		} catch (final Exception e) {
			log.error("Error closing ElasticSearch client: ", e);
		}
	}

	@Override
	public Class<RestHighLevelClient> getObjectType() {
		return RestHighLevelClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public RestHighLevelClient createInstance() {
		return buildClient();
	}

	private RestHighLevelClient buildClient() {
		try {

			// port: 443
			// http: http
			RestClientBuilder restClientBuilder = RestClient
					.builder(new HttpHost(clusterNode, clusterHttpPort, clusterHttpType));
			restClientBuilder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {

				@Override
				public Builder customizeRequestConfig(Builder requestConfigBuilder) {
					// TODO Auto-generated method stub
					return requestConfigBuilder.setConnectTimeout(1000000).setSocketTimeout(6000000)
							.setConnectionRequestTimeout(300000);
				}

			});

			restHighLevelClient = new RestHighLevelClient(restClientBuilder);

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return restHighLevelClient;
	}

}

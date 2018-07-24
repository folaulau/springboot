package com.folaukaveinga.client.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class HttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
	}
    private void logRequest(HttpRequest request, byte[] body) throws IOException {
        //if (log.isDebugEnabled()) {
            log.info("===========================Http Request begin=============================================");
            log.info("URI         : {}", request.getURI());
            log.info("Method      : {}", request.getMethod());
            log.info("Headers     : {}", request.getHeaders());
            log.info("Request body: {}", ObjectUtil.getObjectMapper().writeValueAsString(body));
            log.info("===========================Http Request end===============================================");
        //}
    }
 
    private void logResponse(ClientHttpResponse response) throws IOException {
        //if (log.isDebugEnabled()) {
            log.info("===========================Http Response begin============================================");
            log.info("Status code  : {}", response.getStatusCode());
            log.info("Status text  : {}", response.getStatusText());
            log.info("Headers      : {}", response.getHeaders());
            log.info("Response body: {}", ObjectUtil.getObjectMapper().writeValueAsString(response.getBody()));
            log.info("===========================Http Response end==============================================");
        //}
    }

}

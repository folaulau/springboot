package com.kaveinga.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import brave.ScopedSpan;
import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private Tracer tracer;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.debug("before filter url={}", request.getRequestURL().toString());

		String token = request.getHeader("token");
//
//		tracer.currentSpan().customizer().tag("tagKey", "tagValue").name("newSpanName");
		Span currentSpan = this.tracer.currentSpan();

		((HttpServletResponse) response).addHeader("ZIPKIN-TRACE-ID", currentSpan.context().traceIdString());
		// we can also add some custom tags
		currentSpan.tag("custom", "tag");
		log.debug("spanId={}", currentSpan.context().spanIdString());
		log.debug("traceId={}", currentSpan.context().traceIdString());
		TraceContext traceContext = currentSpan.context().toBuilder().spanId(999999).traceId(9889898).build();
		this.tracer.joinSpan(traceContext);
		ScopedSpan scopedSpan = tracer.startScopedSpan("sdfsd");
		scopedSpan.context().toBuilder().spanId(999999).traceId(9889898).build();
		log.debug("spanId={}", traceContext.spanId());
		log.debug("traceId={}", traceContext.traceId());
		filterChain.doFilter(request, response);

		scopedSpan.finish();
		log.debug("after filter");

	}

}

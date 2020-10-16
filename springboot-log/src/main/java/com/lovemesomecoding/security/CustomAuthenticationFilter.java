package com.lovemesomecoding.security;

import java.io.IOException;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String token = request.getHeader("token");
        String memberUuid = request.getHeader("memberUuid");

        if (memberUuid == null) {
            memberUuid = "mem-dedecdbf-d9b7-448b-989f-4c336cf3bae3";
        }

        ThreadContext.put("memberUuid", memberUuid);

        filterChain.doFilter(request, response);

        /**
         * Remove custom tag from logs.<br>
         * After the transaction is over or context information is no more required,<br>
         * you can empty the information using ThreadContext.clearMap() method.
         */
        ThreadContext.clearMap();

    }

}

package com.lovemesomecoding;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PingController {

    @GetMapping(value = {"/", "/ping"})
    public ResponseEntity<String> logout(HttpServletRequest request) {

        String requestorIPAddress = "";

        if (request != null) {
            requestorIPAddress = request.getHeader("X-FORWARDED-FOR");
            if (requestorIPAddress == null || "".equals(requestorIPAddress)) {
                requestorIPAddress = request.getRemoteAddr();
            }
        }

        log.info("ping from ip: {}", requestorIPAddress);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

}

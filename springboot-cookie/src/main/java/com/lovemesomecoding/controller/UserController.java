package com.lovemesomecoding.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

import com.lovemesomecoding.utils.CookieNames;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {

    @GetMapping(value = {"/"})
    public String getHomePage(HttpSession session, HttpServletResponse response) {
        log.info("get home page");

        log.info("sessionId={}", session.getId());

        Cookie cookie = new Cookie(CookieNames.COLOR.name(), "blue");

        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setComment("user fav color");
        cookie.setVersion(1);
        // optional properties
        // cookie.setSecure(true);
        // cookie.setHttpOnly(true);
        // cookie.setPath("/");

        // add cookie to response
        response.addCookie(cookie);

        response.addHeader("", "");

        return "index";
    }

    @GetMapping(value = {"/profile"})
    public String getProfilePage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        log.info("get profile page");

        log.info("sessionId={}", session.getId());

        Cookie colorCookie = WebUtils.getCookie(request, CookieNames.COLOR.name());
        log.info("colorCookie name={}, colorCookie value={}", colorCookie.getName(), colorCookie.getValue());

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                log.info("cookie name={}, cookie value={}, cookie age={}, cookie httpOnly={}, cookie secure={}", cookie.getName(), cookie.getValue(), cookie.getMaxAge(), cookie.isHttpOnly(),
                        cookie.getSecure());
            }
        }

        Cookie cookie = new Cookie(CookieNames.PROFILE.name(), "folauk");

        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setComment("user profile");
        cookie.setVersion(1);

        response.addCookie(cookie);

        return "profile";
    }

    @GetMapping(value = {"/account"})
    public String getAccountPage(HttpSession session, @CookieValue(required = false, name = "COLOR") String color) {
        log.info("get account page");

        log.info("sessionId={}", session.getId());

        log.info("colorCookie={}", color);

        return "account";
    }

    @GetMapping(value = {"/login"})
    public String getLoginPage(HttpSession session) {
        log.info("get login page");

        log.info("sessionId={}", session.getId());

        return "login";
    }
    
    @GetMapping(value = {"/admin/stats"})
    public String getAdminStats(HttpSession session) {
        log.info("get admin stats page");

        log.info("sessionId={}", session.getId());

        return "admin_stats";
    }
}

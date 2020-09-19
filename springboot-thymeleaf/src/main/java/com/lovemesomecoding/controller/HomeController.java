package com.lovemesomecoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Api(value = "home", tags = "HomeController")
@Slf4j
@Controller
public class HomeController {

    @GetMapping
    public String showHome(Model model, @RequestParam String name) {
        log.info("home");
        model.addAttribute("name", name);

        return "index";
    }

}

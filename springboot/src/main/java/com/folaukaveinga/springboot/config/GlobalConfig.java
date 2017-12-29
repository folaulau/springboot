package com.folaukaveinga.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value= {"spring.cfg.xml", "spring2.cfg.xml"})
public class GlobalConfig {

}

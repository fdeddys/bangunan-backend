package com.ddabadi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Created by deddy on 5/23/16.
 */
@SpringBootApplication
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        //System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
        //super.configure(builder); //To change body of generated methods, choose Tools | Templates.
    }

}

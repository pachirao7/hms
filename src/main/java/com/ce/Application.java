package com.ce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;

/**
 * Created by raop on 3/13/18.
 */
@SpringBootApplication
@ComponentScan("com.ce")
public class Application {

    public static void main(String args[]) {
        SpringApplication.run(Application.class,args);
    }

}

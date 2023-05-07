package com.onurergun.finobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication()
public class FinobsBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinobsBeApplication.class, args);
    }

}

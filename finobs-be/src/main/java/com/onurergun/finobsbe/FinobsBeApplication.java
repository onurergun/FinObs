package com.onurergun.finobsbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FinobsBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinobsBeApplication.class, args);
    }

}

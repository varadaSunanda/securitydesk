package com.codepirates.securitydesk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SecuritydeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritydeskApplication.class, args);
    }

}

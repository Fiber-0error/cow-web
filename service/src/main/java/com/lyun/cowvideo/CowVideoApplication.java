package com.lyun.cowvideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class CowVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CowVideoApplication.class, args);
    }

}

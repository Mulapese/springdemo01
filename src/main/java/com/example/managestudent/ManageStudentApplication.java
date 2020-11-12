package com.example.managestudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ManageStudentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageStudentApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_12).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.managestudent")).build();
    }
}

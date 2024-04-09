package com.moretto.bruno.myspringapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "my-spring-api",
                description = "Project to demonstrate my knowledge in Spring Boot and your features.",
                version = "1.0.0",
                contact = @Contact(name = "Bruno Moretto", email = "bmoretto404@gmail.com", url = "https://www.linkedin.com/in/bruno-moretto-9b518618a/")
        ),
        servers = @Server(url = "http://localhost:8080", description = "This is a portfolio project, therefore it does not have a productive server.")
)
public class OpenAPIConfig {
}

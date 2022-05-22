package com.melichallenger.users;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author biosx1706
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "API de Usuarios", 
    version = "1.0", 
    description = "Gestión de Usuarios",
    contact = @Contact(
        name = "Miguel Angel Morales Coterio", 
        email = "miguelmoralescoterio@gmail.com",
        url = "https://gitlab.com/miguelmoralescoterio"
     ),
     license = @License(
        name = "Apache 2.0", 
        url = "http://www.apache.org/licenses/LICENSE-2.0"
     )
))
public class UsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class, args);
    }
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("users")
            .packagesToScan("com.melichallenger")
            .build();
    }

}

package com.cz.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setName("陈正");
        contact.setEmail("2427999362@qq.com");

        return new OpenAPI()
                .info(new Info()
                        .title("(待定)后端接口说明")
                        .description("此文档详细说明了(待定)项目后端接口规范")
                        .version("v 1.0.9")
                        .contact(contact)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//                .externalDocs(new ExternalDocumentation()
//                    .description("外部文档")
//                    .url("https://springshop.wiki.github.org/docs"));
    }
}
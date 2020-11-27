package com.example.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2 // Swagger2 버전을 활성화 하겠다는 어노테이션
public class SwaggerConfig {

    String version = "V1";
    String title = "GGOMJAE API " + version;
    private static final Contact DEFAULT_CONTACT = new Contact("Contact Me", "www.ggomjae.com", "ggomjae@naver.com");
    private static final Set<String> DEFAULT_PRODUCES_CONSUMES = new HashSet<>(
            Arrays.asList("application/json","application/xml")
    );

    /*
        - ERROR 로 인한 추가 코드 discoverers
        Parameter 0 of method linkDiscoverers in org.springframework.hateoas.config.HateoasConfiguration
        required a single bean
     */
    @Primary
    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2);
//    }
    
    @Bean
    public Docket apiV1() {

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .produces(DEFAULT_PRODUCES_CONSUMES)
                .consumes(DEFAULT_PRODUCES_CONSUMES)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.restapi.controller"))
                .paths(PathSelectors.ant("/users/**"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger로 생성한 API Docs",
                version,
                "www.ggomjae.com",
                DEFAULT_CONTACT,
                "Licenses",
                "www.ggomjae.com",
                new ArrayList<>());
    }

}


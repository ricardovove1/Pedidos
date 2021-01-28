package com.ma.pedidos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ma.pedidos.controller")).paths(PathSelectors.any())
				.build().tags(new Tag("product", "Servicios para el manejo de productos"),new Tag("order","Servicios para el menejo de pedidos "));

	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Pedidos").description("Api para realizar pedidos").version("0.0.1")
				.license("").licenseUrl("").build();
	}

}

package com.example.demo.client;

import java.nio.charset.Charset;
import java.util.Base64;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;

@Configuration
public class GraphQlClient {

	@Bean
	public HttpGraphQlClient httpClient() {

		String auth = "admin" + ":" + "password";
		String token = Base64.getEncoder().encodeToString(auth.getBytes(Charset.forName("US-ASCII")));
		System.out.println("token:: " + token);
		return HttpGraphQlClient.builder().url("http://localhost:8081/graphql")
				.header("Authorization", "Basic " + token).build();
	}

}

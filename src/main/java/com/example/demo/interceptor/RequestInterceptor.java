package com.example.demo.interceptor;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class RequestInterceptor implements WebGraphQlInterceptor {

	@Override
	public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
		// TODO Auto-generated method stub
		System.out.println("Req intercepted:: "+request.getDocument());
		System.out.println("Headers intercepted:: "+request.getHeaders());
		return chain.next(request);
	}

}

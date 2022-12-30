package com.example.demo.exception;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.GraphqlErrorException;
import graphql.schema.DataFetchingEnvironment;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

		if (ex instanceof GraphQLError)
			return new GraphqlErrorException.Builder().message(ex.getMessage())
					.cause(ex).errorClassification(ErrorType.BAD_REQUEST).build();
		
		return super.resolveToSingleError(ex, env);
	}

}

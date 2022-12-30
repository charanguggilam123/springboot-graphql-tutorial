package com.example.demo.exception;

import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class DBException extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6966091542353801006L;

	public DBException(String message) {
		super(message);
	}

	public DBException(Throwable ex) {
		super(ex);
	}

	public DBException(String message, Throwable ex) {
		super(message, ex);
	}

	@Override
	public List<SourceLocation> getLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		// TODO Auto-generated method stub
		return ErrorType.DataFetchingException;
	}

}

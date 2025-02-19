package com.it_patagonia_challenge.it_patagonia_challenge.domain.exception;

public class MissingRequiredAttributeException extends RuntimeException
{
	public MissingRequiredAttributeException(String message)
	{
		super(message);
	}
}

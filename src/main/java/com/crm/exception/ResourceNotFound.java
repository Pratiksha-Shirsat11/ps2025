package com.crm.exception;

//Runtime Exception because it extends Exception and we  get exception during runtime
//whenever we create a custom exception class there should be a constructor with super keyword
//Without super(message), the exception will not display the custom error message.
//super(message) → Passes error message to RuntimeException.
public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message) {
        super(message);
    }
}

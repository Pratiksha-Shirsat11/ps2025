package com.crm.exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//global catch block
//@ControllerAdvice is a global exception handler that handles exceptions for all controllers in a Spring Boot application.
//Avoid repeating try-catch in multiple controllers.
@ControllerAdvice
public class HandleException {

    //to handle exception for employee not found

    //@ExceptionHandler(ResourceNotFound.class)
    // public ResponseEntity <ErrorDetails>handleEmployeeNotFoundException(
    //  ResourceNotFound e,
    @ExceptionHandler(Exception.class)

    public ResponseEntity <ErrorDetails>globalException(

            Exception e,
            WebRequest request

    )
    {
        ErrorDetails errorDetails = new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

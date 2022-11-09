package be.abis.ordersandwich.handler;


import be.abis.ordersandwich.error.ApiError;
import be.abis.ordersandwich.error.ValidationError;

import be.abis.ordersandwich.exception.PersonNotFoundException;

import be.abis.ordersandwich.exception.SessionNotFoundException;
import be.abis.ordersandwich.exception.ShopAlreadyExistsException;
import be.abis.ordersandwich.exception.ShopNotFoundException;
import be.abis.ordersandwich.model.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.List;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = PersonNotFoundException.class)
    protected ResponseEntity<? extends Object> personNotFound
            ( PersonNotFoundException ance, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("not found", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = SessionNotFoundException.class)
    protected ResponseEntity<? extends Object> sessionNotFound
            (SessionNotFoundException ance, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("not found", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = ShopNotFoundException.class)
    protected ResponseEntity<?> handleShopNotFound
            (ShopNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError("Not found", status.value(), exception.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<>(apiError, responseHeaders, status);
    }

    @ExceptionHandler(value = ShopAlreadyExistsException.class)
    protected ResponseEntity<?> handleShopAlreadyExists
            (ShopAlreadyExistsException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError("Shop Already Exists", status.value(), exception.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<>(apiError, responseHeaders, status);
    }



}

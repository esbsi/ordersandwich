package be.abis.ordersandwich.handler;


import be.abis.ordersandwich.error.ApiError;
import be.abis.ordersandwich.error.ValidationError;

import be.abis.ordersandwich.exception.*;

import be.abis.ordersandwich.model.Session;
import org.springframework.core.annotation.Order;
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
    @ExceptionHandler(value = OrderNotFoundException.class)
    protected ResponseEntity<? extends Object> orderNotfound
            ( OrderNotFoundException ance, WebRequest request) {
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

    @ExceptionHandler(value = NullInputException.class)
    protected ResponseEntity<? extends Object> nullInput
            (NullInputException ance, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError err = new ApiError("null input", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = TooLateException.class)
    protected ResponseEntity<? extends Object> tooLate
            (TooLateException ance, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("too late", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = TooManySandwichesException.class)
    protected ResponseEntity<? extends Object> tooMany
            (TooManySandwichesException ance, WebRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        ApiError err = new ApiError("ordering too many sandwiches", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = PersonAlreadyInSessionException.class)
    protected ResponseEntity<? extends Object> personInSession
            (PersonAlreadyInSessionException ance, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("Person is already in the session", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }

    @ExceptionHandler(value = PersonAlreadyInExistException.class)
    protected ResponseEntity<? extends Object> personInSession
            (PersonAlreadyInExistException ance, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError err = new ApiError("Person is already exists", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }


    @ExceptionHandler(value = ShopNotFoundException.class)
    protected ResponseEntity<?> handleShopNotFound
            (ShopNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError("Shop Not Found", status.value(), exception.getMessage());
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

    /*    @ExceptionHandler(value = SandwichTypeNotFoundException.class)
    protected ResponseEntity<? extends Object> SanwichNotFound
            (SandwichTypeNotFoundException ance, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError err = new ApiError("sandwich type not found", status.value(), ance.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type",
                MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(err, responseHeaders, status);
    }
 */

    @ExceptionHandler(value = SandwichTypeNotFoundException.class)
    protected ResponseEntity<?> handleSandwichTypeNotFound
            (SandwichTypeNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError("SandwichType Not found", status.value(), exception.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<>(apiError, responseHeaders, status);
    }

    @ExceptionHandler(value = SandwichTypeAlreadyExistsException.class)
    protected ResponseEntity<?> handleSandwichTypeAlreadyExists
            (SandwichTypeAlreadyExistsException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError("SandwichType Already Exists", status.value(), exception.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<>(apiError, responseHeaders, status);
    }



}

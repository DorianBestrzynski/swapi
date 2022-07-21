package com.miquido.interview.swapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler {
    private final static ZoneId ZONE_ID = ZoneId.of("Europe/Warsaw");

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException ex){
        return handeExceptions(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ApiDuplicateCharacterException.class})
    public ResponseEntity<Object> handleApiDuplicateCharacterException(ApiDuplicateCharacterException ex){
        return handeExceptions(ex, HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> handeExceptions(RuntimeException ex, HttpStatus request){
        ApiException apiException = new ApiException(ex.getMessage(), request, ZonedDateTime.now(ZONE_ID));
        return new ResponseEntity<>(apiException, request);
    }

}

package com.miquido.interview.swapi.exception;

public class ApiDuplicateCharacterException extends RuntimeException  {
    public ApiDuplicateCharacterException(String message){
        super(message);
    }
    public ApiDuplicateCharacterException(String message, Throwable cause){
        super(message, cause);
    }
}

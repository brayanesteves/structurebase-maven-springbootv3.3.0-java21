package com.halconbit.structurebase_maven_springbootv33.__java21.exceptions;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CustomException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private final HttpHeaders httpHeaders;
    private final HttpStatus httpStatus;
    private final String field;
    private final List<String> errors;

    /**
     * Constructor for Exception.
     *
     */
    public CustomException(){
        super("error.no.controlled");
        this.httpHeaders = new HttpHeaders();
        this.httpStatus = null;
        this.field = "";
        this.errors = null;
    }
    public CustomException(List<String> errors, HttpHeaders httpHeaders, HttpStatus httpStatus, String field,
                            String message) {
        super(message);
        this.errors = errors;
        this.httpHeaders = httpHeaders;
        this.httpStatus = httpStatus;
        this.field = field;
    }

    /**
     * Constructor for Exception.
     *
     * @param httpHeaders HttpHeaders
     * @param httpStatus  HttpStatus
     * @param field       String
     * @param message     String
     */
    public CustomException(HttpHeaders httpHeaders, HttpStatus httpStatus, String field, String message) {
        super(message);
        this.httpHeaders = httpHeaders;
        this.httpStatus = httpStatus;
        this.field = field;
        this.errors = null;
    }

    /**
     * Constructor for Exception.
     *
     * @param httpStatus HttpStatus
     * @param field      String
     * @param message    String
     */
    public CustomException(HttpStatus httpStatus, String field, String message) {
        super(message);
        this.httpHeaders = null;
        this.httpStatus = httpStatus;
        this.field = field;
        this.errors = null;
    }

    /**
     * Constructor for Exception.
     *
     * @param field   String
     * @param message String
     */
    public CustomException(String field, String message) {
        super(message);
        this.httpHeaders = null;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        this.field = field;
        this.errors = null;
    }

    /**
     * Constructor for Exception.
     *
     * @param httpStatus HttpStatus
     * @param field      String
     * @param message    String
     */
    public CustomException(HttpStatusCode httpStatus, String field, String message) {
        super(message);
        this.httpHeaders = null;
        this.httpStatus = HttpStatus.resolve(httpStatus.value());
        this.field = field;
        this.errors = null;
    }

}
package org.github.jamiebuckley.quinqueapi.dto;

import org.springframework.http.HttpStatus;

public class APIError {

    public APIError() {
    }

    public APIError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private HttpStatus status;

    private String message;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

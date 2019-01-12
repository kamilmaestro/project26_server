package com.pwsz.project26_server.domain.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Path is wrong- can not resolve that id")
public class UrlError extends RuntimeException{

    public UrlError() {}

    public UrlError(String message) {
        super(message);
    }

    public UrlError(String message, Throwable cause) {
        super(message, cause);
    }
}

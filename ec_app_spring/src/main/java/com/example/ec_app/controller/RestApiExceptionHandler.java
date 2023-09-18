package com.example.ec_app.controller;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.ec_app.exception.ApplicationException;
import com.example.ec_app.exception.ApplicationValidationException;
import com.example.ec_app.payload.response.error.ApiErrorResponse;
import com.example.ec_app.payload.response.error.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleException(final AccessDeniedException e,
            final HttpServletRequest request) {
        log.error("invalid access on api", e);
        return new ApiErrorResponse(request.getRequestURI());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiErrorResponse handleAuthenticationException(final AuthenticationException e,
            final HttpServletRequest request) {
        return new ApiErrorResponse(request.getRequestURI());
    }

    @ExceptionHandler({ApplicationException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleApplicationException(
            final Throwable e,
            final HttpServletRequest request) {
        log.error("[FATAL]raise application exception on api", e);
        return new ApiErrorResponse(request.getRequestURI());
    }

    @ExceptionHandler({ApplicationValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleApplicationValidationException(
            final ApplicationValidationException e,
            final HttpServletRequest request) {
        return new ValidationErrorResponse(request.getRequestURI(), e.getItems());
    }


}

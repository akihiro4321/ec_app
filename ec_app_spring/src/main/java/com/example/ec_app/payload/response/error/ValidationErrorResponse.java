package com.example.ec_app.payload.response.error;

import java.util.List;

public class ValidationErrorResponse extends ApiErrorResponse {

    private static final String TYPE = "validation-error";
    private static final String TITLE = "Bad request";
    private static final String MESSAGE = "The request contained invalid inputs.";
    private final List<String> errors;

    public ValidationErrorResponse(final String requestUri, final List<String> errors) {
        super(TYPE, TITLE, MESSAGE, requestUri);
        this.errors = errors;
    }
}

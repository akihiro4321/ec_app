package com.example.ec_app.payload.response.error;

public class AuthorizationErrorResponse extends ApiErrorResponse {

    private static final String TYPE = "authorization-error";
    private static final String TITLE = "Access denied";
    private static final String MESSAGE = "You do not have the permimissions to access this page";

    public AuthorizationErrorResponse(final String requestUri) {
        super(TYPE, TITLE, MESSAGE, requestUri);
    }

}

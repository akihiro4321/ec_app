package com.example.ec_app.payload.response.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorResponse {

    private static final String DEFAULT_TYPE = "system-error";
    private static final String DEFAULT_TITLE = "System Error";
    private static final String DEFAULT_MESSAGE = "An unexpected error occurred in application";

    public ApiErrorResponse(final String requestUri) {
        this.type = DEFAULT_TYPE;
        this.title = DEFAULT_TITLE;
        this.message = DEFAULT_MESSAGE;
        this.requestUri = requestUri;
    }

    private String type;
    private String title;
    private String message;
    private String requestUri;
}

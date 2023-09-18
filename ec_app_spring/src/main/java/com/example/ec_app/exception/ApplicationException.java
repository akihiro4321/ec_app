package com.example.ec_app.exception;

/**
 * アプリケーションで意図的に発生させる例外クラス
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(final String message) {
        super(message);
    }

}

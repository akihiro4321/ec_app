package com.example.ec_app.exception;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;

/**
 * アプリケーションでバリデーションエラーがある場合に発行される例外クラス
 */
@Getter
public class ApplicationValidationException extends ApplicationException {

    private final List<String> items;

    public ApplicationValidationException(@NonNull final List<String> items,
            final String message, final Throwable cause) {
        super(message, cause);
        this.items = items;
    }

}

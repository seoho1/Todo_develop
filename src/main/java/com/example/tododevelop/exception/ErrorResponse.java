package com.example.tododevelop.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    // 에러 코드
    private final int code;

    private final HttpStatus httpStatus;

    private final String messege;

    private ErrorResponse(int code, HttpStatus httpStatus, String messege) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.messege = messege;
    }

    public static Map<String, Object> ofCustomException(CustomException e) {
        return createErrorResponse(e.getHttpStatus(), e.getErrorMessage());
    }

    private static Map<String, Object> createErrorResponse(HttpStatus httpStatus, String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", httpStatus.value());
        response.put("httpStatus", httpStatus);
        response.put("message", errorMessage);

        return response;
    }
}

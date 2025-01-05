package com.example.tododevelop.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    public static Map<String, Object> ofMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return createErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);

    }

    public static Map<String, Object> ofConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("\n"));

        return createErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }

    public static Map<String, Object> ofHandleInvalidPasswordException(InvalidPasswordException e) {
        String errorMessage = e.getMessage();

        return createErrorResponse(HttpStatus.BAD_REQUEST, errorMessage);
    }
}

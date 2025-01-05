package com.example.tododevelop.exception;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *  CustomException 예외 처리
     * @param e CustomException
     * @return ErrorResponse
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException e) {
        Map<String, Object> response = ErrorResponse.ofCustomException(e);

        log.info("[{}] {} : {}", e.getStackTrace()[0], e.getHttpStatus(), e.getErrorMessage());

        return ResponseEntity.status(e.getHttpStatus()).body(response);
    }
}


package com.example.pnnback.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// 맨 위에 import 추가!
import com.example.pnnback.dto.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgument(IllegalArgumentException e) {
        return new ErrorResponse(e.getMessage());
    }
}

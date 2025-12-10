package org.snakeinc.snake.exceptions;

import org.snakeinc.snake.dto.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorDto> handle(PlayerNotFoundException e) {

        ErrorDto errorDto = new ErrorDto(List.of(e.getMessage()));
        return new ResponseEntity<>(errorDto,HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handle(MethodArgumentNotValidException e) {

        ErrorDto errorDto = new ErrorDto(List.of(e.getBindingResult().getAllErrors().getFirst().getDefaultMessage()));
        return new ResponseEntity<>(errorDto,HttpStatusCode.valueOf(400));
    }

}

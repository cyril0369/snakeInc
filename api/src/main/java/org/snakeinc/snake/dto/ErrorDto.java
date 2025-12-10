package org.snakeinc.snake.dto;

import java.util.List;

public class ErrorDto {

    public ErrorDto(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }

    List<String> errors;

}

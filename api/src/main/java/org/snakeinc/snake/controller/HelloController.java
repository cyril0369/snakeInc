package org.snakeinc.snake.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/hello")
public class HelloController {

    @GetMapping
    public String HelloController(String name) {
        return "hello " + name;
    }

    @PostMapping
    public String postHello(@RequestBody BodyParam name){
        return "post " + name.name();
    }

    private record BodyParam(String name) {

    }
}



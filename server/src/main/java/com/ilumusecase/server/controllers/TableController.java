package com.ilumusecase.server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableController {
    
    @GetMapping("/test")
    public String test(){
        return "Hello world";
    }

}

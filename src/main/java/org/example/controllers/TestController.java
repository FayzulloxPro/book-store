package org.example.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/open")
    public String open(){
        return "Open endpoint";
    }
    @GetMapping("/api/closed")
    public String closed(){
        return "Closed endpoint";
    }
}

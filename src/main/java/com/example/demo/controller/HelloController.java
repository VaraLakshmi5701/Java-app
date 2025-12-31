package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }
    @GetMapping("/")
    public String home() {
        return "Java App is running successfully 🚀";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return helloService.getMessage();
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running";
    }
}

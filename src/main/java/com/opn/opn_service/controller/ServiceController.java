package com.opn.opn_service.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("/")
    public String home() {
        return "Root Home";
    }

}

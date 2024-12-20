package com.opn.opn_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usr")
public class UserController {
    @GetMapping("/home")
    public String home() {
        return "Welcome Home";
    }

}

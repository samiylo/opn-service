package com.opn.opn_service.controller;

import com.opn.opn_service.bo.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/usr")
public class UserController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String getWebToken() throws Exception {
        System.out.println("Controlelr");
        KeyPair keys = loginService.generateJWT();
        System.out.println(keys);
        return "Login Page";
    }

}

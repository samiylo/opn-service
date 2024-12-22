package com.opn.opn_service.controller;

import com.opn.opn_service.bo.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usr")
public class UserController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String getWebToken() throws Exception {
        return loginService.getLoginKey();
    }

    @PostMapping("/validate")
    public Boolean validateWebToken(@RequestHeader("Authorization") String token) throws Exception {
        return loginService.validateKeyPair(token);
    }

}

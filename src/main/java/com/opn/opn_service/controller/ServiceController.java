package com.opn.opn_service.controller;


import com.opn.opn_service.bo.NeshaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    NeshaService neshaService;

    @GetMapping("/")
    public String home() {
        return "Root Home";
    }

    @GetMapping("/ask/nesha")
    public String askNesha() {

        return neshaService.getMessageFromNesha();
    }

}

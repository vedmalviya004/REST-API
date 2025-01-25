package com.vdnt.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Healty_CIntroller {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }
}

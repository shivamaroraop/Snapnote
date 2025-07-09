package com.shivam.firstproject.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthcheck {
@GetMapping("Healthcheck")
    public String hcheck(){
        return "Ok";
    }
}

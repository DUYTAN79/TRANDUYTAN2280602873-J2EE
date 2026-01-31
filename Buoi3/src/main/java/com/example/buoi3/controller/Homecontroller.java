package com.example.buoi3.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class Homecontroller {
    @GetMapping("/home")
    public String Index(){
        return "books";
    }
}

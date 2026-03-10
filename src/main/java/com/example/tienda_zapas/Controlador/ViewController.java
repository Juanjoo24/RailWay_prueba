package com.example.tienda_zapas.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index"; 
    }

    @GetMapping("/login")
    public String login() {
        return "HTML/login"; 
    }

    @GetMapping("/register")
    public String register() {
        return "HTML/register"; 
    }
}
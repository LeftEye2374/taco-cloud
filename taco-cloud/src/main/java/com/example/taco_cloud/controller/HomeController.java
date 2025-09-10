package com.example.taco_cloud.controller;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}

package com.shubh.kafkachat.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class gptcontroller {

    @GetMapping("/gpt")
    public String summarize(){

        String model="gpt-3.5-turbo";
        String api_url="https://api.openai.com/v1/chat/completions";
        String api_key= "";

        return "<h1>Hello</h1>";
    }
}


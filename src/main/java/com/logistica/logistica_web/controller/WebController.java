/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;


import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author BEATRICE
 */
@Controller
public class WebController {
    private final String API = "http://localhost:8080/api";
    private RestTemplate rest = new RestTemplate();

    @GetMapping("/") public String login(){ return "login"; }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String senha, Model m){
        Map user = rest.postForObject(API+"/auth/login", Map.of("email",email,"senha",senha), Map.class);
        m.addAttribute("user", user);
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard") public String dash(Model m){
        Map counts = rest.getForObject(API+"/dashboard", Map.class);
        m.addAttribute("counts", counts);
        return "dashboard";
    }

    @GetMapping("/criar") public String criar(){ return "criar"; }
    
    @GetMapping("/rastrear") public String rastrear(){ return "rastrear"; }
}



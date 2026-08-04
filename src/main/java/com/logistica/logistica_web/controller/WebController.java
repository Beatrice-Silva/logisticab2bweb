/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.service.ApiService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author BEATRICE
 */
@Controller
public class WebController {
    
    @Autowired
    private ApiService apiService;
   
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
    String token = (String) session.getAttribute("token");
    if(token == null) return "redirect:/login";

    //Map<String, Long> counts = apiService.getCounts(token);
   // model.addAttribute("counts", counts);

    //join
    //model.addAttribute("porLoja", apiService.contarPorLoja(token));

    return "dashboard";
} 
}

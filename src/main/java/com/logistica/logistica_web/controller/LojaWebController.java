/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author BEATRICE
 */
@Controller
public class LojaWebController {
    
    @Autowired
    private ApiService apiServ;

    @GetMapping
    public String listar(HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        
        if(token==null)
        return "criarLoja";    
        model.addAttribute("lojas", apiServ.listarLojas(token));
        return "lojas";
        
    }
    
    @GetMapping("/nova")
    public String nova(HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        
        if(session.getAttribute("token") == null)
            return "redirect:/login";
            model.addAttribute("lojaDTO", new LojaDTO());
        
    
            return "criarLoja";   
        
    }
    
  
    
}

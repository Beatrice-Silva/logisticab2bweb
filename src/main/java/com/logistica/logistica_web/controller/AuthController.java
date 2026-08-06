/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.model.UserRequestDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
import com.logistica.logistica_web.service.ApiService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


/**
 *
 * @author Aluno
 */
@Controller
public class AuthController {
    
    @Autowired
     private ApiService apiService;
  
    @GetMapping("/") 
    public String home(){
        return "index"; 
    }
    
    @GetMapping("/login")
    public String login(
            Model model){
        model.addAttribute("credenciais", new UserRequestDTO());
    return "login";
    }
    
    @PostMapping("/logar")
    public String logar(@ModelAttribute UserRequestDTO cred, HttpSession session){
        try{
            String token = apiService.login(cred);
            session.setAttribute("token", token);
            return "redirect:/dashboard";
        }catch(Exception e){
            e.printStackTrace();
            return "redirect:/login?erro";
        }
    }
    
    @GetMapping("/registrar"
            + "") 
    public String cadastrar(Model model){
        model.addAttribute("usuario",new UsuarioDTO());
        return "cadastro"; 
    }
    
    @PostMapping("/registrar") 
    public String mandarRegistro(@ModelAttribute UsuarioDTO usuario){        
        apiService.registrar(usuario);
        return "redirect:/login"; 
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        if(token == null) return "redirect:/login";
        model.addAttribute("counts", apiService.getCounts(token));
        model.addAttribute("porLoja", apiService.contarPorLoja(token));
        return "dashboard";
    }

}

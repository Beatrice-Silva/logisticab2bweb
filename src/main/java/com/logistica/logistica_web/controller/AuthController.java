/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.service.AuthRestClientService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import static jdk.javadoc.doclet.DocletEnvironment.ModuleMode.API;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aluno
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    AuthRestClientService rest;
  
    @GetMapping("/") 
    public String home(
            HttpSession session
            ){
        
        Object token = session.getAttribute("token");
        
        if(token == null){
            return "index";
        }
        
        return "index"; 
    }
    
    @PostMapping("/login")
    public String logar(@RequestParam String email, @RequestParam String senha, Model m){
        /*
        Map user = rest.postForObject(API+"/auth/login", Map.of("email",email,"senha",senha), Map.class);
        m.addAttribute("user", user);
*/
    return "redirect:/dashboard";
    }
  
    @GetMapping("/cadastrar") 
    public String cadastrar(){
        
        return "cadastro"; 
    }
    
    @GetMapping("/criar/pacote") 
    public String criarPacote(){
        
        return "criar"; 
    }
    
    
    
    @GetMapping("/criar/loja") 
    public String criarLoja(){
        
        return "criar";
    }
    

    @GetMapping("/dashboard") 
    public String mapear(Model m){
        //Map counts = rest.getForObject(API+"/dashboard", Map.class);
        //m.addAttribute("counts", counts);
        return "dashboard";
    }

    

}

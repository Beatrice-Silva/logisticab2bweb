/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.model.UserRequestDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
import com.logistica.logistica_web.service.AuthRestClientService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import static jdk.javadoc.doclet.DocletEnvironment.ModuleMode.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aluno
 */
@Controller
public class AuthController {
    
    @Autowired
    private AuthRestClientService restService;
  
    @GetMapping("/") 
    public String home(
            HttpSession session
            ){
        return "index"; 
    }
    
    @GetMapping("/login")
    public String login(
            Model model){
        UserRequestDTO credenciais = new UserRequestDTO();
        model.addAttribute("credenciais", credenciais);
    return "/login";
    }
    
    
    @PostMapping("/logar")
    public String logar(
            @ModelAttribute UserRequestDTO credenciais,
            HttpSession session){
        
        String token = restService.login(credenciais);
        System.out.println("token" + token);
        session.setAttribute("token", token);
        
    return "redirect:/";
    }
    
    @GetMapping("/cadastrar") 
    public String cadastrar(){
        
        return "cadastro"; 
    }
    
    @GetMapping("/listar/pacote") 
    public List<PacoteDTO> listarPacote(PacoteDTO pacote){
        List<PacoteDTO> pacotes = listarPacote(pacote);
        
        return listarPacote(pacote); 
    }
    
    @GetMapping("/criar/pacote") 
    public String criarPacote(){
        
        return "criar"; 
    }
    
    @GetMapping("/listar/loja") 
    public List<LojaDTO> listarLoja(LojaDTO loja){ 
        return listarLoja(loja); 
    }
    
    @GetMapping("/criar/loja") 
    public String criarLoja(){ 
        return "criar";
    }
    
    @GetMapping("/listar/loja") 
    public List<UsuarioDTO> listarEntregador(UsuarioDTO entregador){
        return listarEntregador(entregador); 
    }
    
    @GetMapping("/dashboard") 
    public String mapear(Model m){
        return "dashboard";
    }

}

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author BEATRICE
 */
@Controller
@RequestMapping("/lojas")
public class LojaWebController {

    @Autowired
    private ApiService apiServ;
    
    private boolean temPerfil(HttpSession session, String... perfis){
    String p = (String) session.getAttribute("perfil");
    if(p == null) return false;
    for(String perfil : perfis){
        if(p.equalsIgnoreCase(perfil)) return true;
    }
    return false;
}
    
    @PostMapping("/editar/{id}")
    public String editarSalvar(@PathVariable Long id, @ModelAttribute LojaDTO dto, HttpSession session){
    String token = (String) session.getAttribute("token");
    if(!temPerfil(session, "ADMIN","OPERADOR")) return "redirect:/lojas";
    dto.setIdLoja(id);
    apiServ.atualizarLoja(dto, token); // cria esse método igual ao criarLoja mas com PUT
    return "redirect:/lojas";
    }
    @GetMapping
    public String listar(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";
        model.addAttribute("lojas", apiServ.listarLojas(token));
        return "lojas";
    }
    

   @GetMapping("/novo")
public String nova(HttpSession session, Model model) {
    if(!temPerfil(session, "ADMIN","OPERADOR")) 
        return "redirect:/dashboard?erro=sem_permissao";
    
    String token = (String) session.getAttribute("token");
    if (token == null) return "redirect:/login";

    // GET só cria o objeto vazio e manda pra tela
    model.addAttribute("lojaDTO", new LojaDTO());
    return "criarLoja";
}

@PostMapping("/nova")
public String salvar(@ModelAttribute LojaDTO dto, HttpSession session, Model model) {
    String token = (String) session.getAttribute("token"); 
    if (token == null) return "redirect:/login";

    try {
        apiServ.criarLoja(dto, token);
        return "redirect:/lojas";
    } catch (Exception ex) {
        if (ex.getMessage().contains("cnpj") || ex.getMessage().contains("Duplicate")) {
            model.addAttribute("erro", "CNPJ já cadastrado: " + dto.getCnpj());
            model.addAttribute("lojaDTO", dto); // devolve o que digitou
            return "criarLoja";
        }
        throw ex;
    }
}
    
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, HttpSession session, Model model){
    if(!temPerfil(session, "ADMIN","OPERADOR")) return "redirect:/lojas?erro=sem_permissao";
    String token = (String) session.getAttribute("token");
    if(token == null) return "redirect:/login";
    // busca nas ativas E inativas
    LojaDTO loja = apiServ.listarLojas(token).stream()
        .filter(l -> l.getIdLoja().equals(id)).findFirst().orElse(null);
    model.addAttribute("lojaDTO", loja);
    return "editarLoja";
}
    
    
    /*
    @GetMapping("/{id}")
    public String editarForm(@PathVariable Long id, HttpSession session, Model model){
    String perfil = (String) session.getAttribute("perfil");
    if(!perfil.equals("OPERADOR") && !perfil.equals("ADMIN")){
        return "redirect:/lojas?erro=sem_permissao";
    }
    String token = (String) session.getAttribute("token");
    LojaDTO loja = apiServ.listarAtivas(token).stream()
        .filter(l -> l.getIdLoja().equals(id))
        .findFirst().orElse(null);
    model.addAttribute("lojaDTO", loja);
    return "editarLoja";
    }


    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long idLoja, HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        LojaDTO loja = apiServ.listarLojas(token).stream()
                .filter(l -> l.getIdLoja().equals(idLoja))
                .findFirst().orElse(null);

        model.addAttribute("lojaDTO", loja);
        return "editarLoja";
    }
    */


    @PostMapping("/{id}/arquivar")
    public String arquivar(@PathVariable Long id, HttpSession session){
        String token = (String) session.getAttribute("token");
        apiServ.arquivarLoja(id, token);
        return "redirect:/lojas";
    }
}
    

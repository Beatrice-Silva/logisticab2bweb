/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;


import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.model.PacoteDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author BEATRICE
 */
@Controller
@RequestMapping("/pacotes")
public class PacoteController {
    
    @Autowired
    private ApiService apiService;
    
    @GetMapping
    public String listar(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";
        model.addAttribute("pacotes", apiService.listarPacote(token));
        return "pacotes";
    }
    
@GetMapping("/novo")
    public String novoForm(Model model, HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        model.addAttribute("pacoteDTO", new PacoteDTO());
        model.addAttribute("lojas", apiService.listarAtivas(token)); // AQUI BUSCA AS LOJAS
        return "criarpacote";
    }
    
    @PostMapping("/novo")
    public String criar(@RequestParam Long idLoja,
                    @RequestParam String enderecoDestino,
                    HttpSession session) {
    String token = (String) session.getAttribute("token");
    
    
    LojaDTO lojaRef = new LojaDTO();
    lojaRef.setIdLoja(idLoja);

    PacoteDTO dto = new PacoteDTO();
    dto.setLoja(lojaRef);
    dto.setEnderecoDestino(enderecoDestino);
    dto.setDescObserv("Criado via WEB");

    apiService.criarPacote(dto, token);
    return "redirect:/pacotes";
}
      @PostMapping("/rastrear")
    public String rastrear(@RequestParam String codigo, Model model){
    System.out.println("BUSCANDO CODIGO: " + codigo);
    try {
        PacoteDTO p = apiService.rastrear(codigo.trim());
        model.addAttribute("pacote", p);
        return "verificacao";
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("erro", "Pacote não encontrado: " + codigo + " | Erro API: " + e.getMessage());
        return "rastrearServico";
    }
}
    @GetMapping("/rastrear")
    public String rastrearPage(){
        return "rastrearServico"; 
    }
/*
    @PostMapping("/rastrear")
    public String rastrear(@RequestParam String codigo, Model model){
        try {
            model.addAttribute("pacote", apiService.rastrear(codigo));
            return "verificacao";
        } catch (Exception e) {
            model.addAttribute("erro", "Pacote não encontrado: " + codigo);
            return "rastrearServico";
        }
    }
*/
    @GetMapping("/loja/{id}")
    public String pacotesDaLoja(@PathVariable Long id, HttpSession session, Model model) {
    String token = (String) session.getAttribute("token");
    if (token == null) return "redirect:/login";

    model.addAttribute("pacotes", apiService.listarPacotesPorLoja(id, token));
    model.addAttribute("lojaId", id);
    return "pacotes"; 
}

    @PostMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Long id, 
            @RequestParam String novoStatus,                                         
            @RequestParam(required = false) String otp, 
            HttpSession session) {
        String token = (String)session.getAttribute("token");
        apiService.atualizarStatus(id, novoStatus, otp, token);
        return "redirect:/pacotes";
    }

}



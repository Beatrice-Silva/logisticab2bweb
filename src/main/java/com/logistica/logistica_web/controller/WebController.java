/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;


import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.service.ApiService;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author BEATRICE
 */
@Controller
@RequestMapping("/pacotes")
public class WebController {// pacotes
    
    @Autowired
    private ApiService apiService;

    // REMOVIDO qualquer método de login daqui - login fica só no AuthController

    @GetMapping
    public String listar(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/auth/login";
        model.addAttribute("pacotes", apiService.listarPacote(token));
        return "pacotes/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model, HttpSession session) {
        if (session.getAttribute("token") == null) return "redirect:/auth/login";
        model.addAttribute("pacoteDTO", new PacoteDTO());
        return "pacotes/form";
    }

    @PostMapping("/novo")
    public String criar(@ModelAttribute PacoteDTO pacoteDTO, HttpSession session) {
        String token = (String) session.getAttribute("token");
        apiService.criarPacote(pacoteDTO, token);
        return "redirect:/pacotes";
    }

    @GetMapping("/rastrear")
    public String rastrearPage() { return "pacotes/rastrear"; }

    @PostMapping("/rastrear")
    public String rastrear(@RequestParam String codigo, Model model) {
        try {
            model.addAttribute("pacote", apiService.rastrear(codigo));
            return "pacotes/detalhe";
        } catch (Exception e) {
            model.addAttribute("erro", "Pacote não encontrado: " + codigo);
            return "pacotes/rastrear";
        }
    }

    @PostMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus,
                                  @RequestParam(required = false) String otp, HttpSession session) {
        String token = (String) session.getAttribute("token");
        apiService.atualizarStatus(id, novoStatus, otp, token);
        return "redirect:/pacotes";
    }
}



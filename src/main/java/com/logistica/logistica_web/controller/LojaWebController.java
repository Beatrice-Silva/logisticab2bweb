/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.service.ApiService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
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

    @GetMapping
    public String listar(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        model.addAttribute("lojas", apiServ.listarLojas(token));
        return "lojas";
    }

    @GetMapping("/nova")
    public String nova(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        model.addAttribute("lojaDTO", new LojaDTO());
        return "criarLoja";
    }

    @PostMapping("/nova")
    public String salvar(@ModelAttribute LojaDTO dto, HttpSession session, Model model) {
        String token = (String) session.getAttribute("token"); // FALTAVA ISSO, por isso token não inicializado
        if (token == null) return "redirect:/login";

        try {
            apiServ.criarLoja(dto, token);
            return "redirect:/lojas";
        } catch (Exception ex) {
            if (ex.getMessage().contains("cnpj") || ex.getMessage().contains("Duplicate")) {
                model.addAttribute("erro", "CNPJ já cadastrado: " + dto.getCnpj());
                return "criarLoja";
            }
            throw ex;
        }
    }

    @GetMapping("/editar/{idLoja}")
    public String editarForm(@PathVariable Long idLoja, HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        LojaDTO loja = apiServ.listarLojas(token).stream()
                .filter(l -> l.getIdLoja().equals(idLoja))
                .findFirst().orElse(null);

        model.addAttribute("lojaDTO", loja);
        return "editarLoja";
    }
    
    @GetMapping("/loja/{id}")
    public String listarPacotesPorLoja(@PathVariable Long idLoja, HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        
        apiServ.listarPacotesPorLoja(token);
        
        model.addAttribute("lojaDTO", idLoja);
        return "lojas"; 
    }


    @PostMapping("/{id}/arquivar")
    public String arquivar(@PathVariable Long id, HttpSession session){
        String token = (String) session.getAttribute("token");
        apiServ.arquivarLoja(id, token);
        return "redirect:/lojas";
    }
}
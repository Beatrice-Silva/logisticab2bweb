/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
import com.logistica.logistica_web.service.ApiService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author BEATRICE
 */@Controller
public class UsuarioWebController {
    @Autowired private ApiService apiService;

     @GetMapping("/usuarios")
    public String gestao(HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        String perfil = (String) session.getAttribute("perfil");
        if(token == null) return "redirect:/login";
        if("ENTREGADOR".equalsIgnoreCase(perfil)) return "redirect:/pacotes"; // ENTREGADOR NÃO ACESSA

        List<UsuarioDTO> usuarios = apiService.listarUsuarios(token);
        List<PacoteDTO> pacotes = apiService.listarPacote(token);
        List<PacoteDTO> disponiveis = pacotes.stream()
            .filter(p -> "CRIADO".equalsIgnoreCase(p.getStatusAtual()))
            .toList();

        List<Map<String,Object>> admins = new ArrayList<>();
        List<Map<String,Object>> operadores = new ArrayList<>();
        List<Map<String,Object>> entregadores = new ArrayList<>();

        for(UsuarioDTO u : usuarios){
            Map<String,Object> map = new HashMap<>();
            map.put("id", u.getId()); map.put("nome", u.getNome());
            map.put("email", u.getEmail());
            if("ADMIN".equalsIgnoreCase(u.getPerfilRole())) admins.add(map);
            else if("OPERADOR".equalsIgnoreCase(u.getPerfilRole())){
                map.put("totalLojas", apiService.contarLojasPorUsuario(u.getId(), token));
                operadores.add(map);
            } else if("ENTREGADOR".equalsIgnoreCase(u.getPerfilRole())){
                PacoteDTO atual = pacotes.stream()
                    .filter(p -> p.getIdEntregador() != null && p.getIdEntregador().equals(u.getId()) 
                        && ("COLETADO".equalsIgnoreCase(p.getStatusAtual()) || "EM_TRANSITO".equalsIgnoreCase(p.getStatusAtual())))
                    .findFirst().orElse(null);
                map.put("pacoteAtual", atual);
                entregadores.add(map);
            }
        }
        model.addAttribute("admins", admins);
        model.addAttribute("operadores", operadores);
        model.addAttribute("entregadores", entregadores);
        model.addAttribute("pacotesDisponiveis", disponiveis);
        return "gestao"; // seu arquivo gestao.html
    }

    @PostMapping("/usuarios/atribuir")
    public String atribuir(@RequestParam Long idPacote, @RequestParam Long idEntregador, HttpSession session, RedirectAttributes ra){
        String token = (String) session.getAttribute("token");
        try{
            apiService.atribuirPacote(idPacote, idEntregador, token);
            ra.addFlashAttribute("sucesso","Pacote atribuído!");
        }catch(Exception e){ ra.addFlashAttribute("erro", e.getMessage()); }
        return "redirect:/usuarios";
    }
}

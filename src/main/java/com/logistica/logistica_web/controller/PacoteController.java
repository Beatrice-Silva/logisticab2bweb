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
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author BEATRICE
 */

@Controller
@RequestMapping("/pacotes")
public class PacoteController {
    
    @Autowired private ApiService apiService;
    
 
    private boolean temPerfil(HttpSession session, String... perfis){
        String p = (String) session.getAttribute("perfil");
        if(p == null) return false;
        for(String perfil : perfis){
            if(p.equalsIgnoreCase(perfil)) return true;
        }
        return false;
    }
     @GetMapping
public String listar(@RequestParam(required=false) String status, HttpSession session, Model model) {
    String token = (String) session.getAttribute("token");
    if (token == null) return "redirect:/login";
    List<PacoteDTO> pacotes = apiService.listarPacote(token);
    
    
    if(status != null && !status.isBlank()){
        pacotes = pacotes.stream()
            .filter(p -> status.equalsIgnoreCase(p.getStatusAtual()))
            .toList();
    }

    
    List<String> ordem = List.of("CRIADO","COLETADO","EM_TRANSITO","ENTREGUE","DEVOLVIDO","ARQUIVADO");
    pacotes.sort((a,b) -> {
        int iA = ordem.indexOf(a.getStatusAtual());
        int iB = ordem.indexOf(b.getStatusAtual());
        if(iA != iB) return Integer.compare(iA, iB); 
        return Long.compare(b.getId(), a.getId());  
    });
    model.addAttribute("pacotes", pacotes);
    model.addAttribute("statusFiltro", status);
    
    if(temPerfil(session, "ENTREGADOR")){
        Long meuId = (Long) session.getAttribute("idUsuario");
        boolean jaTemEmTransito = pacotes.stream()
            .anyMatch(p -> meuId != null && meuId.equals(p.getIdEntregador()) && 
                ("EM_TRANSITO".equals(p.getStatusAtual()) || "COLETADO".equals(p.getStatusAtual())));
        model.addAttribute("jaTemEmTransito", jaTemEmTransito);
        model.addAttribute("pacoteAtualId", session.getAttribute("pacoteAtualId"));
    }
    return "pacotes";
}

    @PostMapping("/{id}/pegar")
    public String pegarPacote(@PathVariable Long id, HttpSession session){
        if(!temPerfil(session, "ENTREGADOR")) return "redirect:/pacotes";
        String token = (String) session.getAttribute("token");
        if(session.getAttribute("pacoteAtualId") != null) return "redirect:/pacotes?erro=ja_tem_pacote";
        apiService.atualizarStatus(id, "COLETADO", null, token);
        session.setAttribute("pacoteAtualId", id);
        return "redirect:/pacotes";
    }
    
    @GetMapping("/editar/{id}")
    public String editarPacoteForm(@PathVariable Long id, HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        if(token == null) return "redirect:/login";
        PacoteDTO pacote = apiService.listarPacote(token).stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("pacoteDTO", pacote);
        model.addAttribute("lojas", apiService.listarAtivas(token));
        return "editarPacote";
    }
    
    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @RequestParam String novoStatus, @RequestParam(required=false) String otp, HttpSession session, RedirectAttributes ra){
        String token = (String) session.getAttribute("token");
        try{
            apiService.atualizarStatus(id, novoStatus, otp, token);
            if("ENTREGUE".equals(novoStatus)){
                session.removeAttribute("pacoteAtualId");
            }
            ra.addFlashAttribute("sucesso","Status alterado para " + novoStatus);
            return "redirect:/pacotes";
        }catch(Exception e){
            ra.addFlashAttribute("erro", e.getMessage());
            return "redirect:/pacotes/editar/"+id;
        }
    }

    @GetMapping("/novo")
    public String novoForm(Model model, HttpSession session) {
        if(!temPerfil(session, "OPERADOR","ADMIN")) return "redirect:/pacotes";
        String token = (String) session.getAttribute("token");
        model.addAttribute("pacoteDTO", new PacoteDTO());
        model.addAttribute("lojas", apiService.listarAtivas(token)); 
        return "criarpacote";
    }

    @PostMapping("/novo")
    public String criar(@RequestParam Long idLoja, @RequestParam String enderecoDestino, @RequestParam String emailDestinatario, HttpSession session) {
        String token = (String) session.getAttribute("token");
        LojaDTO lojaRef = new LojaDTO(); lojaRef.setIdLoja(idLoja);
        PacoteDTO dto = new PacoteDTO();
        dto.setLoja(lojaRef); dto.setEnderecoDestino(enderecoDestino);
        dto.setEmailDestinatario(emailDestinatario); dto.setDescObserv("Criado via WEB");
        apiService.criarPacote(dto, token);
        return "redirect:/pacotes"; 
    }
    
    @GetMapping("/rastrear")
    public String rastrearPage(){ return "rastrearServico"; }

    @PostMapping("/rastrear")
    public String rastrear(@RequestParam String codigo, Model model){
        try {
            PacoteDTO p = apiService.rastrear(codigo.trim());
            model.addAttribute("pacote", p);
            return "verificacao";
        } catch (Exception e) {
            model.addAttribute("erro", "Pacote não encontrado: " + codigo);
            return "rastrearServico";
        }
    }

    @GetMapping("/verificacao")
    public String verificacaoPage(){ return "verificacao"; } 

    @GetMapping("/{id}/entregar")
    public String entregarPage(@PathVariable Long id, HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        if(token == null) return "redirect:/login";
        PacoteDTO p = apiService.listarPacote(token).stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("pacote", p);
        return "entregarComOtp"; 
    }
    
    @PostMapping("/{id}/entregar")
    public String entregar(@PathVariable Long id, @RequestParam String otp, HttpSession session, RedirectAttributes redirect){
        String token = (String) session.getAttribute("token");
        try{
            apiService.atualizarStatus(id, "ENTREGUE", otp, token);
            session.removeAttribute("pacoteAtualId");
            redirect.addFlashAttribute("sucesso", "Entrega confirmada! Livre para próximo.");
        }catch(Exception e){
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/pacotes/" + id + "/entregar";
        }
        return "redirect:/pacotes";
    }

    @PostMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus, @RequestParam(required=false) String otp, HttpSession session) {
        String token = (String)session.getAttribute("token");
        apiService.atualizarStatus(id, novoStatus, otp, token);
        if("ENTREGUE".equals(novoStatus)) session.removeAttribute("pacoteAtualId");
        return "redirect:/pacotes";
    }
}
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author BEATRICE
 */
@Controller
@RequestMapping("/pacotes")
public class PacoteController {
    
    @Autowired
    
    private ApiService apiService;
    private boolean temPerfil(HttpSession session, String... perfis){
    String p = (String) session.getAttribute("perfil");
    if(p == null) return false;
    for(String perfil : perfis){
        if(p.equalsIgnoreCase(perfil)) return true;
    }
    return false;
    }
    /*
    @GetMapping
    public String listar(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";
        model.addAttribute("pacotes", apiService.listarPacote(token));
        return "pacotes";
    }*/
    
    @GetMapping
    public String listar(HttpSession session, Model model) {
    String token = (String) session.getAttribute("token");
    if (token == null) return "redirect:/login";
    
    List<PacoteDTO> pacotes = apiService.listarPacote(token);
    model.addAttribute("pacotes", pacotes);
    
    
    String perfil = (String) session.getAttribute("perfil");
    if("ENTREGADOR".equalsIgnoreCase(perfil)){
        boolean jaTemEmTransito = pacotes.stream()
            .anyMatch(p -> "EM_TRANSITO".equals(p.getStatusAtual()) || "COLETADO".equals(p.getStatusAtual()));
        
        Long pacoteAtualId = (Long) session.getAttribute("pacoteAtualId");
        model.addAttribute("jaTemEmTransito", jaTemEmTransito);
        model.addAttribute("pacoteAtualId", pacoteAtualId);
    }
    return "pacotes";
}

    @PostMapping("/{id}/pegar")
    public String pegarPacote(@PathVariable Long id, HttpSession session){
    if(!temPerfil(session, "ENTREGADOR")) return "redirect:/pacotes";
    String token = (String) session.getAttribute("token");
    
    if(session.getAttribute("pacoteAtualId") != null) {
        return "redirect:/pacotes?erro=ja_tem_pacote";
    }
    apiService.atualizarStatus(id, "COLETADO", null, token);
    session.setAttribute("pacoteAtualId", id);
    return "redirect:/pacotes";
}
    
    @GetMapping("/novo")
    public String novoForm(Model model, HttpSession session) {
        
        if(!temPerfil(session, "OPERADOR","ADMIN")) return "redirect:/pacotes?erro=sem_permissao";
        String token = (String) session.getAttribute("token");
        if (token == null) return "redirect:/login";

        model.addAttribute("pacoteDTO", new PacoteDTO());
        model.addAttribute("lojas", apiService.listarAtivas(token)); 
        return "criarpacote";
    }
    
    @GetMapping("/editar/{id}")
    public String editarPacoteForm(@PathVariable Long id, HttpSession session, Model model){
    
    String token = (String) session.getAttribute("token");
    if(token == null) return "redirect:/login";
    
    PacoteDTO pacote = apiService.listarPacote(token).stream()
        .filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    model.addAttribute("pacoteDTO", pacote);
    model.addAttribute("lojas", apiService.listarAtivas(token));
    
    model.addAttribute("todosStatus", List.of("CRIADO","COLETADO","EM_TRANSITO","ENTREGUE","DEVOLVIDO","ARQUIVADO"));
    return "editarPacote";
    }
    
    @PostMapping("/editar/{id}")
    public String editarSalvar(@PathVariable Long id, @ModelAttribute PacoteDTO dto, 
                           @RequestParam String novoStatus,
                           HttpSession session){
    String token = (String) session.getAttribute("token");
    
    apiService.atualizarPacote(id, dto, token);
    
    if(novoStatus != null && !novoStatus.isBlank()){
        apiService.atualizarStatus(id, novoStatus, null, token);
    }
    return "redirect:/pacotes";
}
    
    
    @PostMapping("/novo")
    public String criar(@RequestParam Long idLoja,
                @RequestParam String enderecoDestino,
                @RequestParam String emailDestinatario, 
                HttpSession session) {
    String token = (String) session.getAttribute("token");
    LojaDTO lojaRef = new LojaDTO();
    lojaRef.setIdLoja(idLoja);
    PacoteDTO dto = new PacoteDTO();
    dto.setLoja(lojaRef);
    dto.setEnderecoDestino(enderecoDestino);
    dto.setEmailDestinatario(emailDestinatario);
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
*/ @GetMapping("/{id}/entregar")
    public String entregarPage(@PathVariable Long id, HttpSession session, Model model){
        String token = (String) session.getAttribute("token");
        if(token == null) return "redirect:/login";
        model.addAttribute("pacoteId", id);
        return "pacotes/entregar";
    }
    
    @GetMapping("/loja/{id}")
    public String pacotesDaLoja(@PathVariable Long id, HttpSession session, Model model) {
    String token = (String) session.getAttribute("token");
    if (token == null) return "redirect:/login";

    model.addAttribute("pacotes", apiService.listarPacotesPorLoja(id, token));
    model.addAttribute("lojaId", id);
    return "pacotes"; 
}
    @PostMapping("/{id}/entregar")
    public String entregar(@PathVariable Long id, @RequestParam String otp, HttpSession session, RedirectAttributes redirect){
        String token = (String) session.getAttribute("token");
        try{
            apiService.atualizarStatus(id, "ENTREGUE", otp, token);
            redirect.addFlashAttribute("sucesso", "Entrega confirmada!");
        }catch(Exception e){
            redirect.addFlashAttribute("erro", e.getMessage());
            return "redirect:/pacotes/" + id + "/entregar";
        }
        return "redirect:/dashboard";
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



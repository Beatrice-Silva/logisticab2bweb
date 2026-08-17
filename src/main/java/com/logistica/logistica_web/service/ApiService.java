/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.service;


import com.logistica.logistica_web.model.LojaCountDTO;
import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.model.StatusHistoricoDTO;
import com.logistica.logistica_web.model.UserRequestDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Aluno
 */
@Service
public class ApiService {
    
    private final RestTemplate rest = new RestTemplate();
    private final String BASE = "http://localhost:8000";
    
    private HttpHeaders headers(String token){
        HttpHeaders h = new HttpHeaders();
        if(token!=null) h.setBearerAuth(token.replace("Bearer","").trim());
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }
    /*
    public String login(UserRequestDTO cred){
        ResponseEntity<String> res = rest.postForEntity(BASE + "/api/auth/logar", cred, String.class);
        return res.getBody();
    }*/
   
    
    
    
    public String login(UserRequestDTO cred){
    try{
        // Tenta pegar como Map primeiro
        ResponseEntity<Map> res = rest.postForEntity(BASE + "/api/auth/logar", cred, Map.class);
        Object t = res.getBody().get("token");
        if(t == null) t = res.getBody().get("accessToken");
        if(t != null) return t.toString().replace("\"","").trim();
        
        // Se não for Map, pega String pura
        ResponseEntity<String> res2 = rest.postForEntity(BASE + "/api/auth/logar", cred, String.class);
        String body = res2.getBody();
        // limpa aspas e Bearer
        return body.replace("\"","").replace("Bearer","").trim();
    }catch(Exception e){
        throw new RuntimeException("Login falhou: " + e.getMessage());
    }


    
    }
    
    public String registrar(UsuarioDTO user){
        return rest.postForEntity(BASE + "/api/auth/registrar", user, String.class).getBody();
    }
    
  

    public UsuarioDTO me(String token){
        return rest.exchange(BASE+"/api/auth/me", HttpMethod.GET,
                new HttpEntity<Void>(headers(token)), UsuarioDTO.class).getBody();
    }
    
    public void criarPacote(PacoteDTO dto, String token){
    Map<String, Object> lojaMap = Map.of("id", dto.getLoja().getIdLoja());
    Map<String, Object> body = new HashMap<>();
    body.put("loja", lojaMap);
    body.put("enderecoDestino", dto.getEnderecoDestino());
    body.put("emailDestinatario", dto.getEmailDestinatario()!= null? dto.getEmailDestinatario() : "cliente@teste.com");
    body.put("descObserv", dto.getDescObserv());

    HttpEntity<Map<String,Object>> entity = new HttpEntity<>(body, headers(token));
    rest.postForEntity(BASE + "/api/pacotes/registrar", entity, String.class);
}
    
    public List<PacoteDTO> listarPacote(String token){
        var entity = new HttpEntity<Void>(headers(token));
        var res = rest.exchange(BASE + "/api/pacotes/listar", HttpMethod.GET, entity, 
                new ParameterizedTypeReference<List<PacoteDTO>>(){});
        return res.getBody();

}
    public PacoteDTO rastrear(String codigo){
        try{
        
         return rest.getForObject(BASE + "/api/pacotes/" + codigo, PacoteDTO.class);
        }catch(Exception e1){
        try{
            
            return rest.getForObject(BASE + "/api/pacotes/rastrear/" + codigo, PacoteDTO.class);
        } catch(Exception e2){
            
            return rest.getForObject(BASE + "/api/pacotes/codigo/" + codigo, PacoteDTO.class);
        }
    }
}
    
    public PacoteDTO atualizarStatus(Long id, String novoStatus, String otp, String token){
        String url = BASE + "/api/pacotes/" + id + "/status?novoStatus=" + novoStatus;
        if(otp != null && !otp.isBlank()) url += "&otp=" + otp;
        return rest.exchange(url, HttpMethod.PUT, new HttpEntity<Void>(headers(token)), PacoteDTO.class).getBody();
    }
    
    public Map<String,Long> getCounts(String token){
        var res = rest.exchange(BASE + "/api/pacotes/estatisticas", HttpMethod.GET, 
                new HttpEntity<Void>(headers(token)), new ParameterizedTypeReference<Map<String,Long>>(){});
        return res.getBody();
    }
    
    public List<LojaCountDTO> contarPorLoja(String token){
        var res = rest.exchange(BASE + "/api/pacotes/por-loja", HttpMethod.GET, 
                new HttpEntity<Void>(headers(token)), new ParameterizedTypeReference<List<LojaCountDTO>>(){});
        return res.getBody();
    }
 
    public List<LojaDTO> listarLojas(String token){
    HttpHeaders h = new HttpHeaders();
    h.set("Authorization", "Bearer " + token);
    HttpEntity<Void> entity = new HttpEntity<>(h);
    ResponseEntity<LojaDTO[]> resp = rest.exchange(BASE + "/api/lojas", HttpMethod.GET, entity, LojaDTO[].class);
    System.out.println("LOJAS RETORNADAS: " + resp.getBody().length);
    return Arrays.asList(resp.getBody());
}
    
    public List<LojaDTO> listarAtivas(String token){ 
        return rest.exchange(BASE + "/api/lojas/ativas", HttpMethod.GET, 
                new HttpEntity<Void>(headers(token)), new ParameterizedTypeReference<List<LojaDTO>>(){}).getBody(); 
    }
    
    public void criarLoja(LojaDTO dto, String token){ 
        rest.postForObject(BASE + "/api/lojas", new HttpEntity<>(dto, headers(token)), LojaDTO.class); 
    }
    
    public void arquivarLoja(Long id, String token){ 
        rest.exchange(BASE + "/api/lojas/" + id + "/arquivar", HttpMethod.PUT, 
                new HttpEntity<Void>(headers(token)), Void.class); 
    }   
    
    public List<PacoteDTO> listarPacotesPorLoja(Long idLoja, String token){
        return rest.exchange(BASE + "/api/pacotes/loja/" + idLoja, HttpMethod.GET, 
                new HttpEntity<Void>(headers(token)), new ParameterizedTypeReference<List<PacoteDTO>>(){})
                .getBody();
    }
    
    public List<StatusHistoricoDTO> listarRecentes(String token){
    try{
        var entity = new HttpEntity<Void>(headers(token));
        var res = rest.exchange(BASE + "/api/pacotes/recentes", HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<StatusHistoricoDTO>>(){});
        return res.getBody();
    }catch(Exception e){ return List.of(); }
}
    
    
}
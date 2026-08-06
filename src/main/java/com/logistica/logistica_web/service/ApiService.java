/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.service;

import com.logistica.logistica_web.model.AuthResponseDTO;
import com.logistica.logistica_web.model.LojaCountDTO;
import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.model.UserRequestDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
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
    

    
    public String login(UserRequestDTO cred){
     ResponseEntity<String> res = rest.postForEntity(BASE + "/api/auth/logar", cred, String.class);
        return res.getBody();
    }
    
    public String registrar(UsuarioDTO user){
        ResponseEntity<String> res = rest.postForEntity(BASE + "api/auth/registrar" , user, String.class);
        return res.getBody();
    }
    
    public List<PacoteDTO> listarPacote(String token){
        HttpHeaders h = new HttpHeaders(); h.setBearerAuth(token);
        var entity = new HttpEntity<Void>(h);
        var res = rest.exchange(BASE + "/api/pacotes", HttpMethod.GET, entity, new ParameterizedTypeReference<List<PacoteDTO>>(){});
        return res.getBody();
    }
    
   
    public void criarPacote(PacoteDTO dto, String token){
        HttpHeaders h = new HttpHeaders(); h.setBearerAuth(token);
        // O BACK ESPERA loja.id
        Map body = Map.of(
            "enderecoDestino", dto.getEnderecoDestino(),
            "descObserv", dto.getObservacao(),
            "loja", Map.of("id", dto.getId_loja())
        );
        var entity = new HttpEntity<>(body, h);
        rest.postForEntity(BASE + "/api/pacotes/registrar", entity, String.class);
    }
    
    
    public PacoteDTO rastrear(String codigo){
        return rest.getForObject(BASE + "/api/pacotes/" + codigo, PacoteDTO.class);
    }
    
      public PacoteDTO atualizarStatus(Long id, String novoStatus, String otp, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        rest.postForObject(BASE + "api/auth/" + id + "/loja", entity, String.class); 
    
        
        String url = BASE + "/api/pacotes/" + id + "/status?novoStatus=" + novoStatus;
        if(otp != null && !otp.isBlank()){
            url += "&otp=" + otp;
        }
        
        ResponseEntity<PacoteDTO> resp = rest.exchange(url, HttpMethod.PUT, entity, PacoteDTO.class);
        return resp.getBody();
      }  

    public List<LojaDTO> listarLojas(String token){
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        
        ResponseEntity<List<LojaDTO>> response = rest.exchange(
            BASE + "/api/loja",
                HttpMethod.GET, 
                entity,
                new ParameterizedTypeReference<List<LojaDTO>>() {}
                );
        return response.getBody();
    }
    
    public List<UsuarioDTO> listarEntregadores(UsuarioDTO user, String token){
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        
        ResponseEntity<List<UsuarioDTO>> response = rest.exchange(
            BASE + "/api/entregadore",
                HttpMethod.GET, 
                entity,
                new ParameterizedTypeReference<List<UsuarioDTO>>() {}
                );
        return response.getBody();
    }
  
    public void criarLoja(LojaDTO loja, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<LojaDTO> entity = new HttpEntity<> (loja, headers);
        rest.postForObject(BASE + "api/auth/cadastrar/loja", entity, AuthResponseDTO.class); 
    }
    
    
    public void registrarPacote(Long lojaId, LojaDTO loja, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<LojaDTO> entity = new HttpEntity<> (loja,headers);
        rest.postForObject(BASE + "api/auth/" + lojaId + "/loja", entity, String.class); 
    }    
    
    
    
    public Map<String,Long> getCounts(String token){
        HttpHeaders h = new HttpHeaders(); h.setBearerAuth(token);
        var entity = new HttpEntity<Void>(h);
        var res = rest.exchange(BASE + "/api/pacotes/estatisticas", HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String,Long>>(){});
        return res.getBody();
    }
    
    public List<LojaCountDTO> contarPorLoja(String token){
        HttpHeaders h = new HttpHeaders(); h.setBearerAuth(token);
        var entity = new HttpEntity<Void>(h);
        var res = rest.exchange(BASE + "/api/pacotes/por-loja", HttpMethod.GET, entity, new ParameterizedTypeReference<List<LojaCountDTO>>(){});
        return res.getBody();
    }
    
  
        
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.logistica.logistica_web.model.AuthResponseDTO;
import com.logistica.logistica_web.model.LojaDTO;
import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.model.UserRequestDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
import java.util.List;
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
    
    private final RestTemplate restTemplate= new RestTemplate();
    private final String BASE_URL = "http://localhost:9000";

    
    public AuthResponseDTO logar(UserRequestDTO credenciais){
        return restTemplate.postForObject(BASE_URL + "api/auth/logar", credenciais, AuthResponseDTO.class); 
    }
    
   
    public void cadastrar(UsuarioDTO user){
        restTemplate.postForObject(BASE_URL + "api/auth/cadastrar", user, AuthResponseDTO.class); 
    }
    
    
   
    public List<PacoteDTO> listarPacote(String token){
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        
        ResponseEntity<List<PacoteDTO>> response = restTemplate.exchange(
            BASE_URL + "/api/pacotes",
                HttpMethod.GET, 
                entity,
                new ParameterizedTypeReference<List<PacoteDTO>>() {}
                );
        return response.getBody();
    }
    
   
    public void criarPacote(PacoteDTO pacote, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<PacoteDTO> entity = new HttpEntity<> (pacote,headers);
        restTemplate.postForObject(BASE_URL + "api/auth/criar/pacote", entity, AuthResponseDTO.class); 
    }
    
    
    public PacoteDTO rastrear(String codigo){
     return restTemplate.getForObject(BASE_URL + "/api/pacotes/" + codigo, PacoteDTO.class);
             }
    
      public PacoteDTO atualizarStatus(Long id, String novoStatus, String otp, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        restTemplate.postForObject(BASE_URL + "api/auth/" + id + "/loja", entity, String.class); 
    
        
        String url = BASE_URL + "/api/pacotes/" + id + "/status?novoStatus=" + novoStatus;
        if(otp != null && !otp.isBlank()){
            url += "&otp=" + otp;
        }
        
        ResponseEntity<PacoteDTO> resp = restTemplate.exchange(url, HttpMethod.PUT, entity, PacoteDTO.class);
        return resp.getBody();
      }  

    public List<LojaDTO> listarLojas(String token){
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        
        ResponseEntity<List<LojaDTO>> response = restTemplate.exchange(
            BASE_URL + "/api/loja",
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
        
        ResponseEntity<List<UsuarioDTO>> response = restTemplate.exchange(
            BASE_URL + "/api/entregadore",
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
        restTemplate.postForObject(BASE_URL + "api/auth/cadastrar/loja", entity, AuthResponseDTO.class); 
    }
    
    
    public void registrarPacote(Long lojaId, LojaDTO loja, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<LojaDTO> entity = new HttpEntity<> (loja,headers);
        restTemplate.postForObject(BASE_URL + "api/auth/" + lojaId + "/loja", entity, String.class); 
    }    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.service;

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
    private final String BASE_URL = "http://localhost:9003";

    
    //logar (token e role)
    public AuthResponseDTO logar(UserRequestDTO credenciais){
        return restTemplate.postForObject(BASE_URL + "api/auth/logar", credenciais, AuthResponseDTO.class); 
    }
    
    //registrar user
    public void cadastrar(UsuarioDTO user){
        restTemplate.postForObject(BASE_URL + "api/auth/cadastrar", user, AuthResponseDTO.class); 
    }
    
    
    //listar remessa por loja
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
    
    //criar pacotes
    public void criarPacote(PacoteDTO pacote, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<PacoteDTO> entity = new HttpEntity<> (pacote,headers);
        restTemplate.postForObject(BASE_URL + "api/auth/criar/pacote", entity, AuthResponseDTO.class); 
    }
    
    //registrar pacotes
    public void registrarPacote(PacoteDTO pacoteId,PacoteDTO pacote, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<PacoteDTO> entity = new HttpEntity<> (pacote,headers);
        restTemplate.postForObject(BASE_URL + "api/auth/" + pacoteId + "/pacote", entity, String.class); 
    }

    //listar lojas
    public List<LojaDTO> listarLojas(String token){
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<> (headers);
        
        ResponseEntity<List<LojaDTO>> response = restTemplate.exchange(
            BASE_URL + "/api/pacotes",
                HttpMethod.GET, 
                entity,
                new ParameterizedTypeReference<List<LojaDTO>>() {}
                );
        return response.getBody();
    }
    
    //criar lojas
    public void criarLoja(LojaDTO loja, String token){

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<LojaDTO> entity = new HttpEntity<> (loja, headers);
        restTemplate.postForObject(BASE_URL + "api/auth/cadastrar/loja", entity, AuthResponseDTO.class); 
    }
    
    //registrar lojas
    public void registrarPacote(Long lojaId, LojaDTO loja, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<LojaDTO> entity = new HttpEntity<> (loja,headers);
        restTemplate.postForObject(BASE_URL + "api/auth/" + lojaId + "/loja", entity, String.class); 
    }    
    
}

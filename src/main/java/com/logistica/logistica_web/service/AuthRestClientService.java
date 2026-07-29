/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.service;

import com.logistica.logistica_web.model.AuthResponseDTO;
import com.logistica.logistica_web.model.PacoteDTO;
import com.logistica.logistica_web.model.UserRequestDTO;
import com.logistica.logistica_web.model.UsuarioDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Aluno
 */
@Service
public class AuthRestClientService {

    @Value("${api.base-url:http://localhost:9000}")
    private String baseUrl;
    
    private final RestTemplate rest = new RestTemplate();
   
    
    
    public AuthResponseDTO login(String email, String senha){
        String url = baseUrl + "/api/auth/login";
        UserRequestDTO req = new UserRequestDTO(email, senha);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRequestDTO> entity = new HttpEntity<>(req, headers);
        ResponseEntity<AuthResponseDTO> resp = rest.exchange(url, HttpMethod.POST, entity, AuthResponseDTO.class);
        return resp.getBody();
    }

    public String registrar(com.logistica.logistica_web.model.UsuarioDTO dto){
       
        String url = baseUrl + "/api/auth/registrar";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UsuarioDTO> entity = new HttpEntity<>(dto, headers);
        ResponseEntity<String> resp = rest.exchange(url, HttpMethod.POST, entity, String.class);
        return resp.getBody();
    }
   
    
}

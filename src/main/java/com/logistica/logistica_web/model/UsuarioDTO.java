/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.model;

import java.time.LocalDateTime;

/**
 *
 * @author BEATRICE
 */
public class UsuarioDTO {
    
    private Long id;
    
    private String nome;
    private String email;
    private String senha;
    private String perfilRole;

    private Integer tentativasOtp = 0; 

    private LocalDateTime bloqueadoAte;

    private LocalDateTime criadoEm = LocalDateTime.now(); 

    private String status; 
    
    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String senha, String perfiRole, LocalDateTime bloqueadoAte, String status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfilRole = perfiRole;
        this.bloqueadoAte = bloqueadoAte;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfilRole() {
        return perfilRole;
    }

    public void setPerfilRole(String perfiRole) {
        this.perfilRole = perfiRole;
    }

    public Integer getTentativasOtp() {
        return tentativasOtp;
    }

    public void setTentativasOtp(Integer tentativasOtp) {
        this.tentativasOtp = tentativasOtp;
    }

    public LocalDateTime getBloqueadoAte() {
        return bloqueadoAte;
    }

    public void setBloqueadoAte(LocalDateTime bloqueadoAte) {
        this.bloqueadoAte = bloqueadoAte;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.model;

/**
 *
 * @author BEATRICE
 */
public class UsuarioDTO {
    
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String perfiRole;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, String senha, String perfiRole) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfiRole = perfiRole;
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

    public String getPerfiRole() {
        return perfiRole;
    }

    public void setPerfiRole(String perfiRole) {
        this.perfiRole = perfiRole;
    }
    
    
    
}

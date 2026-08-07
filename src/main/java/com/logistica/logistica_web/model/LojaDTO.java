/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.model;

/**
 *
 * @author BEATRICE
 */
public class LojaDTO {
    
    private Long idLoja;
    private String nome_estabelecimento;
    private String cnpj;
    private Long id_usuario;
    private String contato_email;
    private String codigoLon;
    private String cidade;
    private String endereco;
    private Boolean ativo = true;

    public LojaDTO() {
    }

    public LojaDTO(Long id, String nome_estabelecimento, String cnpj, Long id_usuario, String contato_email, String codigoLon, String cidade, String endereco) {
        this.idLoja = id;
        this.nome_estabelecimento = nome_estabelecimento;
        this.cnpj = cnpj;
        this.id_usuario = id_usuario;
        this.contato_email = contato_email;
        this.codigoLon = codigoLon;
        this.cidade = cidade;
        this.endereco = endereco;
    }

    public Long getId() {
        return idLoja;
    }

    public void setId(Long id) {
        this.idLoja = idLoja;
    }

    public String getNome_estabelecimento() {
        return nome_estabelecimento;
    }

    public void setNome_estabelecimento(String nome_estabelecimento) {
        this.nome_estabelecimento = nome_estabelecimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getContato_email() {
        return contato_email;
    }

    public void setContato_email(String contato_email) {
        this.contato_email = contato_email;
    }

    public String getCodigoLon() {
        return codigoLon;
    }

    public void setCodigoLon(String codigoLon) {
        this.codigoLon = codigoLon;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    
    
    
    

}

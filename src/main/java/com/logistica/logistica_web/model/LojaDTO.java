/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author BEATRICE
 */
public class LojaDTO {
 
    
    @JsonAlias({"id", "idLoja", "id_loja"})
    private Long idLoja;
    
    @JsonAlias({"nomeEstabelecimento", "nome_estabelecimento", "nome"})
    private String nomeEstabelecimento;
    
    private String cnpj;

    @JsonAlias({"id_usuario", "idUsuario"})
    private Long idUsuario;

    @JsonAlias({"contato_email", "contatoEmail", "email"})
    private String contatoEmail;

    @JsonAlias({"codigo_lon", "codigoLon"})
    private String codigoLon;
    
    private String cidade;
    private String enderecoDestino;

    public LojaDTO() {}

    public LojaDTO(Long idLoja, String nomeEstabelecimento, String cnpj, Long idUsuario, String contatoEmail, String codigoLon, String cidade, String enderecoDestino) {
        this.idLoja = idLoja;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.cnpj = cnpj;
        this.idUsuario = idUsuario;
        this.contatoEmail = contatoEmail;
        this.codigoLon = codigoLon;
        this.cidade = cidade;
        this.enderecoDestino = enderecoDestino;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContatoEmail() {
        return contatoEmail;
    }

    public void setContatoEmail(String contatoEmail) {
        this.contatoEmail = contatoEmail;
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

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }


}    


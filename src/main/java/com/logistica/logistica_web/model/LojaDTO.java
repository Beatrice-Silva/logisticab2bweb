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
    
    @JsonAlias({"id"})
    private Long idLoja;
    
    @JsonAlias({"nome_estabelecimento", "nomeEstabelecimento"})
    private String nomeEstabelecimento;
    
    private String cnpj;

    @JsonProperty("id_usuario")
    private Long idUsuario;

    @JsonProperty("contato_email")
    private String contatoEmail;

    @JsonProperty("codigo_lon")
    private String codigoLon;
    
    private String cidade;
    private String endereco;

    public LojaDTO() {
    }

    public LojaDTO(Long idLoja, String nomeEstabelecimento, String cnpj, Long idUsuario, String contatoEmail, String codigoLon, String cidade, String endereco) {
        this.idLoja = idLoja;
        this.nomeEstabelecimento = nomeEstabelecimento;
        this.cnpj = cnpj;
        this.idUsuario = idUsuario;
        this.contatoEmail = contatoEmail;
        this.codigoLon = codigoLon;
        this.cidade = cidade;
        this.endereco = endereco;
    }
    
    @JsonProperty("id")
    public Long getId() { return idLoja; }
    
    @JsonProperty("id")
    public void setId(Long id) { this.idLoja = id; }

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

   
}

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

public class PacoteDTO {
    
    private Long id;
    private String codigoRastreio;
    private String otp_codigo;
    private LocalDateTime otp_expira;
    private Long id_loja;
    private String enderecoDestino;
    private String status_atual;
    private String observacao;
    private Double peso;

    public PacoteDTO() {
    }

    public PacoteDTO(Long id, String codigoRastreio, String otp_codigo, LocalDateTime otp_expira, Long id_loja, String enderecoDestino, String status_atual, String observacao, Double peso) {
        this.id = id;
        this.codigoRastreio = codigoRastreio;
        this.otp_codigo = otp_codigo;
        this.otp_expira = otp_expira;
        this.id_loja = id_loja;
        this.enderecoDestino = enderecoDestino;
        this.status_atual = status_atual;
        this.observacao = observacao;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public String getOtp_codigo() {
        return otp_codigo;
    }

    public void setOtp_codigo(String otp_codigo) {
        this.otp_codigo = otp_codigo;
    }

    public LocalDateTime getOtp_expira() {
        return otp_expira;
    }

    public void setOtp_expira(LocalDateTime otp_expira) {
        this.otp_expira = otp_expira;
    }

    public Long getId_loja() {
        return id_loja;
    }

    public void setId_loja(Long id_loja) {
        this.id_loja = id_loja;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public String getStatus_atual() {
        return status_atual;
    }

    public void setStatus_atual(String status_atual) {
        this.status_atual = status_atual;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    

}
    
    
    
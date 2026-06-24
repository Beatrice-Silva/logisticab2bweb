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
    private String codigoLon;
    private String otp_codigo;
    private LocalDateTime otp_expira;
    private Long id_loja;
    private String endereco;
    private String status_atual;
    private String desc_observ;
    private Double peso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoLon() {
        return codigoLon;
    }

    public void setCodigoLon(String codigoLon) {
        this.codigoLon = codigoLon;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getStatus_atual() {
        return status_atual;
    }

    public void setStatus_atual(String status_atual) {
        this.status_atual = status_atual;
    }

    public String getDesc_observ() {
        return desc_observ;
    }

    public void setDesc_observ(String desc_observ) {
        this.desc_observ = desc_observ;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
}
    
    
    
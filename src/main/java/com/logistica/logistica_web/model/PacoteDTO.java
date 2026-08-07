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
    
    private Long id_loja;

    private String codigoRastreio;
    private Long loja;
    private String enderecoDestino;
    private Enum statusAtual;
    private String emailDestinatario;
    private String otpCodigo;
    private LocalDateTime otpExpira;
    private String Observacao;
    private LocalDateTime otp_expira;
    

    public PacoteDTO() {
    }

    public PacoteDTO(Long id_loja, String codigoRastreio, Long loja, String enderecoDestino, Enum statusAtual, String emailDestinatario, String otpCodigo, LocalDateTime otpExpira, String Observacao, LocalDateTime otp_expira) {
        this.id_loja = id_loja;
        this.codigoRastreio = codigoRastreio;
        this.loja = loja;
        this.enderecoDestino = enderecoDestino;
        this.statusAtual = statusAtual;
        this.emailDestinatario = emailDestinatario;
        this.otpCodigo = otpCodigo;
        this.otpExpira = otpExpira;
        this.Observacao = Observacao;
        this.otp_expira = otp_expira;
    }

    
    
    public Long getId_loja() {
        return id_loja;
    }

    public void setId_loja(Long id_loja) {
        this.id_loja = id_loja;
    }

    

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public Long getLoja() {
        return loja;
    }

    public void setLoja(Long loja) {
        this.loja = loja;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public Enum getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(Enum statusAtual) {
        this.statusAtual = statusAtual;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getOtpCodigo() {
        return otpCodigo;
    }

    public void setOtpCodigo(String otpCodigo) {
        this.otpCodigo = otpCodigo;
    }

    public LocalDateTime getOtpExpira() {
        return otpExpira;
    }

    public void setOtpExpira(LocalDateTime otpExpira) {
        this.otpExpira = otpExpira;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    public LocalDateTime getOtp_expira() {
        return otp_expira;
    }

    public void setOtp_expira(LocalDateTime otp_expira) {
        this.otp_expira = otp_expira;
    }
    
    
    
}
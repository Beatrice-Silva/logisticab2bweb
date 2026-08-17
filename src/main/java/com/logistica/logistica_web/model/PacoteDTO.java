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
    
    private LojaDTO loja;
    
    private String enderecoDestino;
    
    private String statusAtual;
    
    private String emailDestinatario;
    
    private String otpCodigo;
    
    private LocalDateTime otpExpira;
    
    private String descObserv;

    public PacoteDTO() {
    }

    public PacoteDTO(Long id, String codigoRastreio, LojaDTO loja, String enderecoDestino, String statusAtual, String emailDestinatario, String otpCodigo, LocalDateTime otpExpira, String descObserv) {
        this.id = id;
        this.codigoRastreio = codigoRastreio;
        this.loja = loja;
        this.enderecoDestino = enderecoDestino;
        this.statusAtual = statusAtual;
        this.emailDestinatario = emailDestinatario;
        this.otpCodigo = otpCodigo;
        this.otpExpira = otpExpira;
        this.descObserv = descObserv;
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

    public LojaDTO getLoja() {
        return loja;
    }

    public void setLoja(LojaDTO loja) {
        this.loja = loja;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public String getStatusAtual() {
        return statusAtual;
    }

    public void setStatusAtual(String statusAtual) {
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

    public String getDescObserv() {
        return descObserv;
    }

    public void setDescObserv(String descObserv) {
        this.descObserv = descObserv;
    }

    
    

    
}
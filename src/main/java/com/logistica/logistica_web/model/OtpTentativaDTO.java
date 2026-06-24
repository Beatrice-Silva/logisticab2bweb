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

public class OtpTentativaDTO {

    private Long id;
    private Long idPacote;
    private Integer tentativas = 0;
    private LocalDateTime bloqueioAte;   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Long idPacote) {
        this.idPacote = idPacote;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public LocalDateTime getBloqueioAte() {
        return bloqueioAte;
    }

    public void setBloqueioAte(LocalDateTime bloqueioAte) {
        this.bloqueioAte = bloqueioAte;
    }



}

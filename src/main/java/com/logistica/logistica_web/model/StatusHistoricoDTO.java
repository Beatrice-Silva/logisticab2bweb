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

public class StatusHistoricoDTO {

    private Long id;    
    private Long idPacote;
    private String status;
    private LocalDateTime dataHora;
    private String observacao;
    private Long idUsuario;

    public StatusHistoricoDTO() {
    }

    public StatusHistoricoDTO(Long id, Long idPacote, String status, LocalDateTime dataHora, String observacao, Long idUsuario) {
        this.id = id;
        this.idPacote = idPacote;
        this.status = status;
        this.dataHora = dataHora;
        this.observacao = observacao;
        this.idUsuario = idUsuario;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
   
    
    
}

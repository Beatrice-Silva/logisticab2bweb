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
    private Long id_pacote;
    private String status;
    private LocalDateTime data_hora;
    private String desc_observ;
    private Long id_usuario;
    
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_pacote() {
        return id_pacote;
    }

    public void setId_pacote(Long id_pacote) {
        this.id_pacote = id_pacote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public String getDesc_observ() {
        return desc_observ;
    }

    public void setDesc_observ(String desc_observ) {
        this.desc_observ = desc_observ;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
}

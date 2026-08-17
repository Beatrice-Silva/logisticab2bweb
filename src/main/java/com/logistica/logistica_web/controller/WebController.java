/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.logistica.logistica_web.controller;

import com.logistica.logistica_web.model.UsuarioDTO;
import com.logistica.logistica_web.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author BEATRICE
 */
@Controller
public class WebController {

       @GetMapping("/index")
    public String index(){ return "index"; }

    @GetMapping("/rastrearServico")
    public String rastrear(){ return "rastrearServico"; 
   
        }

    @GetMapping("/verificacao")
    public String verificacao(){ return "verificacao"; }

    @GetMapping("/validaentrega")
    public String validaentrega(){ return "validaentrega"; }
}


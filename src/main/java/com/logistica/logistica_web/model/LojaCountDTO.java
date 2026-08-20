package com.logistica.logistica_web.model;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LojaCountDTO {
    private Long id;
    @JsonAlias({"nome_estabelecimento","nomeEstabelecimento","nome"})
    private String nome;
    private Long total;

    public LojaCountDTO() {}
    public LojaCountDTO(Long id, String nome, Long total){ 
        this.id=id; this.nome=nome; this.total=total; 
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getNome(){ return nome; }
    public void setNome(String nome){ this.nome=nome; }

    
    @JsonProperty("nomeEstabelecimento")
    public String getNomeEstabelecimento(){ return nome; }
    public void setNomeEstabelecimento(String n){ this.nome=n; }

    @JsonProperty("nome_estabelecimento")
    public String getNome_estabelecimento(){ return nome; }
    public void setNome_estabelecimento(String n){ this.nome=n; }

    public Long getTotal(){ return total; }
    public void setTotal(Long total){ this.total=total; }
}
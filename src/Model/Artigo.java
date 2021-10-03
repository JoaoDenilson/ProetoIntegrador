/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author João Denílson
 */
public class Artigo {
    private int  id;
    private String titulo;
    private String paginacao;
    private String resumo;
    private Volume volume;
    private List<Assunto> assunto;
    private List<Autor> autor;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPaginacao() {
        return paginacao;
    }

    public void setPaginacao(String paginacao) {
        this.paginacao = paginacao;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public List<Assunto> getAssunto() {
        return assunto;
    }

    public void setAssunto(List<Assunto> assunto) {
        this.assunto = assunto;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }
    
    

}

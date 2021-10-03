/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author João Denílson
 */
public class Volume {
    private int  id;
    private String volume;
    private String edicao;
    private String anoFinal;
    private String indicacaoResponsabilidade;
    private Obra obra;

    @Override
    public String toString() {
        return volume;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(String anoFinal) {
        this.anoFinal = anoFinal;
    }

    public String getIndicacaoResponsabilidade() {
        return indicacaoResponsabilidade;
    }

    public void setIndicacaoResponsabilidade(String indicacaoResponsabilidade) {
        this.indicacaoResponsabilidade = indicacaoResponsabilidade;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
    
    
}

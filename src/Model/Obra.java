package Model;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Collections.list;
import java.util.List;
public class Obra {
    private int issn;
    private String titulo;
    private String subTitulo;
    private String classificacao;
    private String anoInicial;
    private String anoFinal;
    private String indicacaoResponsabilidade;
    private String notacaoAutor;
    private Local localpubli;
    private Editora editora;
    private Tipo tipoobra;
    private List<Assunto> assunto;
    private List<Autor> autor;

    @Override
    public String toString() {
        return titulo;
    }
    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }


    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getAnoInicial() {
        return anoInicial;
    }

    public void setAnoInicial(String anoInicial) {
        this.anoInicial = anoInicial;
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

    public String getNotacaoAutor() {
        return notacaoAutor;
    }

    public void setNotacaoAutor(String notacaoAutor) {
        this.notacaoAutor = notacaoAutor;
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

    public int getIssn() {
        return issn;
    }

    public void setIssn(int issn) {
        this.issn = issn;
    }

    public Local getLocalpubli() {
        return localpubli;
    }

    public void setLocalpubli(Local localpubli) {
        this.localpubli = localpubli;
    }

    public Tipo getTipoobra() {
        return tipoobra;
    }

    public void setTipoobra(Tipo tipoobra) {
        this.tipoobra = tipoobra;
    }    

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }
    
}


package Dao;

import Model.Obra;
import Model.Assunto;
import Model.Autor;
import Model.Tipo;
import Conecao_bd.ConnectionFactory;
import Model.Editora;
import Model.Local;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;


public class ObraDAO {
    
    private Connection connection;
    //Conectando com o Banco de dados
    public ObraDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }   
    
    //Adcionando Obra
    public int adiciona(Obra obra) throws SQLException {
        int id = 0;
        String sql = "insert into obra (titulo, subTitulo, ISSN, Classificacao, "
                + "Ano_inicial, Ano_final, Indicacao_responsabilidade, Notacao_autor, id_local, id_editora, id_tipo) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //seta os valores
            stmt.setString(1, obra.getTitulo());
            stmt.setString(2, obra.getSubTitulo());
            stmt.setInt(3, obra.getIssn());
            stmt.setString(4, obra.getClassificacao());
            stmt.setString(5, obra.getAnoInicial());
            stmt.setString(6, obra.getAnoFinal());
            stmt.setString(7, obra.getIndicacaoResponsabilidade());
            stmt.setString(8, obra.getNotacaoAutor());
            stmt.setInt(9, obra.getLocalpubli().getId());
            stmt.setInt(10, obra.getEditora().getId());
            stmt.setInt(11, obra.getTipoobra().getId());
            //executa
            stmt.execute();            
            
            //Pegando o ID 
            id= obra.getIssn();
            
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Segunda tabela.
        String sql2 = "insert into obra_autores (id_autores, ISSN) "
                + "values (?, ?)";
        
        List<Autor> list = obra.getAutor();
        
        for (Autor autor : list) {
            try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql2);
            //seta os valores
            stmt.setInt(1, autor.getId());
            stmt.setInt(2, obra.getIssn());
            //executa
            stmt.execute();
            stmt.close();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        //Terceira tabela.
        String sql3 = "insert into obra_assunto (id_assunto, ISSN) "
                + "values (?, ?)";
        
        List<Assunto> Assunt = obra.getAssunto();
        
        for (Assunto assunto : Assunt) {
            try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql3);
            //seta os valores
            stmt.setInt(1, assunto.getId());
            stmt.setInt(2, obra.getIssn());
            //executa
            stmt.execute();
            stmt.close();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return id;       
    }
       
    //Listando as Obras
    public List<Obra> listar(){
        String sql = "select * from obra o, aux_editora e, aux_local l, aux_tipo_obra t\n" +
                    "WHERE o.id_editora = e.id_editora AND o.id_local = l.id_local AND o.id_tipo = t.id_tipo";
        List<Obra> listar = new ArrayList<Obra>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()){
                Obra obra = new Obra();
                Editora edit = new Editora();
                Local local = new Local();
                
                obra.setIssn(rs.getInt("ISSN"));
                obra.setTitulo(rs.getString("Titulo"));
                edit.setNome(rs.getString("Editora"));
                local.setLocal(rs.getString("Local"));
                
                obra.setEditora(edit);
                obra.setLocalpubli(local);
                            
                listar.add(obra);
                 
                 }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return listar;
        }
   
    //Fazendo busca de uma Obra
    public Obra buscarObra(Integer ISSN) throws SQLException{
        String sql = "select * from obra o, aux_editora e, aux_local l, aux_tipo_obra t\n" +
                    "WHERE `ISSN` = ? AND o.id_editora = e.id_editora AND o.id_local = l.id_local AND o.id_tipo = t.id_tipo";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ISSN);
        ResultSet rs =  stmt.executeQuery();
        rs.beforeFirst();
        rs.next();
        Obra obra = new Obra();
        Editora edit = new Editora();
        Local local = new Local();
        Tipo tipo = new Tipo();
        
        obra.setIssn(rs.getInt("ISSN"));
        obra.setTitulo(rs.getString("Titulo"));
        obra.setSubTitulo(rs.getString("SubTitulo"));
        obra.setAnoInicial(rs.getString("Ano_inicial"));
        obra.setAnoFinal(rs.getString("Ano_final"));
        obra.setClassificacao(rs.getString("Classificacao"));
        obra.setIndicacaoResponsabilidade(rs.getString("Indicacao_responsabilidade"));
        obra.setNotacaoAutor(rs.getString("Notacao_autor"));
        
        tipo.setId(rs.getInt("id_tipo"));
        tipo.setTipo(rs.getString("Tipo"));
        edit.setId(rs.getInt("id_editora"));
        edit.setNome(rs.getString("Editora"));
        local.setId(rs.getInt("id_local"));
        local.setLocal(rs.getString("Local"));
                
        obra.setEditora(edit);
        obra.setLocalpubli(local);
        
        stmt.close();
        
        //Buscando os autores da Obra
        List<Autor> listAutor = new ArrayList<Autor>(); 
        
        String sql2 = "select * from aux_autores a, obra_autores oa WHERE oa.ISSN = ? AND a.id_autores = oa.id_autores ";
        PreparedStatement stmt2 = connection.prepareStatement(sql2);
        stmt2.setInt(1, ISSN);
        ResultSet rs2 =  stmt2.executeQuery();
        while(rs2.next()){
           Autor autor = new Autor();
           autor.setId(rs2.getInt("id_autores"));
           autor.setNome(rs2.getString("Autores"));
           
           listAutor.add(autor);
        }
        stmt2.close();
        
        //Buscando os assuntos da Obra
        List<Assunto> assunt = new ArrayList<Assunto>();
        
        String sql3 = "select * from aux_assuntos a, obra_assunto oa WHERE oa.ISSN = ? AND a.id_assunto = oa.id_assunto";
        PreparedStatement stmt3 = connection.prepareStatement(sql3);
        stmt3.setInt(1, ISSN);
        ResultSet rs3 =  stmt3.executeQuery();
        while(rs3.next()){
           Assunto assunto = new Assunto();
           assunto .setId(rs3.getInt("id_assunto"));
           assunto .setNome(rs3.getString("Nome"));
           
           assunt.add(assunto);
        }
        stmt3.close();
        
        //Atribuindo os autores e assuntos da obra
        obra.setAutor(listAutor);
        obra.setAssunto(assunt);
        
        return obra;       
    }
    
    //Pesquisar fazendo filtragem
    public List<Obra> pesquisar(Obra obra){
        
        String sql = "select * from obra o, aux_editora e, aux_local l, aux_tipo_obra t\n" +
        "WHERE (o.id_editora = e.id_editora AND o.id_local = l.id_local AND o.id_tipo = t.id_tipo) AND\n" +
        "(o.Titulo LIKE ? or e.Editora = ? or l.Local = ?)";
        
        List<Obra> listar = new ArrayList<Obra>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, '%'+obra.getTitulo()+ '%');
            stmt.setString(2, obra.getEditora()!=null?obra.getEditora().getNome():"");
            stmt.setString(3, obra.getLocalpubli()!=null?obra.getLocalpubli().getLocal():"");
            ResultSet rs =  stmt.executeQuery();
            
            while(rs.next()){
                Obra obr = new Obra();
                Editora edit = new Editora();
                Local loc = new Local();
                
                obr.setIssn(rs.getInt("ISSN"));
                obr.setTitulo(rs.getString("Titulo"));
                edit.setNome(rs.getString("Editora"));
                loc.setLocal(rs.getString("Local"));
                
                obr.setEditora(edit);
                obr.setLocalpubli(loc);
                            
                listar.add(obr);
                 
                 }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return listar;
    }

    //Editando Obra
    public void alterar(Obra obra) throws SQLException{
               
        String sql = "UPDATE obra SET titulo=?, subTitulo=?, classificacao=?, "
                + "Ano_inicial=?, Ano_final=?, Indicacao_responsabilidade=?, Notacao_autor=?, id_local=?, id_editora=?, id_tipo=?"
                + "WHERE ?";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1, obra.getTitulo());
            stmt.setString(2, obra.getSubTitulo());
            stmt.setString(3, obra.getClassificacao());
            stmt.setString(4, obra.getAnoInicial());
            stmt.setString(5, obra.getAnoFinal());
            stmt.setString(6, obra.getIndicacaoResponsabilidade());
            stmt.setString(7, obra.getNotacaoAutor());
            stmt.setInt(8, obra.getLocalpubli().getId());
            stmt.setInt(9, obra.getEditora().getId());
            stmt.setInt(10, obra.getTipoobra().getId());
            stmt.setInt(11, obra.getIssn());
            //executa
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        //Segunda tabela.
        String sql2 = "UPDATE obra_autores SET id_autores=?, ISSN=?";
        
        List<Autor> list = obra.getAutor();
        if(list.size() > 0){
            for (Autor autor : list) {
                try {
                    //prepared statement para inserção
                    PreparedStatement stmt = connection.prepareStatement(sql2);
                    //seta os valores
                    stmt.setInt(1, autor.getId());
                    stmt.setInt(2, obra.getIssn());
                    //executa
                    stmt.execute();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } 
        }  
        
        //Terceira tabela.
        String sql3 = "UPDATE obra_assunto SET id_assunto=?, ISSN=?";
        
        List<Assunto> Assunt = obra.getAssunto();
        if(Assunt.size() > 0){
            for (Assunto assunto : Assunt) {
                try {
                //prepared statement para inserção
                PreparedStatement stmt = connection.prepareStatement(sql3);
                //seta os valores
                stmt.setInt(1, assunto.getId());
                stmt.setInt(2, obra.getIssn());
                //executa
                stmt.execute();
                stmt.close();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        
    }
    
    //Excluindo Obra
    public void excluir(Integer ISSN) throws SQLException{
        
        String sql = "DELETE FROM obra WHERE `ISSN` = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, ISSN);
        stmt.executeUpdate();  
    } 
}

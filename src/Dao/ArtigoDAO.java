/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conecao_bd.ConnectionFactory;
import Model.Artigo;
import Model.Assunto;
import Model.Autor;
import Model.Editora;
import Model.Local;
import Model.Obra;
import Model.Volume;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 * @author João Denílson
 */
public class ArtigoDAO {
    
    private Connection connection;
    //Construtor da coneceção com o SGBD
    public ArtigoDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    //Adcionando Obra
    //não está completo
    public void adiciona(List<Artigo> artigo, int idVolume) throws SQLException {
        int idA= 0;
        String sql ="Insert into artigo (Titulo, Paginacao, Resumo, Id_volume)"
        + "values (?, ?, ?, ?)";
        
        for (Artigo art : artigo) {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, art.getTitulo());
            stmt.setString(2, art.getPaginacao());
            stmt.setString(3, art.getResumo());
            stmt.setInt(4, idVolume);
            
            stmt.execute();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                idA = rs.getInt(1);
            }
             //Adcionando os Autores do artigo
            String sql2 = "insert into artigo_autores (id_autores, Id_artigo) "
                    + "values (?, ?)";
            List<Autor> listA = art.getAutor();
            
            for (Autor autor : listA) {
                //seta os valores
                try ( 
                    //prepared statement para inserção
                    PreparedStatement stmt2 = connection.prepareStatement(sql2)){
                    //seta os valores
                    stmt2.setInt(1, autor.getId());
                    stmt2.setInt(2, idA);
                    //executa
                    stmt.execute();
                   
                }
            }
            
             //Adcionando os Assuntos do artigo
            String sql3 = "insert into artigo_assunto (id_assunto, Id_artigo) "
                + "values (?, ?)";
            List<Assunto> Assunt = art.getAssunto();
            for (Assunto ass : Assunt) {
                //seta os valores
                try ( 
                    //prepared statement para inserção
                    PreparedStatement stmt3 = connection.prepareStatement(sql3)){
                    //seta os valores
                    stmt3.setInt(1, ass.getId());
                    stmt3.setInt(2, idA);
                    //executa
                    stmt.execute();
                    stmt.close();
                }
            }
        }       
    }
    
    //Listando os Artigo
    public List<Artigo> listar() throws SQLException{
        String sql = "select * from artigo a, volume v WHERE a.Id_volume = v.Id_volume";
        List<Artigo> listar = new ArrayList<Artigo>();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs =  stmt.executeQuery();
        
        while(rs.next()){
            Volume volume = new Volume();
            Artigo artigo = new Artigo();            
            
            artigo.setId(rs.getInt("Id_artigo"));
            artigo.setTitulo(rs.getString("Titulo"));
            artigo.setResumo(rs.getString("Resumo"));
            artigo.setPaginacao(rs.getString("Paginacao"));
            volume.setVolume(rs.getString("Volume"));
            artigo.setVolume(volume);
            
            listar.add(artigo);

        }
        return listar;    
    }
    
    //Fazendo busca de um Artigo
    public Artigo buscarArtigo(Integer idArtigo) throws SQLException{
        String sql = "select * from artigo a, volume v\n" +
        "WHERE a.Id_artigo = ? AND a.Id_volume = v.Id_volume";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idArtigo);
        ResultSet rs =  stmt.executeQuery();
        rs.beforeFirst();
        rs.next();
        
        Artigo artigo = new Artigo();
        Volume volume = new Volume();
        
        artigo.setId(rs.getInt("Id_artigo"));
        artigo.setTitulo(rs.getString("Titulo"));
        artigo.setResumo(rs.getString("Resumo"));
        artigo.setPaginacao(rs.getString("Paginacao"));
        volume.setVolume(rs.getString("Volume"));
        artigo.setVolume(volume);
        
        stmt.close();
        
        //Buscando os autores da Obra
        List<Autor> listAutor = new ArrayList<Autor>(); 
        
        String sql2 = "select * from aux_autores a, artigo_autores oa WHERE oa.Id_artigo = ? AND a.id_autores = oa.id_autores";
        PreparedStatement stmt2 = connection.prepareStatement(sql2);
        stmt2.setInt(1, idArtigo);
        ResultSet rs2 =  stmt2.executeQuery();
        while(rs2.next()){
           Autor autor = new Autor();
           autor.setId(rs2.getInt("id_autores"));
           autor.setNome(rs2.getString("Autores"));
           
           listAutor.add(autor);
        }
        stmt2.close();
        
        //Buscando os assuntos do Artigo
        List<Assunto> assunt = new ArrayList<Assunto>();
        
        String sql3 = "select * from aux_assuntos a, artigo_assunto oa WHERE oa.Id_artigo = ? AND a.id_assunto = oa.id_assunto";
        PreparedStatement stmt3 = connection.prepareStatement(sql3);
        stmt3.setInt(1, idArtigo);
        ResultSet rs3 =  stmt3.executeQuery();
        while(rs3.next()){
           Assunto assunto = new Assunto();
           assunto .setId(rs3.getInt("id_assunto"));
           assunto .setNome(rs3.getString("Nome"));
 
           assunt.add(assunto);
        }
        stmt3.close();
        
        //Atribuindo os autores e assuntos do Artigo
        artigo.setAutor(listAutor);
        artigo.setAssunto(assunt);
        
        return artigo;              
    }
    
     //Pesquisar fazendo filtragem dos Artigos
    public List<Artigo> pesquisar(Artigo artigo, Autor autor){
        String sql = "select * from artigo o, volume v, aux_autores ax, artigo_autores a "
        + "WHERE (o.Id_volume = v.Id_volume AND a.id_autores = ax.id_autores AND a.Id_artigo = o.Id_artigo)" +
        "AND(o.Titulo LIKE ? or ax.id_autores = ?)";
        
        List<Artigo> listar = new ArrayList<Artigo>();

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, '%'+artigo.getTitulo()+'%');
            stmt.setInt(2, autor.getId());
            ResultSet rs =  stmt.executeQuery();
            
            while(rs.next()){
                Volume volume = new Volume();
                Artigo art = new Artigo();            
                
                art.setId(rs.getInt("Id_artigo"));
                art.setTitulo(rs.getString("Titulo"));
                //art.setResumo(rs.getString("Resumo"));
                art.setPaginacao(rs.getString("Paginacao"));
                volume.setVolume(rs.getString("Volume"));
                art.setVolume(volume);
            
                listar.add(art);
                 
                 }
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        return listar;
    
    }
    
    //Editando um Artigo
    public void alterar(Artigo artigo) throws SQLException{
        String sql = "UPDATE artigo SET titulo =?, Paginacao =?, Resumo =? WHERE Id_artigo = ?";
        
        //prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);
        //seta os valores
        stmt.setString(1, artigo.getTitulo());
        stmt.setString(2, artigo.getPaginacao());
        stmt.setString(3, artigo.getResumo());
        stmt.setInt(4, artigo.getId());
        
        stmt.execute();
        
        //Adcionando os Autores do artigo
        String sql2 = "UPDATE artigo_autores SET id_autores =?, Id_artigo=? "
                + "values (?, ?)";
        List<Autor> listA = artigo.getAutor();
        if(listA.size() > 0){
            for (Autor autor : listA) {
                //seta os valores
                try ( 
                    //prepared statement para inserção
                    PreparedStatement stmt2 = connection.prepareStatement(sql2)){
                    //seta os valores
                    stmt2.setInt(1, autor.getId());
                    stmt2.setInt(2, artigo.getId());
                    //executa
                    stmt.execute();
                }
            }
        }        
        
        //Adcionando os Assuntos do artigo
        String sql3 = "UPDATE artigo_assunto SET id_assunto=?, Id_artigo=?"
            + "values (?, ?)";
        List<Assunto> Assunt = artigo.getAssunto();
        if(Assunt.size() > 0){
            for (Assunto ass : Assunt) {
                //seta os valores
                try ( 
                    //prepared statement para inserção
                    PreparedStatement stmt3 = connection.prepareStatement(sql3)){
                    //seta os valores
                    stmt3.setInt(1, ass.getId());
                    stmt3.setInt(2, artigo.getId());
                    //executa
                    stmt.execute();
                }
            }
        }      
    }
    
    //Excluindo Artigo
    public void excluir(Integer idArtigo) throws SQLException{
        String sql = "DELETE FROM artigo WHERE Id_artigo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idArtigo);
        stmt.executeUpdate();  
    } 
}

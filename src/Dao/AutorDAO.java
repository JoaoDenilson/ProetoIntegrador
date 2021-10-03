/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conecao_bd.ConnectionFactory;
import Model.Autor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author João Denílson
 */
public class AutorDAO {
    
    private Connection connection;

    public AutorDAO() {
        this.connection = new ConnectionFactory().getConnection();

    }
    
    public void adiciona(Autor autor) {
        String sql = "insert into aux_autores (Autores) values (?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1, autor.getNome());        
            //executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Lista de todos os Autores
    public List<Autor> listaAutor() throws SQLException{
        String sql= "SELECT * FROM aux_autores";
        ResultSet rs = null ;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
             rs=stmt.executeQuery();
             List<Autor> listaAutores = new ArrayList<>();
             
             while(rs.next()){
                 Autor autor = new Autor();
                 autor.setId(rs.getInt("id_autores"));
                 autor.setNome(rs.getString("Autores"));
                listaAutores.add(autor);
                 
             }
             return listaAutores;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

     }
}

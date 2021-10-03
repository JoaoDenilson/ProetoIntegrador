/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Conecao_bd.ConnectionFactory;
import Model.Assunto;
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
public class AssuntoDAO {
    
    private Connection connection;

    public AssuntoDAO() {
        this.connection = new ConnectionFactory().getConnection();

    }
    
    public void adiciona(Assunto assunto) {
        String sql = "insert into aux_assuntos (Nome) values (?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1, assunto.getNome()); 
            //executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Lista de todos os assuntos
    public List<Assunto> listaAssuntos() throws SQLException{
        String sql= "SELECT * FROM aux_assuntos";
        ResultSet rs = null ;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
             rs=stmt.executeQuery();
             List<Assunto> listaAssuntos = new ArrayList<>();
             
             while(rs.next()){
                Assunto assunto = new Assunto();
                assunto.setId(rs.getInt("id_assunto"));
                assunto.setNome(rs.getString("Nome"));
                listaAssuntos.add(assunto);
                 
             }
             return listaAssuntos;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
     }
}

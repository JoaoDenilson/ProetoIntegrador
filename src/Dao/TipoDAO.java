/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Tipo;
import Conecao_bd.ConnectionFactory;
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
public class TipoDAO {
    
    private Connection connection;

    public TipoDAO() {
        this.connection = new ConnectionFactory().getConnection();

    }
    
    public void adiciona(Tipo tipo) {
        String sql = "insert into aux_tipo_obra (Tipo) values (?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1, tipo.getTipo());        
            //executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Lista de todos os Locais
    public List<Tipo> listaTipo() throws SQLException{
        String sql= "SELECT * FROM aux_tipo_obra";
        ResultSet rs = null ;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
             rs=stmt.executeQuery();
             List<Tipo> listaTipo = new ArrayList<>();
             
             while(rs.next()){
                 Tipo tipo = new Tipo();
                 tipo.setId(rs.getInt("id_tipo"));
                 tipo.setTipo(rs.getString("Tipo"));
                listaTipo.add(tipo);                 
             }
             return listaTipo;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conecao_bd.ConnectionFactory;
import Model.Local;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LocalDAO {
    private Connection connection;

    public LocalDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Local local){
        String sql = "insert into aux_local (Local) values (?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1, local.getLocal()); 
            //executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Lista de todos os Locais
    public List<Local> listaLocal() throws SQLException{
        String sql= "SELECT * FROM aux_local";
        ResultSet rs = null ;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
             rs=stmt.executeQuery();
             List<Local> listaLocal = new ArrayList<>();
             
             while(rs.next()){
                 Local local = new Local();
                 local.setId(rs.getInt("id_local"));
                 local.setLocal(rs.getString("Local"));
                listaLocal.add(local);                 
             }
             return listaLocal;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

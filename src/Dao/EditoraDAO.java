/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conecao_bd.ConnectionFactory;
import Model.Editora;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EditoraDAO {
    private Connection connection;

    public EditoraDAO() {
        this.connection = new ConnectionFactory().getConnection();

    }
    
    public void adiciona(Editora editora) {
        String sql = "insert into aux_editora (Editora) values (?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);
            //seta os valores
            stmt.setString(1, editora.getNome()); 
            //executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    //Lista de todos os Editoras
    public List<Editora> listaEditora() throws SQLException{
        String sql= "SELECT * FROM aux_editora";
        ResultSet rs = null ;
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
             rs=stmt.executeQuery();
             List<Editora> listaEditora = new ArrayList<>();
             
             while(rs.next()){
                 Editora editora = new Editora();
                 editora.setId(rs.getInt("id_editora"));
                 editora.setNome(rs.getString("Editora"));
                listaEditora.add(editora);                 
             }
             return listaEditora;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


package Dao;

import Conecao_bd.ConnectionFactory;
import Model.Artigo;
import Model.Editora;
import Model.Local;
import Model.Obra;
import java.sql.Connection;
import Model.Volume;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * @author João Denílson
 */
public class VolumeDAO {
    
    private Connection connection;
    //Construtor da coneceção com o SGBD
    public VolumeDAO() {
        this.connection = new ConnectionFactory().getConnection();
    }   
    
    ArtigoDAO artigodao = new ArtigoDAO();
    //Método Adciona o Volume
    //retornar o ID.
    public void adciona(Volume volume, int idObra, List<Artigo> artigos){
        int id = 0;
        
        String sql = "INSERT INTO volume(Ano_final, Edicao, Volume, Indicacao_responsabilidade, ISSN) "
                + "VALUES (?,?,?,?,?)";
        try {
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //seta os valores
            stmt.setString(1, volume.getAnoFinal());
            stmt.setString(2, volume.getEdicao());
            stmt.setString(3, volume.getVolume());
            stmt.setString(4, volume.getIndicacaoResponsabilidade());
            stmt.setInt(5, idObra);
            //executa a query
            stmt.execute();
            
            //Pegando o id do Volume cadastrado
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
            
            artigodao.adiciona(artigos, id);
            
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
    }
    
    //Retorna uma lista com os Volumes
    public List<Volume> listar() throws SQLException{
        //Analisar 
        String sql = "select * from volume v, obra o WHERE v.ISSN = o.ISSN";
        
        List<Volume> listar = new ArrayList<Volume>();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs =  stmt.executeQuery();
        
        while(rs.next()){
                Obra obra = new Obra();
                Volume volume = new Volume();
                
                volume.setId(rs.getInt("Id_volume"));
                volume.setVolume(rs.getString("Volume"));
                volume.setEdicao(rs.getString("Edicao"));
                volume.setAnoFinal(rs.getString("Ano_final"));
                //volume.setIndicacaoResponsabilidade(rs.getString("Indicacao_responsabilidade"));
                obra.setTitulo(rs.getString("Titulo"));
                //obra.setIssn(rs.getInt("ISSN"));
                
                //Adcionando a obra referente ao Volume
                volume.setObra(obra);
                
                listar.add(volume);       
        }
        return listar;
    }
    
    //Fazendo busca de um Volume
    public Volume buscarVolume(Integer idVolume) throws SQLException{
        String sql ="select * from volume v, obra o WHERE v.ISSN = o.ISSN AND Id_volume = ? ";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idVolume);
        ResultSet rs =  stmt.executeQuery();
        rs.beforeFirst();
        rs.next();
        
        Volume volume = new Volume();
        
        volume.setId(rs.getInt("Id_volume"));
        volume.setVolume(rs.getString("Volume"));
        volume.setEdicao(rs.getString("Edicao"));
        volume.setAnoFinal(rs.getString("Ano_final"));
        volume.setIndicacaoResponsabilidade(rs.getString("Indicacao_responsabilidade"));
        
        stmt.close();
        
        return volume;
    }
    
     //Pesquisar fazendo filtragem
    public List<Volume> pesquisar(Volume volume) throws SQLException{
        String sql = "select * from volume v, obra o "
        + "WHERE (v.ISSN = o.ISSN) AND (v.Volume = ? or v.Edicao = ? or v.Ano_final = ?)";
         
        List<Volume> listar = new ArrayList<Volume>();
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        stmt.setString(1, volume.getVolume());
        stmt.setString(2, volume.getEdicao());
        stmt.setString(3, volume.getAnoFinal());
        ResultSet rs =  stmt.executeQuery();
        
        while(rs.next()){
            Obra obra = new Obra();
            Volume vol = new Volume();

            vol.setId(rs.getInt("Id_volume"));
            vol.setVolume(rs.getString("Volume"));
            vol.setEdicao(rs.getString("Edicao"));
            vol.setAnoFinal(rs.getString("Ano_final"));
            //volume.setIndicacaoResponsabilidade(rs.getString("Indicacao_responsabilidade"));
            obra.setTitulo(rs.getString("Titulo"));
            //obra.setIssn(rs.getInt("ISSN"));
            
            //Adcionando a obra referente ao Volume
            vol.setObra(obra);

            listar.add(vol);       
        }
        return listar;
    }
    
    //Editando um Volume
    public void alterar(Volume volume) throws SQLException{
        String sql = "UPDATE volume SET Volume = ?, Edicao= ?, Ano_final= ?, Indicacao_responsabilidade= ? WHERE Id_volume = ?";
        
        //prepared statement para inserção
        PreparedStatement stmt = connection.prepareStatement(sql);
        //seta os valores
        stmt.setString(1, volume.getVolume());
        stmt.setString(2, volume.getEdicao());
        stmt.setString(3, volume.getAnoFinal());
        stmt.setString(4, volume.getIndicacaoResponsabilidade());
        stmt.setInt(5, volume.getId());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    //Excluindo Volume
    public void excluir(Integer idVolume) throws SQLException{  
        String sql = "DELETE FROM volume WHERE Id_volume = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idVolume);
        stmt.executeUpdate();  
    } 
}


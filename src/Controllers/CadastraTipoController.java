/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.TipoDAO;
import Model.Tipo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class CadastraTipoController implements Initializable {
    
    @FXML AnchorPane cadastrarTipo;
    @FXML Button btCancelarTipoObra;
    @FXML Button btCadastrarTipoObra;
    @FXML TextField tftext;
     
    @FXML public void cadastrarTipoObra()  throws IOException{
        Tipo t = new Tipo();
        TipoDAO  tipodao = new TipoDAO();
        
        //adcionando tipo
         System.err.println(tftext);
        //adcionando tipo
        t.setTipo(tftext.getText());
        
        tipodao.adiciona(t);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cadastrar Tipo de Obra");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarTipo.getChildren().setAll(a);
    }
    
    @FXML public void cancelarTipoObra() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarTipo.getChildren().setAll(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

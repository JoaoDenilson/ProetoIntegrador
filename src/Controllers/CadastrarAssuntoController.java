/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Dao.AssuntoDAO;
import Model.Assunto;
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
public class CadastrarAssuntoController implements Initializable {

    @FXML AnchorPane cadastrarAssunto;
    @FXML Button btCadastrarAssunto;
    @FXML Button btCancelarAssunto;
    @FXML TextField tftext;
     
    @FXML public void cadastrarAssunto() throws IOException{
        Assunto a = new Assunto();
        AssuntoDAO  assuntodao = new AssuntoDAO();
        
        System.err.println(tftext);
        //adcionando tipo
        a.setNome(tftext.getText());
        
        assuntodao.adiciona(a);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cadastrar Assunto");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane b = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarAssunto.getChildren().setAll(b);
    }
    
    @FXML public void cancelarAssunto() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarAssunto.getChildren().setAll(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

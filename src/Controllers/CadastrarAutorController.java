/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.AutorDAO;
import Model.Autor;
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
public class CadastrarAutorController implements Initializable {

    @FXML AnchorPane cadastrarAutor;
    @FXML Button btCadastrarAutor;
    @FXML Button btCancelarAutor;
    @FXML TextField tftext;
     
    @FXML public void cadastrarAutor() throws IOException{
        Autor aut = new Autor();
        AutorDAO  autordao = new AutorDAO();
        
        //adcionando tipo
        aut.setNome(tftext.getText());
        
        autordao.adiciona(aut);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro de Autor");
        alert.setHeaderText("Cadastrar Autor");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarAutor.getChildren().setAll(a);
    }
    
    @FXML public void cancelarAutor() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarAutor.getChildren().setAll(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

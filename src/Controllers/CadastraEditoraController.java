/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.EditoraDAO;
import Model.Editora;
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


public class CadastraEditoraController implements Initializable {

    @FXML AnchorPane cadastrarEditora;
    @FXML Button btCadastrarEditora;
    @FXML Button btCancelarEditora;
    @FXML TextField tftext;
    
    @FXML public void cadastrarEditora() throws IOException{
        Editora e = new Editora();
        EditoraDAO  editroadao = new EditoraDAO();
        
        System.err.println(tftext);
        //adcionando tipo
        e.setNome(tftext.getText());
        
        editroadao.adiciona(e);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cadastrar Editora");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane b = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarEditora.getChildren().setAll(b);
    }
    
    @FXML public void cancelarEditora() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarEditora.getChildren().setAll(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

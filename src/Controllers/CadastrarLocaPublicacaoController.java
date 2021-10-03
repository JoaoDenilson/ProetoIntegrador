/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.EditoraDAO;
import Dao.LocalDAO;
import Model.Editora;
import Model.Local;
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
public class CadastrarLocaPublicacaoController implements Initializable {

    @FXML AnchorPane cadastrarLocal;
    @FXML Button btCadastrarLocal;
    @FXML Button btCancelarLocal;
    @FXML TextField tftext;
    
    @FXML public void cadastrarLocal() throws IOException{
        Local l = new Local();
        LocalDAO  localdao = new LocalDAO();
        
        System.err.println(tftext);
        //adcionando tipo
        l.setLocal(tftext.getText());
        
        localdao.adiciona(l);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Cadastrar Local de Publicação");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane b = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarLocal.getChildren().setAll(b);
    }
    
    @FXML public void cancelarEditora() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        cadastrarLocal.getChildren().setAll(a);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

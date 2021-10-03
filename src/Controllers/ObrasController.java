/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class ObrasController implements Initializable {
    
    @FXML Button btCadastrarObra;
    @FXML AnchorPane Obra;
    @FXML public void cadastrarObra() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastrarObra.fxml"));
        Obra.getChildren().setAll(a);
    }
    
    @FXML Button btPesquisarObra;
    @FXML public void pesquisarObra() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/pesquisarObra.fxml"));
        Obra.getChildren().setAll(a);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

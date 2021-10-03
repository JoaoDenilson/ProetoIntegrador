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
public class VolumeController implements Initializable {
    
    @FXML Button btCadastrarVolume;
    @FXML AnchorPane Volume;
    @FXML public void cadastrarVolume() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/CadastrarVolume.fxml"));
        Volume.getChildren().setAll(a);
    }
    
    @FXML Button btPesquisarVolume;
    @FXML public void pesquisarVolume() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
        Volume.getChildren().setAll(a);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

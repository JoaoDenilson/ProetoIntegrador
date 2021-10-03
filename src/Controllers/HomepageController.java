/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class HomepageController implements Initializable {
    @FXML Button btObra;
    @FXML Button btArtigo;
    @FXML Button btVolume;
    @FXML Button btPremilimar;
    @FXML AnchorPane Principal;
     
    @FXML public void opcoesObra() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/obras.fxml"));
        Principal.getChildren().setAll(a);
    }
    
    @FXML public void opcoesArtigo() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarArtigo.fxml"));
        Principal.getChildren().setAll(a);
    }
    
    @FXML public void opcoesVolume() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
        Principal.getChildren().setAll(a);
    }
    @FXML public void opcoesPreliminar() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/preliminares.fxml"));
        Principal.getChildren().setAll(a);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
}

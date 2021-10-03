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
public class PreliminaresController implements Initializable {

    @FXML Button btTipoObra;
    @FXML Button btAssunto;
    @FXML Button btLocal;
    @FXML Button btEditora;
    @FXML Button btAutor;
    @FXML AnchorPane Preliminar;
    
    //Chama a tela de Cadastrar o Tipo
    @FXML public void cadastrarTipoObra() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastraTipo.fxml"));
        Preliminar.getChildren().setAll(a);
    }
    
    //Chama a tela de Cadastrar o Assunto
    @FXML public void cadastrarAssunto() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastrarAssunto.fxml"));
        Preliminar.getChildren().setAll(a);
    }
    
    //Chama a tela de Cadastrar o Local
     @FXML public void cadastrarLocal() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastrarLocalPublicacao.fxml"));
        Preliminar.getChildren().setAll(a);
    }
    
    //Chama a tela de Cadastrar a Editora
    @FXML public void cadastrarEditora() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastraEditora.fxml"));
        Preliminar.getChildren().setAll(a);
    }
    
    //Chama a tela de Cadastrar o Autor
    @FXML public void cadastrarAutor() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastrarAutor.fxml"));
        Preliminar.getChildren().setAll(a);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

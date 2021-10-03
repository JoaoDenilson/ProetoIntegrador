/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ArtigoDAO;
import Dao.AssuntoDAO;
import Dao.AutorDAO;
import Dao.VolumeDAO;
import Model.Artigo;
import Model.Assunto;
import Model.Autor;
import Model.Volume;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class PopupCadArtigoController implements Initializable {
    
    private Stage dialongStage;
    @FXML
    private AnchorPane popupcadastrarArtigo;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfPaginacao;

    @FXML
    private TextArea taResumo;
    
    @FXML
    private CheckComboBox<Autor> ccbAutor;
    @FXML
    private CheckComboBox<Assunto> ccbAssunto;
    
    @FXML
    private Button btSalvarArtigo;

    
    @FXML
    private void salvarArtigo(ActionEvent event) throws SQLException, IOException {
        Artigo artigo = new Artigo();
        ArtigoDAO artigodao = new ArtigoDAO();
        
        ObservableList<Assunto> Assuntos;
        ObservableList<Autor> Autores;
        
        artigo.setTitulo(tfTitulo.getText());
        artigo.setPaginacao(tfPaginacao.getText());
        artigo.setResumo(taResumo.getText());
        
        //Vem uma Lista de Autores
        Autores = ccbAutor.getCheckModel().getCheckedItems();
         //Vem uma Lista de Assuntos
        Assuntos = ccbAssunto.getCheckModel().getCheckedItems();
        
        List<Assunto> Listassunto = new ArrayList<Assunto>(Assuntos);
        List<Autor> Listautor = new ArrayList<Autor>(Autores);
        
        if (Listassunto.size() <= 6) {
            artigo.setAssunto(Listassunto);
        }
        
        if(Listautor.size() <= 10){
            artigo.setAutor(Listautor);
        }
        
        FXMLLoader loader = new FXMLLoader(CadastrarVolumeController.class.getResource("/View/CadastrarVolume.fxml"));
        Parent root = (Parent) loader.load();
        CadastrarVolumeController control = (CadastrarVolumeController)loader.getController();
        control.setArtigo(artigo);
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/CadastrarVolume.fxml"));
        popupcadastrarArtigo.getChildren().setAll(a);
               
    }
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AssuntoDAO assuntodao =new AssuntoDAO();
       AutorDAO autordao = new AutorDAO();
       
       List<Assunto> ListaAssunto = new ArrayList<Assunto>();
       List<Autor> listaAutor  = new ArrayList<Autor>();
       
       try {
        ListaAssunto = assuntodao.listaAssuntos();   
       } catch (SQLException ex) {
           Logger.getLogger(CadastrarObraController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       try {
        listaAutor = autordao.listaAutor();   
       } catch (SQLException ex) {
           Logger.getLogger(CadastrarObraController.class.getName()).log(Level.SEVERE, null, ex);
       }
        ObservableList<Assunto> assuntos = FXCollections.observableList(ListaAssunto);
       ccbAssunto.getItems().addAll(assuntos);
       
       ObservableList<Autor> autores = FXCollections.observableList(listaAutor);
       ccbAutor.getItems().addAll(autores);
    } 

    void setDialongStage(Stage stage) {
        this.dialongStage = stage;
    }
}

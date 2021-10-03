/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ArtigoDAO;
import Dao.AssuntoDAO;
import Dao.AutorDAO;
import Model.Artigo;
import Model.Assunto;
import Model.Autor;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class CadastrarArtigoController implements Initializable {

    @FXML
    private AnchorPane cadastrarArtigo;
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
    private Button btCancelarArtigo;
    @FXML
    private Button btCadastrarObra;
    @FXML
    private Button btProseguir;    
    
    static int idVolume = 0;
    @FXML
    private void cadastrarArtigo(ActionEvent event) throws SQLException, IOException {
        
        List<Artigo> listArtigo = new ArrayList<Artigo>();
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
        
        listArtigo.add(artigo);
        
        artigodao.adiciona(listArtigo, idVolume);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastrar Artigo");
        alert.setHeaderText("Cadastrar Artigo");
        alert.setContentText("Artigo Cadastrado !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarArtigo.fxml"));
        cadastrarArtigo.getChildren().setAll(a);
    }

    @FXML
    private void cancelarArtigo(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
        cadastrarArtigo.getChildren().setAll(a);
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

    void setId(int id) {
        this.idVolume = id;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.EditarObraController.edObra;
import Dao.ArtigoDAO;
import Dao.AssuntoDAO;
import Dao.AutorDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class EditarArtigoController implements Initializable {

    @FXML
    private AnchorPane editarArtigo;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfPaginacao;

    @FXML
    private Button btCadastrarArtigo;
    @FXML
    private CheckComboBox<Autor> ccbAutor;
    @FXML
    private CheckComboBox<Assunto> ccbAssunto;
    @FXML
    private Button btCancelarArtigo;
    @FXML
    private TextArea taResumo;
    
    static Artigo art = new Artigo();
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
       
       dadosObra();
    } 
    
    @FXML
     public void dadosObra() {
         tfTitulo.setText(art.getTitulo());
         tfPaginacao.setText(art.getPaginacao());
         taResumo.setText(art.getResumo());
     }
     
    @FXML
    private void editarArtigo() throws SQLException, IOException {
        Artigo artigo = new Artigo();
        ArtigoDAO artigodao = new ArtigoDAO();
        ObservableList<Assunto> Assuntos;
        ObservableList<Autor> Autores;
        
        artigo.setTitulo(tfTitulo.getText());
        artigo.setPaginacao(tfPaginacao.getText());
        artigo.setResumo(taResumo.getText());
        artigo.setId(art.getId());
        
        //Vem uma Lista de Autores
        Autores = ccbAutor.getCheckModel().getCheckedItems();
        if(Autores.size() == 0){
            CamposIncompletos();
        }
        //Vem uma Lista de Assuntos
        Assuntos = ccbAssunto.getCheckModel().getCheckedItems();
        if(Assuntos.size() == 0){
            CamposIncompletos();
        }
        
        List<Assunto> Listassunto = new ArrayList<Assunto>(Assuntos);
        List<Autor> Listautor = new ArrayList<Autor>(Autores);
        
        if (Listassunto.size() <= 6 && Listassunto.size() > 0) {
            artigo.setAssunto(Listassunto);
        }
        
        if(Listautor.size() <= 10 && Listautor.size() > 0){
            artigo.setAutor(Listautor);
        }
        
        artigodao.alterar(artigo);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pesquisar Artigo");
        alert.setContentText("Artigo alterado com Sucesso !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarArtigo.fxml"));
        editarArtigo.getChildren().setAll(a);
        
    }
    
    @FXML public void CamposIncompletos(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Editar Artigo");
        alert.setHeaderText("Editar Artigo");
        alert.setContentText("Todos os campos precisam ser preenchidos  !");
        alert.showAndWait();
    }
    
    @FXML
    private void cancelarArtigo(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarArtigo.fxml"));
        editarArtigo.getChildren().setAll(a);
    }

    void setArtigo(Artigo artigo) {
        this.art = artigo;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ArtigoDAO;
import Dao.AutorDAO;
import Dao.ObraDAO;
import Dao.VolumeDAO;
import Model.Artigo;
import Model.Autor;
import Model.Obra;
import Model.Tipo;
import Model.Volume;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class PesquisarArtigoController implements Initializable {

    @FXML
    private AnchorPane pesquisarArtigo;
    @FXML
    private TableView<Artigo> idtableArtigo;
    @FXML
    private TableColumn<?, ?> cId;
    @FXML
    private TableColumn<?, ?> cVolume;
    @FXML
    private TableColumn<?, ?> cTitulo;
    @FXML
    private TableColumn<?, ?> cPaginacao;
    
    @FXML
    private TextField tfTitulo;    
    @FXML
    private ComboBox<Autor> cbAutor;

    @FXML
    private Button btEditarObra;
    @FXML
    private Button btExcluirObra;
    @FXML
    private Button btPesquisar;     
    
    
    @FXML
    private void listarArtigo() throws SQLException {
        Artigo artigo = new Artigo();
        ArtigoDAO artigodao = new ArtigoDAO();
        
        List<Artigo> listadeArtigo = new ArrayList<Artigo>();
        listadeArtigo = artigodao.listar();
        
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        cTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        cPaginacao.setCellValueFactory(new PropertyValueFactory<>("paginacao"));
        
        listadeArtigo = FXCollections.observableArrayList(listadeArtigo);
        idtableArtigo.setItems((ObservableList<Artigo>) listadeArtigo);
        
    } 
    
    @FXML
    private void pesquisarArtigo() throws SQLException {
        Artigo artigo = new Artigo();
        ArtigoDAO artigodao = new ArtigoDAO();      
        Autor autor = new Autor();
        
        artigo.setTitulo(tfTitulo.getText());
        autor = cbAutor.getSelectionModel().getSelectedItem();   
        
        List<Artigo> listadeArtigo = new ArrayList<Artigo>();
        listadeArtigo = artigodao.pesquisar(artigo, autor);
        
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        cTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        cPaginacao.setCellValueFactory(new PropertyValueFactory<>("paginacao"));
        
        listadeArtigo = FXCollections.observableArrayList(listadeArtigo);
        idtableArtigo.setItems((ObservableList<Artigo>) listadeArtigo);
    }

    @FXML
    private void editarArtigo(ActionEvent event) throws IOException, SQLException {
        Artigo art = new Artigo();
        Artigo artigo = new Artigo();
        ArtigoDAO artigodao = new ArtigoDAO();
        
        art = idtableArtigo.getSelectionModel().getSelectedItem();
        if(art == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar Volume");
            alert.setHeaderText("Editar Volume");
            alert.setContentText("Nenhuma Artigo Foi Selecionado !");
            alert.showAndWait();
        } else {
            int id = art.getId();
            artigo = artigodao.buscarArtigo(id);
       
        FXMLLoader loader = new FXMLLoader(EditarArtigoController.class.getResource("/View/editarArtigo.fxml"));
        Parent root = (Parent) loader.load();
        EditarArtigoController control = (EditarArtigoController)loader.getController();
        control.setArtigo(artigo);
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/editarArtigo.fxml"));
        pesquisarArtigo.getChildren().setAll(a);
        
        }
    }

    @FXML
    private void excluirArtigo(ActionEvent event) throws SQLException, IOException {
        Artigo artigo = new Artigo();
        ArtigoDAO artigodao = new ArtigoDAO();
        artigo = idtableArtigo.getSelectionModel().getSelectedItem();
        if(artigo == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pesquisar Artigo");
            alert.setContentText("Nenhuma Artigo Foi Selecionada !");
            alert.showAndWait();
        }
        else{
         
        int id = artigo.getId();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir Artigo");
        alert.setContentText("Deseja excliur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            artigodao.excluir(id);
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarArtigo.fxml"));
            pesquisarArtigo.getChildren().setAll(a);
        } else {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarArtigo.fxml"));
            pesquisarArtigo.getChildren().setAll(a);
            }
        }     
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AutorDAO autordao = new AutorDAO();
        List<Autor> listaAutor  = new ArrayList<Autor>();
       try {
        listaAutor = autordao.listaAutor();   
       } catch (SQLException ex) {
           Logger.getLogger(CadastrarObraController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       ObservableList<Autor> autores = FXCollections.observableList(listaAutor);
       cbAutor.setItems(autores);
       
        try {
            listarArtigo();
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarArtigoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}

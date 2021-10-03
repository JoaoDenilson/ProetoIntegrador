/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ArtigoDAO;
import Dao.ObraDAO;
import Dao.VolumeDAO;
import Model.Artigo;
import Model.Obra;
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
import javafx.beans.property.SimpleStringProperty;
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
public class PesquisarVolumeController implements Initializable {

    @FXML
    private AnchorPane pesquisarVolume;
    @FXML
    private TableView<Volume> idtableVolumes;
    @FXML
    private TableColumn<Volume, String> cObra;
    @FXML
    private TableColumn<?, ?> cVolume;
    @FXML
    private TableColumn<?, ?> cEdicao;
    @FXML
    private TableColumn<?, ?> cAno;
    
    @FXML
    private Button btPesquisar;
    @FXML
    private Button btEditarObra;
    @FXML
    private Button btVoltar;
    @FXML
    private Button btExcluirObra;
    @FXML
    private Button btCadastrarArtigo;
    
    @FXML
    private TextField tfEdicao;
    @FXML
    private TextField tfVolume;
    @FXML
    private TextField tfAno;
    
    private void ListarVolume() throws SQLException {
        VolumeDAO volumedao = new VolumeDAO();
        
        List<Volume> listadeVolume = new ArrayList<Volume>();
        listadeVolume = volumedao.listar();
        
        cObra.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getObra().getTitulo()));
        //cObra.setCellValueFactory(new PropertyValueFactory<>("titulo"));//Não está mostrando na Tabela
        cVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        cEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
        cAno.setCellValueFactory(new PropertyValueFactory<>("anoFinal"));
        
        listadeVolume = FXCollections.observableArrayList(listadeVolume);
        idtableVolumes.setItems((ObservableList<Volume>) listadeVolume);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ListarVolume();
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarVolumeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML
    private void CadastrarArtigo(ActionEvent event) throws IOException {
        Volume vol = new Volume();
        
        vol = idtableVolumes.getSelectionModel().getSelectedItem();
        if(vol == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar Volume");
            alert.setHeaderText("Editar Volume");
            alert.setContentText("Nenhuma Volume Foi Selecionado !");
            alert.showAndWait();
        } else {
            int id = vol.getId();
            
            FXMLLoader loader = new FXMLLoader(CadastrarArtigoController.class.getResource("/View/CadastrarArtigo.fxml"));
            Parent root = (Parent) loader.load();
            CadastrarArtigoController control = (CadastrarArtigoController)loader.getController();
            control.setId(id);

            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/CadastrarArtigo.fxml"));
            pesquisarVolume.getChildren().setAll(a);
        }
    
    }
    
    @FXML
    private void PesquisarVolume(ActionEvent event) throws SQLException {
        Volume volume = new Volume();
        VolumeDAO volumedao = new VolumeDAO();
        
        volume.setAnoFinal(tfAno.getText());
        volume.setVolume(tfVolume.getText());
        volume.setEdicao(tfEdicao.getText());
        
        List<Volume> listadeVolumes = new ArrayList<Volume>();
        listadeVolumes = volumedao.pesquisar(volume);
        
        cObra.setCellValueFactory(c->new SimpleStringProperty(c.getValue().getObra().getTitulo()));
        cVolume.setCellValueFactory(new PropertyValueFactory<>("volume"));
        cEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
        cAno.setCellValueFactory(new PropertyValueFactory<>("anoFinal"));
        
        listadeVolumes = FXCollections.observableArrayList(listadeVolumes);
        idtableVolumes.setItems((ObservableList<Volume>) listadeVolumes);
        
    }

    @FXML
    private void EditarVolume() throws SQLException, IOException {
        Volume vol = new Volume();
        Volume volume = new Volume();
        VolumeDAO volumedao = new VolumeDAO();
        
        vol = idtableVolumes.getSelectionModel().getSelectedItem();
        if(vol == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar Volume");
            alert.setHeaderText("Editar Volume");
            alert.setContentText("Nenhuma Volume Foi Selecionado !");
            alert.showAndWait();
        } else {
            int id = vol.getId();
            volume = volumedao.buscarVolume(id);
       
        FXMLLoader loader = new FXMLLoader(EditarVolumeController.class.getResource("/View/EditarVolume.fxml"));
        Parent root = (Parent) loader.load();
        EditarVolumeController control = (EditarVolumeController)loader.getController();
        control.setVolume(volume);
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/EditarVolume.fxml"));
        pesquisarVolume.getChildren().setAll(a);
        
        }
    }
    
    @FXML
    private void cancelarPesquisa() throws IOException {
        /*
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/FXMLhomepage.fxml"));
        pesquisarVolume.getChildren().setAll(a);
        */
    }

    @FXML
    private void ExcluirVolume(ActionEvent event) throws SQLException, IOException {
        Volume volume = new Volume();
        VolumeDAO volumedao = new VolumeDAO();
        volume = idtableVolumes.getSelectionModel().getSelectedItem();
        if(volume == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pesquisar Volume");
            alert.setContentText("Nenhuma Volume Foi Selecionada !");
            alert.showAndWait();
        }
        else{
         
        int id = volume.getId();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir Volume");
        alert.setContentText("Deseja excliur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            volumedao.excluir(id);
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
           pesquisarVolume.getChildren().setAll(a);
        } else {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
            pesquisarVolume.getChildren().setAll(a);
            }
        }     
    }
    
}

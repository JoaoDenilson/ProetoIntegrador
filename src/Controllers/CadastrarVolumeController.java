/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.ObraDAO;
import Dao.VolumeDAO;
import Model.Artigo;
import Model.Obra;
import Model.Volume;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class CadastrarVolumeController implements Initializable {

    @FXML
    private AnchorPane cadastrarVolume;
    
    @FXML
    private TableView<Artigo> idtableArtigos;
    
    @FXML
    TableColumn<?, ?> cTitulo;
 
    @FXML
    TableColumn<?, ?> cPaginacao;
    
    @FXML
    private TextField tfVolume;
    @FXML
    private TextField tfEdicao;
    @FXML
    private TextField tfAnoFinal;
    @FXML
    private TextField tfIndicacao;
    //Irá salvar o ID da Obra
    static int idObra = 0;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelarVolume;
    @FXML
    private Button btAddArtigo;
    
    //Recebde o ID da Obra
    void setId(int id) {
        this.idObra = id;
    }

     static List<Artigo> osartigos = new ArrayList<Artigo>();
    
    void setArtigo(Artigo artigo) {
        this.osartigos.add(artigo);
    }
    
    @FXML
    private void cadastrarVolume(ActionEvent event) throws IOException {
        Volume volume =new Volume();
       //Retornar o ID 
        int idVol = 0;
         //Chamando a Persistencia de dados
        VolumeDAO volumedao = new VolumeDAO();
        
        //adcionando tipo
        volume.setVolume(tfVolume.getText());
        volume.setEdicao(tfEdicao.getText());
        volume.setAnoFinal(tfAnoFinal.getText());
        volume.setIndicacaoResponsabilidade(tfIndicacao.getText());
        
        //Falta pegar os artigos
        if(osartigos.size() != 0){
             volumedao.adciona(volume, idObra, osartigos);
             this.osartigos = new ArrayList<Artigo>();
             
             AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/pesquisarObra.fxml"));
            cadastrarVolume.getChildren().setAll(a);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adicionar Artigo");
            alert.setHeaderText("Adicionar Artigo");
            alert.setContentText("Nenhuma Artigo foi ADICIONADO !");
            alert.showAndWait();
        }            
    }
    
    @FXML
    private void cadastrarArtigo() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(PopupCadArtigoController.class.getResource("/View/popupCadArtigo.fxml"));
        Parent root = (Parent) loader.load();
        PopupCadArtigoController control = (PopupCadArtigoController)loader.getController();
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/popupCadArtigo.fxml"));
        cadastrarVolume.getChildren().setAll(a);
       
    }
    
    //Listando as Obras que foram adcionandas ao Volume
    @FXML
    private void ListadeArtigos() {  
        ObservableList<Artigo> obsArtigo;

        cTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        cPaginacao.setCellValueFactory(new PropertyValueFactory<>("paginacao"));
        
        List<Artigo> list = osartigos;
        obsArtigo = FXCollections.observableArrayList(list);
        idtableArtigos.setItems(obsArtigo);
       
    }
    
    @FXML
    private void cancelarVolume(ActionEvent event) throws IOException {
        this.osartigos = new ArrayList<Artigo>();
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/cadastrarObra.fxml"));
        cadastrarVolume.getChildren().setAll(a);
    }

     //Initializes the controller class.  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(osartigos.size() != 0){
            ListadeArtigos();
         }
    } 

}

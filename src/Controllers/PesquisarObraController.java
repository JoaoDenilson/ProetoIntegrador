/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.EditoraDAO;
import Dao.LocalDAO;
import Dao.ObraDAO;
import Model.Editora;
import Model.Local;
import Model.Obra;
import Model.Tipo;
import inicial.FXMLDocumentController;
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
import javafx.scene.control.CheckBox;
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
public class PesquisarObraController implements Initializable {
    
    @FXML AnchorPane pesquisarObra;
    @FXML
    //TableView idtableObras;
    TableView<Obra> idtableObras;
    @FXML
    private TableColumn<?, ?> cIssn;
    @FXML
    private TableColumn<?, ?> cTitulo;
    @FXML
    private TableColumn<?, ?> cEditora;
    @FXML
    private TableColumn<?, ?> cLocal;
    @FXML
    private TextField tfTitulo;
    
    @FXML Button btPesquisar;  
    @FXML Button btVoltar;
    @FXML Button btEditarObra;
    @FXML Button btExcluirObra;
    @FXML Button btCadVolume;
    
    @FXML ComboBox<Editora> cbEditora;
    @FXML ComboBox<Local> cbLocal;
    
    private ObservableList<Obra> ListadeObras;

    @FXML
    private void PesquisarObra()throws IOException, SQLException {
        Obra obra = new Obra();
        Editora edit = new Editora();
        Local local = new Local();
        ObraDAO obradao = new ObraDAO();
        
        //adcionando tipo
        obra.setTitulo(tfTitulo.getText());
        edit = cbEditora.getSelectionModel().getSelectedItem();
        //System.err.println(edit);
        local = cbLocal.getSelectionModel().getSelectedItem();
        
        obra.setEditora(edit);
        obra.setLocalpubli(local);
        
        List<Obra> listadeObras = new ArrayList<Obra>();
        listadeObras =  obradao.pesquisar(obra);
        
        cIssn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        cTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
        cEditora.setCellValueFactory(new PropertyValueFactory<>("Editora"));
        cLocal.setCellValueFactory(new PropertyValueFactory<>("localpubli"));
        
        ListadeObras = FXCollections.observableArrayList(listadeObras);
        idtableObras.setItems(ListadeObras);
    }
    
    @FXML
    private void ExcluirObra() throws IOException, SQLException {
        Obra obra = new Obra();
        ObraDAO obradao = new ObraDAO();
        obra = idtableObras.getSelectionModel().getSelectedItem();
        if(obra == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pesquisar Obra");
            alert.setContentText("Nenhuma Obra Foi Selecionada !");
            alert.showAndWait();
        }
        else{
         
        int id = obra.getIssn();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir Obra");
        alert.setContentText("Deseja excliur ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            obradao.excluir(id);
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/pesquisarObra.fxml"));
            pesquisarObra.getChildren().setAll(a);
        } else {
            AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/pesquisarObra.fxml"));
            pesquisarObra.getChildren().setAll(a);
            }
        }     
    }
    
    @FXML
    private void EditarObra() throws IOException, SQLException {
        Obra o = new Obra();
        Obra obra = new Obra();
        ObraDAO obradao = new ObraDAO();
        o = idtableObras.getSelectionModel().getSelectedItem();
        if(o == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar Obra");
            alert.setHeaderText("Editar Obra");
            alert.setContentText("Nenhuma Obra Foi Selecionada !");
            alert.showAndWait();
        } else {
            int id = o.getIssn();
            obra = obradao.buscarObra(id);
        
        FXMLLoader loader = new FXMLLoader(EditarObraController.class.getResource("/View/editarObra.fxml"));
        Parent root = (Parent) loader.load();
        EditarObraController control = (EditarObraController)loader.getController();
        control.setObra(obra);
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/editarObra.fxml"));
        pesquisarObra.getChildren().setAll(a);
        }        
    }
    
    private void ListarObra() {
        //Obra obra = new Obra();
        ObraDAO obradao = new ObraDAO();
        
        List<Obra> listadeObras = new ArrayList<Obra>();
        listadeObras = obradao.listar();
        
        cIssn.setCellValueFactory(new PropertyValueFactory<>("issn"));
        cTitulo.setCellValueFactory(new PropertyValueFactory<>("Titulo"));
        cEditora.setCellValueFactory(new PropertyValueFactory<>("Editora"));
        cLocal.setCellValueFactory(new PropertyValueFactory<>("localpubli"));
        
        ListadeObras = FXCollections.observableArrayList(listadeObras);
        idtableObras.setItems(ListadeObras);
       
    }
    
    //Voltar p/ a View anterior
    @FXML public void cancelarPesquisa() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/obras.fxml"));
        pesquisarObra.getChildren().setAll(a);
    }
    
    //Cadastrar Volume
    @FXML
    private void cadastrarVolume() throws IOException, SQLException {
        Obra o = new Obra();
        Obra obra = new Obra();
        ObraDAO obradao = new ObraDAO();
        o = idtableObras.getSelectionModel().getSelectedItem();
        if(o == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Editar Obra");
            alert.setHeaderText("Editar Obra");
            alert.setContentText("Nenhuma Obra Foi Selecionada !");
            alert.showAndWait();
        } else {
            int id = o.getIssn();
            obra = obradao.buscarObra(id);     
        
            
        FXMLLoader loader = new FXMLLoader(CadastrarVolumeController.class.getResource("/View/CadastrarVolume.fxml"));
        Parent root = (Parent) loader.load();
        CadastrarVolumeController control = (CadastrarVolumeController)loader.getController();
        control.setId(id);
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/CadastrarVolume.fxml"));
        pesquisarObra.getChildren().setAll(a);
        }        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Fazendo a chamada dos dados
        ObraDAO obradao = new ObraDAO();
        EditoraDAO editoradao = new EditoraDAO();
        LocalDAO localdao = new LocalDAO();
        
        List<Editora> listaEditora = new ArrayList<Editora>();
        List<Local> listaLocal = new ArrayList<Local>();
        
        try {
        listaEditora = editoradao.listaEditora();   
        } catch (SQLException ex) {
           Logger.getLogger(CadastrarObraController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       try {
        listaLocal = localdao.listaLocal();   
       } catch (SQLException ex) {
           Logger.getLogger(CadastrarObraController.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       ObservableList<Editora> editoras = FXCollections.observableList(listaEditora);
       cbEditora.setItems(editoras);
       
       ObservableList<Local> locais = FXCollections.observableList(listaLocal);
       cbLocal.setItems(locais);
       
        //Chamando a lista de Obras
        ListarObra(); 
        
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.AssuntoDAO;
import Dao.AutorDAO;
import Dao.EditoraDAO;
import Dao.LocalDAO;
import Dao.ObraDAO;
import Dao.TipoDAO;
import Model.Assunto;
import Model.Autor;
import Model.Editora;
import Model.Local;
import Model.Obra;
import Model.Tipo;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class CadastrarObraController implements Initializable {

    @FXML
    private AnchorPane cadastrarObra;
    @FXML
    private TextField tfTitulo;
    @FXML
    private ComboBox<Editora> cbEditora;
    @FXML
    private TextField tfSubtitulo;
    @FXML
    private TextField tfIssn;
    @FXML
    private TextField tfAnoInicial;
    @FXML
    private TextField tfNotacaoAutor;
    @FXML
    private TextField tfIndicacao;
    @FXML
    private TextField tfClassificacao;
    
    @FXML
    private TextField tfAnoFinal;
    @FXML
    private CheckComboBox<Autor> ccbAutor;
    @FXML
    private CheckComboBox<Assunto> ccbAssunto;
    @FXML
    private ComboBox<Local> cbLocal;
    @FXML
    private ComboBox<Tipo> cbTipo;
    
    @FXML
    private Button btCadastrarObra;;
    @FXML
    private Button btCancelarObra;
    @FXML
    private Button btProsseguir;
            
    //---------------------  MÉTODOS  --------------------------//
    
public void CamposIncompletos(){
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Editar Obra");
    alert.setHeaderText("Editar Obra");
    alert.setContentText("Todos os campos precisan ser preenchidos  !");
    alert.showAndWait();
}
 //Cadastrando Obra
    @FXML public Obra cadastrarObra() throws IOException, SQLException{
        ObservableList<Assunto> Assuntos;
        ObservableList<Autor> Autores;
        Editora edit = new Editora();
        Local local = new Local();
        Tipo tipo = new Tipo();
        Obra obra = new Obra();
        //Chamando a Persistencia de dados
        ObraDAO obradao = new ObraDAO();
        
        //adcionando tipo
        
        obra.setTitulo(tfTitulo.getText());
        
        
        obra.setSubTitulo(tfSubtitulo.getText());
        if(obra.getSubTitulo() == null){
            CamposIncompletos();
        }
        obra.setIssn(Integer.parseInt(tfIssn.getText()));
        
        obra.setAnoInicial(tfAnoInicial.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setAnoFinal(tfAnoFinal.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setNotacaoAutor(tfNotacaoAutor.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setIndicacaoResponsabilidade(tfIndicacao.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setClassificacao(tfClassificacao.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        edit = cbEditora.getSelectionModel().getSelectedItem();
        //System.err.println(edit);
        local = cbLocal.getSelectionModel().getSelectedItem();
        tipo = cbTipo.getSelectionModel().getSelectedItem();
        //Vem uma Lista de Autores
        Autores = ccbAutor.getCheckModel().getCheckedItems();
         //Vem uma Lista de Assuntos
        Assuntos = ccbAssunto.getCheckModel().getCheckedItems();
        
        List<Assunto> Listassunto = new ArrayList<Assunto>(Assuntos);
        List<Autor> Listautor = new ArrayList<Autor>(Autores);
        
        if (Listassunto.size() <= 6) {
            obra.setAssunto(Listassunto);
        }
        
        if(Listautor.size() <= 10){
            obra.setAutor(Listautor);
        }
        
        obra.setEditora(edit);
        obra.setLocalpubli(local);
        obra.setTipoobra(tipo);
        
        obradao.adiciona(obra);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro da Obra");
        alert.setHeaderText("Cadastrar Obra");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/obras.fxml"));
        cadastrarObra.getChildren().setAll(a);
        
        return obra;
    }
    
     //Cadastrando Obra e preossegui
    @FXML
    public void prosseguirObra() throws IOException, SQLException{
        ObservableList<Assunto> Assuntos;
        ObservableList<Autor> Autores;
        Editora edit = new Editora();
        Local local = new Local();
        Tipo tipo = new Tipo();
        Obra obra = new Obra();
        //Chamando a Persistencia de dados
        ObraDAO obradao = new ObraDAO();
        
        //adcionando tipo
        
        obra.setTitulo(tfTitulo.getText());
        
        
        obra.setSubTitulo(tfSubtitulo.getText());
        if(obra.getSubTitulo() == null){
            CamposIncompletos();
        }
        obra.setIssn(Integer.parseInt(tfIssn.getText()));
        
        obra.setAnoInicial(tfAnoInicial.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setAnoFinal(tfAnoFinal.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setNotacaoAutor(tfNotacaoAutor.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setIndicacaoResponsabilidade(tfIndicacao.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        obra.setClassificacao(tfClassificacao.getText());
        if(obra.getTitulo() == null){
            CamposIncompletos();
        }
        
        edit = cbEditora.getSelectionModel().getSelectedItem();
        //System.err.println(edit);
        local = cbLocal.getSelectionModel().getSelectedItem();
        tipo = cbTipo.getSelectionModel().getSelectedItem();
        //Vem uma Lista de Autores
        Autores = ccbAutor.getCheckModel().getCheckedItems();
         //Vem uma Lista de Assuntos
        Assuntos = ccbAssunto.getCheckModel().getCheckedItems();
        
        List<Assunto> Listassunto = new ArrayList<Assunto>(Assuntos);
        List<Autor> Listautor = new ArrayList<Autor>(Autores);
        
        if (Listassunto.size() <= 6) {
            obra.setAssunto(Listassunto);
        }
        
        if(Listautor.size() <= 10){
            obra.setAutor(Listautor);
        }
        
        obra.setEditora(edit);
        obra.setLocalpubli(local);
        obra.setTipoobra(tipo);
        
        int id = obradao.adiciona(obra);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cadastro da Obra");
        alert.setHeaderText("Cadastrar Obra");
        alert.setContentText("Obra cadastrada com Sucesso !");
        alert.showAndWait();
        //Chamando a tela de Volume
        FXMLLoader loader = new FXMLLoader(CadastrarVolumeController.class.getResource("/View/CadastrarVolume.fxml"));
        Parent root = (Parent) loader.load();
        CadastrarVolumeController control = (CadastrarVolumeController)loader.getController();
        control.setId(id);
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/CadastrarVolume.fxml"));
        cadastrarObra.getChildren().setAll(a);
    }

    //Voltar p/ a View anterior
    @FXML public void cancelarObra(ActionEvent event) throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/obras.fxml"));
        cadastrarObra.getChildren().setAll(a);
    }
    
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AssuntoDAO assuntodao =new AssuntoDAO();
       AutorDAO autordao = new AutorDAO();
       EditoraDAO editoradao = new EditoraDAO();
       LocalDAO localdao = new LocalDAO();
       TipoDAO tipodao = new TipoDAO();
       
       List<Assunto> ListaAssunto = new ArrayList<Assunto>();
       List<Autor> listaAutor  = new ArrayList<Autor>();
       List<Editora> listaEditora = new ArrayList<Editora>();
       List<Local> listaLocal = new ArrayList<Local>();
       List<Tipo> listaTipo = new ArrayList<Tipo>();
       
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
       
       try {
        listaTipo = tipodao.listaTipo();   
       } catch (SQLException ex) {
           Logger.getLogger(CadastrarObraController.class.getName()).log(Level.SEVERE, null, ex);
       }  
      
       ObservableList<Assunto> assuntos = FXCollections.observableList(ListaAssunto);
       ccbAssunto.getItems().addAll(assuntos);
       
       ObservableList<Autor> autores = FXCollections.observableList(listaAutor);
       ccbAutor.getItems().addAll(autores);
       
       ObservableList<Editora> editoras = FXCollections.observableList(listaEditora);
       cbEditora.setItems(editoras);
       
       ObservableList<Local> locais = FXCollections.observableList(listaLocal);
       cbLocal.setItems(locais);
       
       ObservableList<Tipo> tipos = FXCollections.observableList(listaTipo);
       cbTipo.setItems(tipos);
    }    

}

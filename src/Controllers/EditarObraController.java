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
public class EditarObraController implements Initializable {

    @FXML
    private AnchorPane editarObra;
    @FXML
    private TextField tfTitulo;
    @FXML

    private TextField tfSubtitulo;
    @FXML
    private TextField tfIssn;
    @FXML
    private TextField tfAnoInicial;
    @FXML
    private TextField tfAnoFinal;
    @FXML
    private TextField tfNotacaoAutor;
    @FXML
    private TextField tfIndicacao;
    @FXML
    private TextField tfClassificacao;
    @FXML
    private Button btCadastrarObra;
    
    @FXML
    private CheckComboBox<Autor> ccbAutor;
    @FXML
    private CheckComboBox<Assunto> ccbAssunto;
    @FXML
    private ComboBox<Editora> cbEditora;
    @FXML
    private ComboBox<Local> cbLocal;
    @FXML
    private ComboBox<Tipo> cbTipo;
    @FXML
    private Button btCancelarObra;
    @FXML
    private Button btSalvarObra;
    
    //Criando objeto que vai receber
    static Obra edObra = new Obra();
    
    @FXML public void CamposIncompletos(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Editar Obra");
        alert.setHeaderText("Editar Obra");
        alert.setContentText("Todos os campos precisam ser preenchidos  !");
        alert.showAndWait();
    }
     
    //Salvar a edição
    @FXML
    private void salvarObra() throws SQLException, IOException {
        
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
        obra.setAnoInicial(tfAnoInicial.getText());
        obra.setAnoFinal(tfAnoFinal.getText());
        obra.setNotacaoAutor(tfNotacaoAutor.getText());
        obra.setIndicacaoResponsabilidade(tfIndicacao.getText());
        obra.setClassificacao(tfClassificacao.getText());
        obra.setIssn(edObra.getIssn());
        
        edit = cbEditora.getSelectionModel().getSelectedItem();
        //System.err.println(edit);
        local = cbLocal.getSelectionModel().getSelectedItem();
        tipo = cbTipo.getSelectionModel().getSelectedItem();
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
            obra.setAssunto(Listassunto);
        }
        
        if(Listautor.size() <= 10 && Listautor.size() > 0){
            obra.setAutor(Listautor);
        }
        
        obra.setEditora(edit);
        obra.setLocalpubli(local);
        obra.setTipoobra(tipo);
        
        obradao.alterar(obra);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Editar Obra");
        alert.setContentText("Dados Salvos com Sucesso !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane)FXMLLoader.load(getClass().getResource("/View/pesquisarObra.fxml"));
        editarObra.getChildren().setAll(a);
     
    }
    
    //Voltar p/ a View anterior
    @FXML
    private void cancelarObra() throws IOException {
         AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/pesquisarObra.fxml"));
        editarObra.getChildren().setAll(a);
    }
    
    @FXML
    public void setObra(Obra obra) {
        this.edObra = obra;     //Setando o que recebe no que foi Criado
    }
    //Setando nos campos
    @FXML
    public void dadosObra() {
        //tfIssn.setText(String.valueOf(edObra.getIssn()));
        tfTitulo.setText(edObra.getTitulo());
        tfSubtitulo.setText(edObra.getSubTitulo());
        tfAnoInicial.setText(edObra.getAnoInicial());
        tfAnoFinal.setText(edObra.getAnoFinal());
        tfClassificacao.setText(edObra.getClassificacao());
        tfNotacaoAutor.setText(edObra.getNotacaoAutor());
        tfIndicacao.setText(edObra.getIndicacaoResponsabilidade());
        
        cbEditora.getSelectionModel().select(edObra.getEditora());
        cbLocal.getSelectionModel().select(edObra.getLocalpubli());
        cbTipo.getSelectionModel().select(edObra.getTipoobra());
        
        //ccbAutor.getCheckModel().check();
    }

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
       
      //Chamando a função
      dadosObra();
    } 
}

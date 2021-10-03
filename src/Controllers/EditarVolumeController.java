/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Dao.VolumeDAO;
import Model.Volume;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author João Denílson
 */
public class EditarVolumeController implements Initializable {

    @FXML
    private AnchorPane editarVolume;
    @FXML
    private TextField tfVolume;
    @FXML
    private TextField tfEdicao;
    @FXML
    private TextField tfAnoFinal;
    @FXML
    private TextField tfIndicacao;
    @FXML
    private Button btSalvar;
    @FXML
    private Button btCancelarVolume;
    
    static Volume vol = new Volume();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dadosVolume();
    }    

    @FXML
    private void cadastrarVolume(ActionEvent event) throws SQLException, IOException {
        Volume volume =new Volume();
        VolumeDAO volumedao = new VolumeDAO();
        
        //adcionando tipo
        volume.setVolume(tfVolume.getText());
        volume.setEdicao(tfEdicao.getText());
        volume.setAnoFinal(tfAnoFinal.getText());
        volume.setIndicacaoResponsabilidade(tfIndicacao.getText());
        volume.setId(vol.getId());
        
        volumedao.alterar(volume);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pesquisar Obra");
        alert.setContentText("Volume alterado com Sucesso !");
        alert.showAndWait();
        
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
        editarVolume.getChildren().setAll(a);
    }

    @FXML
    private void cancelarVolume(ActionEvent event) throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/ListarVolume.fxml"));
        editarVolume.getChildren().setAll(a);
    }
    
    public void dadosVolume(){
        tfVolume.setText(vol.getVolume());
        tfEdicao.setText(vol.getEdicao());
        tfAnoFinal.setText(vol.getAnoFinal());
        tfIndicacao.setText(vol.getIndicacaoResponsabilidade());
    }
    void setVolume(Volume volume) {
       this.vol = volume;
    }
    
}

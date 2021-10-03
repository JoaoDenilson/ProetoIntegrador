/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inicial;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author marco
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField tfUsuario;
    @FXML
    private PasswordField pfSenha;

    @FXML
    private Text textMensagem;
    @FXML
    private Button btLogin;

    @FXML
    private void handleBtLogin() {

        if (tfUsuario.getText().equals("root") && pfSenha.getText().equals("root")) {
            try {
                textMensagem.setText("Bem vindo root!");
                textMensagem.setFill(Color.GREEN);
                
                Stage stageLogin = (Stage) btLogin.getScene().getWindow(); //usa o botão da tela de login para pegar o objeto Stage
                stageLogin.close(); //fecha a tela de login 
                //chama a tela principal da aplicação
                Stage stage = new Stage();
                URL url = new File("src/View/FXMLhomepage.fxml").toURL();
                Parent root = FXMLLoader.load(url);
                //Parent root = FXMLLoader.load(getClass().getResource("../View/FXMLhomepage.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            textMensagem.setText("Login não permitido");
            textMensagem.setFill(Color.RED);
        }
    }

    @FXML
    private void tfHandleKeyPressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            handleBtLogin();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textMensagem.setText("");
        textMensagem.setFill(Color.BLACK);
    }

}

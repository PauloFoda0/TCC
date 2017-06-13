/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import Main.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import Main.Cadastro;
import Main.TelaPrincipal;
import Model.Criptografia;
import Model.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Vitor
 */
public class LoginController implements Initializable {

    @FXML
    private Button btLogin;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private Button btCadastro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Action por meio do clique do Mouse
        btCadastro.setOnMouseClicked((MouseEvent e) -> {
            Cadastro();
        });
        btLogin.setOnMouseClicked((MouseEvent e) -> {
            if (tfLogin == null || pfSenha == null) {
                Alert vazio = new Alert(Alert.AlertType.ERROR);
                vazio.setHeaderText("CAMPOS VAZIOS");
                vazio.setContentText("Preencher todos os campos");
                vazio.showAndWait();
            } else {
                Login();
            }
        });
        //Action por meio do clique do Botao ENTER
        btCadastro.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Cadastro();
            }
        });
        btLogin.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                if (tfLogin == null || pfSenha == null) {   //Verifica se campos estao nulos
                    Alert vazio = new Alert(Alert.AlertType.ERROR);
                    vazio.setHeaderText("CAMPOS VAZIOS");
                    vazio.setContentText("Preencher todos os campos");
                    vazio.showAndWait();
                } else {
                    Login();
                }  
            }
        });
        tfLogin.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                if (tfLogin == null || pfSenha == null) { //Verifica se campos estao nulos
                    Alert vazio = new Alert(Alert.AlertType.ERROR);
                    vazio.setHeaderText("CAMPOS VAZIOS");
                    vazio.setContentText("Preencher todos os campos");
                    vazio.showAndWait();
                } else {
                    Login();
                }
            }
        });
        pfSenha.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                if (tfLogin == null || pfSenha == null) { //Verifica se campos estao nulos
                    Alert vazio = new Alert(Alert.AlertType.ERROR);
                    vazio.setHeaderText("CAMPOS VAZIOS");
                    vazio.setContentText("Preencher todos os campos");
                    vazio.showAndWait();

                } else {
                    Login();
                }

            }
        });
    }
    //Abre a tela de Cadastro caso solicitado
    public void Cadastro() {
        Cadastro cad = new Cadastro();
        try {
            Login.getStage().close();
            cad.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //verifica se existe um usuario cadastrado no sistema
    //caso exista encaminha para a tela Principal
    //caso nao exista apresenta uma mensagem de erro
    public void Login() {
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> user = FXCollections.observableArrayList(dao.getLista());
        for (int i = 0; i < user.size(); i++) {
            if (tfLogin.getText().equals(user.get(i).getLogin())) {
                if (Criptografia.criptografar(pfSenha.getText()).equals(user.get(i).getSenha())) {
                    
                    TelaPrincipal tela = new TelaPrincipal();
                    try {
                        
                        tela.start(new Stage());
                        Login.getStage().close();
                        
                        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setHeaderText("SEJA BEM-VINDO");
                        alert.showAndWait();
                        
                        i = user.size();

                    } catch (Exception ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (i + 1 == user.size()) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setHeaderText("Usuario incorreto");
                erro.setContentText("Verificar Login e Senha");
                erro.showAndWait();
            }
        }
    }
}

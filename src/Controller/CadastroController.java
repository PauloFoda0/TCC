/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import Main.Cadastro;
import Main.Login;
import Model.Criptografia;
import Model.Usuario;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Vitor
 */
public class CadastroController implements Initializable {

    @FXML
    private Button btCadastrar;

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btDeletar;

    @FXML
    private ImageView imgFoto;

    private String caminhoFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Action por meio do clique do Mouse
        btAlterar.setOnMouseClicked((MouseEvent e) -> {
            AlteraFoto();
        });
        btCancelar.setOnMouseClicked((MouseEvent e) -> {
            Cancelar();
        });
        btCadastrar.setOnMouseClicked((MouseEvent e) -> {
            if (tfLogin == null || tfLogin.getText().trim().isEmpty() //Verifica se campos estao nulos
                    || pfSenha == null || pfSenha.getText().trim().isEmpty()) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setHeaderText("CAMPOS VAZIOS");
                erro.setContentText("Preencha todos os campos");
                erro.showAndWait();

            } else {
                Cadastrar();
            }
        });
        btDeletar.setOnMouseClicked((MouseEvent e) -> {
            Deletar();
        });
        //Action por meio do clique do Botao ENTER
        pfSenha.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                if (tfLogin == null || tfLogin.getText().trim().isEmpty() //Verifica se campos estao nulos
                        || pfSenha == null || pfSenha.getText().trim().isEmpty()) {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setHeaderText("CAMPOS VAZIOS");
                    erro.setContentText("Preencha todos os campos");
                    erro.showAndWait();

                } else {
                    Cadastrar();
                }
            }
        });
        btCadastrar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                if (tfLogin == null || tfLogin.getText().trim().isEmpty() //Verifica se campos estao nulos
                        || pfSenha == null || pfSenha.getText().trim().isEmpty()) {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setHeaderText("CAMPOS VAZIOS");
                    erro.setContentText("Preencha todos os campos");
                    erro.showAndWait();

                } else {
                    Cadastrar();
                }
            }
        });

        btDeletar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Deletar();
            }
        });
        btCancelar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Cancelar();
            }
        });
        btAlterar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                AlteraFoto();
            }
        });
    }
    //Cancela o Cadastro Retornando a tela de Login
    public void Cancelar() {
        Login log = new Login();
        try {
            Cadastro.getStage().close();
            log.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Deleta a imagem que foi selecionada retornando a imagem padrao
    public void Deletar() {
        imgFoto.setImage(new Image("/Imagens/FotoPerfilNull.png"));
    }
    //Cadastra usuario realizando uma verificacao caso o usuario ja esteja cadastrado
    public void Cadastrar() {
        UsuarioDao dao = new UsuarioDao();
        List<Usuario> user = FXCollections.observableArrayList(dao.getLista());
        for (int i = 0; i < user.size(); i++) {

            if (tfLogin.getText().equals(user.get(i).getLogin())) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Este usuario ja existe");
                alerta.showAndWait();
                i = user.size();
            } else if (i + 1 == user.size()) {

                Usuario usuario = new Usuario();
                usuario.setLogin(tfLogin.getText());
                usuario.setSenha(Criptografia.criptografar(pfSenha.getText()));
                usuario.setFoto(caminhoFoto);

                UsuarioDao dao2 = new UsuarioDao();
                dao2.InsereUsuario(usuario);
                Cancelar();

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setHeaderText("Cadastro Realizado com Sucesso");
                alerta.showAndWait();
            }
        }

    }
    //Altera a imagem do cadastro de usuario
    public void AlteraFoto() {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("Imagens", "*.jpg", "*.png", "*.jpeg"));
        File file = f.showOpenDialog(new Stage());
        if (file != null) {
            imgFoto.setImage(new Image("file:///" + file.getAbsolutePath()));
            caminhoFoto = file.getAbsolutePath();
        }
    }
}

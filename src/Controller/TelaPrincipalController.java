/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Atualizar;
import Main.CadastroProduto;
import Main.Descricao;
import Main.Pedidos;
import Main.TelaPrincipal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Vitor
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    private Button btPedidos;

    @FXML
    private Button btCadastro;

    @FXML
    private Button btProdutos;

    @FXML
    private Button btDescricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Action por meio do clique do Mouse
        btPedidos.setOnMouseClicked((MouseEvent e) -> {
            pedido();
        });
        btCadastro.setOnMouseClicked((MouseEvent e) -> {
            cadastro();
        });
        btProdutos.setOnMouseClicked((MouseEvent e) -> {
            produto();
        });
        btDescricao.setOnMouseClicked((MouseEvent e) -> {
            descricao();
        });

        //Action por meio do clique do Botao ENTER
        btPedidos.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                pedido();
            }
        });
        btCadastro.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                cadastro();
            }
        });
        btProdutos.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                produto();
            }
        });
        btDescricao.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                descricao();
            }
        });
    }
    //Abre a tela de Pedidos
    public static void pedido() {
        Pedidos pedidos = new Pedidos();
        try {
            TelaPrincipal.getStage().close();
            pedidos.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Abre a tela de Cadastro de Produtos
    public static void cadastro() {
        CadastroProduto cadastroproduto = new CadastroProduto();
        try {
            TelaPrincipal.getStage().close();
            cadastroproduto.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Abre a tela de Produtos
    public static void produto() {
        Atualizar atualizar = new Atualizar();
        try {
            TelaPrincipal.getStage().close();
            atualizar.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Abre a tela de Descricao
    public static void descricao() {
        Descricao descricao = new Descricao();
        try {
            TelaPrincipal.getStage().close();
            descricao.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PedidoDao;
import Main.ConfirmarExcluirPedido;
import Main.Pedidos;
import Model.Pedido;
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
public class ConfirmarExcluirPedidoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Button btConfirmar;
    @FXML
    private Button btCancelar;
    
    private static Pedido ped;
    
    public static void setPedido(Pedido p){
        ped = p;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btConfirmar.setOnKeyPressed((KeyEvent evt)->{
            if(evt.getCode()==KeyCode.ENTER){
                Confirmar();
            }
        });
        btConfirmar.setOnMouseClicked((MouseEvent e)->{
            Confirmar();
        });
        btCancelar.setOnKeyPressed((KeyEvent evt)->{
            if(evt.getCode()==KeyCode.ENTER){
                Cancelar();
            }
        });
        btCancelar.setOnMouseClicked((MouseEvent e)->{
            Cancelar();
        });
    }
    public void Confirmar(){
        PedidoDao dao = new PedidoDao();
        dao.DeletaPedido(ped);
        ConfirmarExcluirPedido.getStage().close();
        Pedidos.getStage().close();
        Pedidos pe=new Pedidos();
        try {
            pe.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(ConfirmarExcluirPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Cancelar(){
        ConfirmarExcluirPedido.getStage().close();
    }   
}

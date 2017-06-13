/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PedidoDao;
import Main.ConfirmarExcluirPedido;
import Main.Pedidos;
import Main.TelaPrincipal;
import Model.Pedido;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Vitor
 */
public class PedidosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableColumn<Pedido, String> tcCliente;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private ObservableList<Pedido> pedido;

    @FXML
    private Button btVoltar;

    @FXML
    private Button btExcluir;

    @FXML
    private TableColumn<Pedido, String> tcProduto;

    @FXML
    private TableColumn<Pedido, Integer> tcQuantidade;

    @FXML
    private TableColumn<Pedido, Long> tcId;

    @FXML
    private TableColumn<Pedido, Double> tcPreco;

    @FXML
    private TableView<Pedido> tvPedido;

    public Pedido excluir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ExibirPedido();
        tvPedido.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                excluir = (Pedido) newValue;

            }
        });
        btExcluir.setOnMouseClicked((MouseEvent e) -> {
            ExcluirPedido();
        });
        btExcluir.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                ExcluirPedido();
            }
        });
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Voltar();
        });
        btVoltar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {

            }
        });
        tfPesquisa.setOnKeyReleased((KeyEvent e) -> {
            tvPedido.setItems(Procurar());
        });
    }

    public void ExibirPedido() {
        tcCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        tcProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        tcQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        tcPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        tcId.setCellValueFactory(new PropertyValueFactory("id"));

        PedidoDao dao = new PedidoDao();
        pedido = FXCollections.observableArrayList(dao.getList());
        tvPedido.setItems(pedido);
    }

    private ObservableList<Pedido> Procurar() {
        ObservableList<Pedido> pedidoPesquisa = FXCollections.observableArrayList();
        for (int x = 0; x < pedido.size(); x++) {
            if (pedido.get(x).getCliente().toLowerCase().contains(tfPesquisa.getText().toLowerCase())
                    || pedido.get(x).getProduto().toLowerCase().contains(tfPesquisa.getText().toLowerCase())) {
                pedidoPesquisa.add(pedido.get(x));
            }
        }
        return pedidoPesquisa;
    }

    public void Voltar() {
        TelaPrincipal tela = new TelaPrincipal();
        Pedidos.getStage().close();
        try {
            tela.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ExcluirPedido() {
        if (excluir != null) {
            ConfirmarExcluirPedidoController.setPedido(excluir);
            ConfirmarExcluirPedido confirmar = new ConfirmarExcluirPedido();
            try {
                confirmar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(PedidosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Nenhum pedido selecionado para excluir");
            alerta.showAndWait();
        }
    }
    public Button getBtExcluir(){
        return btExcluir;
    }
    public void setBtExcluir(Button btExcluir){
        this.btExcluir= btExcluir;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PedidoDao;
import Main.Descricao;
import Main.GraficoVendido;
import Model.Pedido;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
public class GraficoVendidoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int agua;
    private int alcool;
    private int amaciante;
    private int cloro;
    private int desinfetante;
    private int detergente;
    private int hommo;

    @FXML
    private AreaChart<Number, Number> acVendido;

    @FXML
    private Button btVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CalcularQuantidade();
        GraficoLinha();

        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Voltar();
        });
        btVoltar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Voltar();
            }
        });
    }

    public void Voltar() {
        GraficoVendido.getStage().close();
        Descricao desc = new Descricao();
        try {
            desc.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GraficoVendidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void CalcularQuantidade() {
        PedidoDao dao = new PedidoDao();
        List<Pedido> pedido = new ArrayList<Pedido>(dao.getList());

        for (int x = 0; x < pedido.size(); x++) {

            if (pedido.get(x).getProduto().toLowerCase().equals("agua")) {
                agua = agua + 1;
            }
            if (pedido.get(x).getProduto().toLowerCase().equals("alcool")) {
                alcool = alcool + 1;
            }
            if (pedido.get(x).getProduto().toLowerCase().equals("amaciante")) {
                amaciante = amaciante + 1;
            }
            if (pedido.get(x).getProduto().toLowerCase().equals("cloro")) {
                cloro = cloro + 1;
            }
            if (pedido.get(x).getProduto().toLowerCase().equals("desinfetante")) {
                desinfetante = desinfetante + 1;
            }
            if (pedido.get(x).getProduto().toLowerCase().equals("detergente")) {
                detergente = detergente + 1;
            }
            if (pedido.get(x).getProduto().toLowerCase().equals("hommo")) {
                hommo = hommo + 1;
            }
        }
    }

    public void GraficoLinha() {

        XYChart.Series produtos = new XYChart.Series();
        produtos.setName("Produtos");

        XYChart.Data aguag = new XYChart.Data("Água", 5); 
        XYChart.Data alcoolg = new XYChart.Data("Alcool", 15);
        XYChart.Data amacianteg = new XYChart.Data("Amaciante", 10);
        XYChart.Data clorog = new XYChart.Data("Cloro",15);
        XYChart.Data desinfetanteg = new XYChart.Data("Desinfetante",20);
        XYChart.Data detergenteg = new XYChart.Data("Detergente",15);
        XYChart.Data hommog = new XYChart.Data("Hommo",15);
        
        produtos.getData().addAll(aguag, alcoolg, amacianteg,
                 clorog, desinfetanteg, detergenteg, hommog);

        acVendido.getData().add(produtos);
    }
}

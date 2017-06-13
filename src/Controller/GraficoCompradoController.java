/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProdutoDao;
import Main.Descricao;
import Main.GraficoComprado;
import Model.Produto;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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
public class GraficoCompradoController implements Initializable {
 
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
    private PieChart pcComprado;

    @FXML
    private Button btVoltar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CalcularQuantidade();
        GraficoPizza();
        btVoltar.setOnMouseClicked((MouseEvent evt) -> {
            Voltar();
        });
        btVoltar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Voltar();
            }
        });
        
    }

    public void Voltar() {
        Descricao desc = new Descricao();
        GraficoComprado.getStage().close();
        try {
            desc.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GraficoCompradoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void CalcularQuantidade(){
        ProdutoDao dao=new ProdutoDao();
        List<Produto> produto=new ArrayList<Produto>(dao.getList());
        for(int x=0; x< produto.size();x++){
            if(produto.get(x).getTipo().toLowerCase().equals("agua")){
                agua=agua+1;
            }
            if(produto.get(x).getTipo().toLowerCase().equals("alcool")){
                alcool=alcool+1;
            }
            if(produto.get(x).getTipo().toLowerCase().equals("amaciante")){
                amaciante=amaciante+1;
            }
            if(produto.get(x).getTipo().toLowerCase().equals("cloro")){
                cloro=cloro+1;
            }
            if(produto.get(x).getTipo().toLowerCase().equals("desinfetante")){
                desinfetante=desinfetante+1;
            }
            if(produto.get(x).getTipo().toLowerCase().equals("detergente")){
                detergente=detergente+1;
            }
            if(produto.get(x).getTipo().toLowerCase().equals("hommo")){
                hommo=hommo+1;
            }
        }
    }
    public void GraficoPizza(){
            pcComprado.getData().addAll(new PieChart.Data("Agua Sanitaria", agua),
            new PieChart.Data("Alcool", alcool),
            new PieChart.Data("Amaciante", amaciante),
            new PieChart.Data("Cloro", cloro),
            new PieChart.Data("Desinfetante", desinfetante),
            new PieChart.Data("Detergente",detergente),
            new PieChart.Data("Hommo",hommo));
            
    } 
}
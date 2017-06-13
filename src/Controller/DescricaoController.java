/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.ProdutoDao;
import DAO.UsuarioDao;
import Main.Descricao;
import Main.GraficoComprado;
import Main.GraficoVendido;
import Main.TelaPrincipal;
import Model.Produto;
import Model.Usuario;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class DescricaoController implements Initializable {

    @FXML
    private Button btVoltar;

    @FXML
    private Button btVendido;

    @FXML
    private Button btPdfProduto;

    @FXML
    private Button btPdfUsuario;

    @FXML
    private Button btComprado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Action por meio do clique do Mouse
        btVoltar.setOnMouseClicked((MouseEvent e) -> {
            Cancelar();
        });
        btVendido.setOnMouseClicked((MouseEvent e) -> {
            GraficoVend();
        });
        btPdfProduto.setOnMouseClicked((MouseEvent e) -> {
            pdfProduto();
        });
        btPdfUsuario.setOnMouseClicked((MouseEvent e) -> {
            pdfUsuario();
        });
        btComprado.setOnMouseClicked((MouseEvent e) -> {
            GraficoComp();
        });
        //Action por meio do clique do botao ENTER
        btVoltar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Cancelar();
            }
        });
        btVendido.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                GraficoVend();
            }
        });
        btPdfProduto.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                pdfProduto();
            }
        });
        btPdfUsuario.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                pdfUsuario();
            }
        });
        btComprado.setOnKeyPressed((KeyEvent evt)->{
            if(evt.getCode() == KeyCode.ENTER){
                GraficoComp();
            }
        });
    }

    //Retorna a tela Principal do Sistema
    public void Cancelar() {
        TelaPrincipal tela = new TelaPrincipal();

        try {
            Descricao.getStage().close();
            tela.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Abre a tela do Grafico de Produtos Vendidos
    public void GraficoVend() {
        GraficoVendido vendido=new GraficoVendido();
        Descricao.getStage().close();
        try {
            vendido.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    //Abre a tela do Grafico de Produtos Comprados
    public void GraficoComp() {
        GraficoComprado comprado=new GraficoComprado();
        Descricao.getStage().close();
        try {
            comprado.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Gera um pdf dos produtos cadastrados
    public void pdfProduto() {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("PDF", ".pdf"));
        File file = f.showSaveDialog(new Stage());
        if (file != null) {
            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsoluteFile()));
                doc.open();

                List<Produto> produto = new ProdutoDao().getList();
                for (int x = 0; x < produto.size(); x++) {
                    doc.add(new Paragraph("Tipo do Produto: "+produto.get(x).getTipo()));
                    doc.add(new Paragraph("Foto do Produto: "+produto.get(x).getFoto()));
                    doc.add(new Paragraph("Quantidade em Litros do Produto: "+produto.get(x).getLitros()));
                    doc.add(new Paragraph("ID do Produto: "+produto.get(x).getId()));
                    doc.add(new Paragraph(""));
                    doc.add(new Paragraph("==/==/==/==/==/==/==/==/==/==/=="));
                    doc.add(new Paragraph(""));
                
                }
                doc.close();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setHeaderText("PDF gerado com sucesso");
                alerta.showAndWait();
            } catch (DocumentException ex) {
                Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione um local valido");
            alerta.showAndWait();
        }
    }
    
    public void pdfUsuario() {
        FileChooser f = new FileChooser();
        f.getExtensionFilters().add(new ExtensionFilter("PDF", ".pdf"));
        File file = f.showSaveDialog(new Stage());
        if (file != null) {
            Document doc = new Document();
            try {

                PdfWriter.getInstance(doc, new FileOutputStream(file.getAbsoluteFile()));
                doc.open();
                
                List<Usuario>  usu=new UsuarioDao().getLista();
                for(int x=0; x<usu.size(); x++){
                    doc.add(new Paragraph("Login de Usuario: "+usu.get(x).getLogin()));
                    doc.add(new Paragraph("Foto do Usuario: "+usu.get(x).getFoto()));
                    doc.add(new Paragraph(""));
                    doc.add(new Paragraph("==/==/==/==/==/==/==/==/==/==/=="));
                    doc.add(new Paragraph("")); 
                }
                doc.close();
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setHeaderText("PDF gerado com sucesso");
                alerta.showAndWait();
                  
            } catch (DocumentException ex) {
                Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DescricaoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setHeaderText("Selecione um local valido");
            alerta.showAndWait();
        }
    }
}

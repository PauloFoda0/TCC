package Controller;

import DAO.ProdutoDao;
import Main.Atualizar;
import Main.ConfirmarExcluirProduto;
import Main.TelaPrincipal;
import Model.Produto;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Paulo Vitor
 */
public class AtualizarController implements Initializable {

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btExcluir;
    
    @FXML
    private Button btVoltar;
    
    @FXML
    private TableView<Produto> tvProduto;

    @FXML
    private ObservableList<Produto> produto;

    @FXML
    private TableColumn<Produto, String> tcLitros;

    @FXML
    private ImageView imagem;

    @FXML
    private TableColumn<Produto, Long> tcId;

    @FXML
    private TableColumn<Produto, String> tcTipo;

    @FXML
    private TableColumn<Produto, Double> tcPreco;

    private Produto selecionado;

    public Produto excluir;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ExibirProduto();
     
        tvProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selecionado = (Produto) newValue;
                if(selecionado != null){
                detalhes();
                }
            }    
        });
        tvProduto.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                excluir=(Produto) newValue;
                
            }
        });
        tfPesquisa.setOnKeyReleased((KeyEvent e) -> {
            tvProduto.setItems(Procurar());
        });
        btExcluir.setOnMouseClicked((MouseEvent e)->{
            excluirProduto();
        });
        btExcluir.setOnKeyPressed((KeyEvent evt)->{
            if(evt.getCode()==KeyCode.ENTER){
                excluirProduto();
            }
        });
        btVoltar.setOnKeyPressed((KeyEvent evt)->{
            if(evt.getCode()==KeyCode.ENTER){
                voltarPrincipal();
            }
        });
        btVoltar.setOnMouseClicked((MouseEvent)->{
            voltarPrincipal();
        });
    }
    public void voltarPrincipal(){
        TelaPrincipal tela=new TelaPrincipal();
        Atualizar.getStage().close();
        try {
            tela.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(AtualizarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ExibirProduto() {
        tcLitros.setCellValueFactory(new PropertyValueFactory("litros"));
        tcPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        tcTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        tcId.setCellValueFactory(new PropertyValueFactory("id"));

        ProdutoDao dao = new ProdutoDao();
        produto = FXCollections.observableArrayList(dao.getList());
        tvProduto.setItems(produto);
    }
    public void excluirProduto() {
       if(excluir!=null){
            ConfirmarExcluirProdutoController.setProduto(excluir);
            ConfirmarExcluirProduto confirmar= new ConfirmarExcluirProduto();
            try {
                confirmar.start(new Stage());
            } catch (Exception ex) {
                Logger.getLogger(AtualizarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Nenhum produto selecionado para excluir");
            alerta.showAndWait();
        }
    }

    public void detalhes() {
        if (selecionado != null) {
            imagem.setImage(new Image("file:///" + selecionado.getFoto()));
        } else {
            imagem.setImage(new Image("/Imagens/Icone produto.png"));
        }
    }

    private ObservableList<Produto> Procurar() {
        ObservableList<Produto> produtoPesquisa = FXCollections.observableArrayList();
        for (int x = 0; x < produto.size(); x++) {
            if (produto.get(x).getTipo().toLowerCase().contains(tfPesquisa.getText().toLowerCase())) {
                produtoPesquisa.add(produto.get(x));
            }
        }
        return produtoPesquisa;
    }
    
    public Button getBtExcluir() {
        return btExcluir;
    }
    public void setBtExcluir(Button btExcluir) {
        this.btExcluir = btExcluir;
    }
}

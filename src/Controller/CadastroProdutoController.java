package Controller;

import DAO.ProdutoDao;
import Main.CadastroProduto;
import Main.TelaPrincipal;
import Model.Produto;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class CadastroProdutoController implements Initializable {

    @FXML
    private TextField tfPreco;

    @FXML
    private TextField tfTipo;

    @FXML
    private Button btAlterar;

    @FXML
    private Button btCadastrar;

    @FXML
    private ImageView imgFoto;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfVolume;

    private String caminhoFoto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tfPreco.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                if (tfPreco == null || tfPreco.getText().trim().isEmpty()
                        || tfTipo == null || tfTipo.getText().trim().isEmpty()
                        || tfVolume == null || tfVolume.getText().trim().isEmpty()) {
                    Alert erro = new Alert(Alert.AlertType.ERROR);
                    erro.setHeaderText("CAMPOS VAZIOS");
                    erro.setContentText("Preencha todos os campos");
                    erro.showAndWait();
                } else {
                    cadastrar();
                }
            }
        });
        btCancelar.setOnMouseClicked((MouseEvent e) -> {
            Cancelar();
        });
        btCancelar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                Cancelar();
            }
        });
        btAlterar.setOnMouseClicked((MouseEvent e) -> {
            AlteraFoto();

        });
        btAlterar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                AlteraFoto();
            }
        });
        btCadastrar.setOnMouseClicked((MouseEvent e) -> {
            if (tfPreco == null || tfPreco.getText().trim().isEmpty()
                    || tfTipo == null || tfTipo.getText().trim().isEmpty()
                    || tfVolume == null || tfVolume.getText().trim().isEmpty()) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setHeaderText("CAMPOS VAZIOS");
                erro.setContentText("Preencha todos os campos");
                erro.showAndWait();
            } else {
                cadastrar();
            }
        });
        btCadastrar.setOnKeyPressed((KeyEvent evt) -> {
            if (evt.getCode() == KeyCode.ENTER) {
                cadastrar();
            }
        });
    }

    public void Cancelar() {
        TelaPrincipal tela = new TelaPrincipal();
        CadastroProduto.getStage().close();
        try {
            tela.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void blockPreco() {
        String t = tfPreco.getText().replaceAll("[a-zA-Z\\á-ýÁ-Ý\\s\\-\\,\\*\\/\\=\\("
                + "\\)\\&\\¨\\%\\$\\#\\@\\!\\¹\\²\\³\\£\\¢\\¬\\'\\§\\_\\ª\\[\\]"
                + "\\º\\;\\:\\?\\~\\^\\ã-õÃ-Õ\\+\\|\\\\´\\`]", "");

        tfPreco.setText(t);
        tfPreco.end();
    }

    public void cadastrar() {

        Produto prod = new Produto();
        prod.setTipo(tfTipo.getText());
        prod.setLitros(tfVolume.getText());

        prod.setPreco(Double.valueOf(tfPreco.getText()));

        prod.setFoto(caminhoFoto);

        ProdutoDao dao = new ProdutoDao();
        dao.InsereProduto(prod);

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText("Cadastro Realizado com Sucesso");
        alerta.showAndWait();

        try {
            TelaPrincipal tela = new TelaPrincipal();
            CadastroProduto.getStage().close();
            tela.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(CadastroProdutoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

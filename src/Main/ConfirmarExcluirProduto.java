package Main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Paulo Vitor
 */
public class ConfirmarExcluirProduto extends Application {
     private static Stage stage; //Criar um Stage privado para pegar e selecionar

    public static Stage getStage() {  //Stage publico para pegar
        return stage;
    }

    public static void setStage(Stage stage) { //Stage publico para selecionar
        ConfirmarExcluirProduto.stage = stage;
    }

    
     
     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ConfirmarExcluirProduto.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Confirmar produto");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/Icone produto.png")));
        stage.show();
        ConfirmarExcluirProduto.setStage(stage);
    }

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

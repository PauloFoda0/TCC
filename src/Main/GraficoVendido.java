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
public class GraficoVendido extends Application {
     private static Stage stage; //Criar um Stage privado para pegar e selecionar

    public static Stage getStage() { //Stage publico para pegar
        return stage;
    }

    public static void setStage(Stage stage) { //Stage publico para selecionar
        GraficoVendido.stage = stage;
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/GraficoVendido.fxml")); //Puxa a tela que se localiza no View
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene); //Seleciona tela ao Stage
        stage.setTitle("Grafico de produtos Vendidos"); //Titulo da tela
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/Icone produto.png")));
        stage.show(); //Abre a tela
        GraficoVendido.setStage(stage); //Adiciona o Stage a GraficoVendido
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

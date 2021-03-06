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
public class Login extends Application {
     private static Stage stage; //Criar um Stage privado para pegar e selecionar

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) { //Stage publico para selecion ar
        Login.stage = stage;
    }
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Login.fxml")); //Puxa a tela que se localiza no View
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene); //Seleciona tela ao Stage
        stage.setTitle("Login"); //Titulo da tela
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/Icone produto.png")));
        stage.show(); //Abre a tela
        Login.setStage(stage); //Adiciona o Stage ao Login
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

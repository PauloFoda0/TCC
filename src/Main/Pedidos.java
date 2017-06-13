/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Pedidos extends Application {
     private static Stage stage; //Criar um Stage privado para pegar e selecionar
 
    public static Stage getStage() {  //Stage publico para pegar
        return stage;
    }

    public static void setStage(Stage stage) { //Stage publico para selecionar
        Pedidos.stage = stage;
    }

    
     
     
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Pedidos.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Pedidos");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Imagens/Icone produto.png")));
        stage.show();
        Pedidos.setStage(stage);
    }

    /** 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sgerypg;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author 
*/ 
public class sgerypg extends Application{
    

    /**
     * @param args the command line arguments
    */
    
    private static Scene scenePr;

    @Override
    public void start(Stage stage) throws IOException {
        scenePr = new Scene(loadFXML("/mx/uv/fei/GUI/Login"));
        stage.setScene(scenePr);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scenePr.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(sgerypg.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void changeView(String url) throws IOException {
        setRoot(url);
    }

    public static void main(String[] args){     
        launch();
    }
}


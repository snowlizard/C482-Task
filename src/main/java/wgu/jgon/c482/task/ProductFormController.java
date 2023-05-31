/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wgu.jgon.c482.task;

import javafx.scene.control.Label;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mrjack
 */


    

public class ProductFormController {
    private Stage stage = new Stage();
    private Scene scene;
    
    public void init(String label) throws IOException{
        scene = new Scene(loadFXML("productForm"));
        stage.initModality(Modality.APPLICATION_MODAL);
        Label mainLabel = (Label) scene.lookup("#mainLabel");
        mainLabel.setText(label);
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    private void onCancel(ActionEvent e){
        Node source = (Node) e.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }
    
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
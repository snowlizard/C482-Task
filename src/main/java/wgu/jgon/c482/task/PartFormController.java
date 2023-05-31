/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wgu.jgon.c482.task;

import javafx.scene.control.RadioButton;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.control.*;
import javafx.beans.value.*;

/**
 * FXML Controller class
 *
 * @author mrjack
 */
public class PartFormController{
    private Stage stage = new Stage();
    private Scene scene;
    
    @FXML private RadioButton inhouse;
    @FXML private RadioButton outsourced;
    @FXML private Label machineCompany;
    
    public void init(String text) throws IOException{
        scene = new Scene(loadFXML("partForm"));
        stage.initModality(Modality.APPLICATION_MODAL);
        Label mainLabel = (Label) scene.lookup("#mainLabel");
        mainLabel.setText(text);
        
        machineCompany = (Label) scene.lookup("#machineCompany");
        
        // create toggle group
        ToggleGroup group = new ToggleGroup();
        inhouse = (RadioButton) scene.lookup("#inhouse");
        outsourced  = (RadioButton) scene.lookup("#outsourced");
        inhouse.setToggleGroup(group);
        outsourced.setToggleGroup(group);
        
        // add event listener to radio button group
        // switch machine or company label
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n){
                RadioButton btn = (RadioButton) group.getSelectedToggle();
                if(btn.getId().equals("inhouse")){
                    machineCompany.setText("Machine ID");
                } else {
                    System.out.print(btn.getId());
                    machineCompany.setText("Company name");
                }
            }
        });
        
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

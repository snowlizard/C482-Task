/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wgu.jgon.c482.task;

import javafx.scene.control.TextField;
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
 * @author Julian Gonzalez
 */
public class PartFormController{
    private Stage stage = new Stage();
    private Scene scene;
    
    @FXML private ToggleGroup group = new ToggleGroup();
    @FXML private RadioButton inhouse;
    @FXML private RadioButton outsourced;
    @FXML private Label machineCompany;
    
    @FXML private TextField partName;
    @FXML private TextField partInv;
    @FXML private TextField partPrice;
    @FXML private TextField partMin;
    @FXML private TextField partMax;
    @FXML private TextField companyMachineField;
    
    public void init(String text) throws IOException{
        scene = new Scene(loadFXML("partForm"));
        stage.initModality(Modality.APPLICATION_MODAL);
        Label mainLabel = (Label) scene.lookup("#mainLabel");
        mainLabel.setText(text);
        
        machineCompany = (Label) scene.lookup("#machineCompany");
        
        // create toggle group
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
    private void onSave(ActionEvent e){
        Node source = (Node) e.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        Part part;
        
        String name = partName.getText();
        double price = Double.parseDouble(partPrice.getText());
        int stock = Integer.parseInt(partInv.getText());
        int min = Integer.parseInt(partMin.getText());
        int max = Integer.parseInt(partMax.getText());
        
        if(inhouse.isSelected()){
            int machineId = Integer.parseInt(companyMachineField.getText());
            part = new InHouse(53, name, price, stock, min, max,
                        machineId);
        } else {
            String companyName = companyMachineField.getText();
            part = new Outsourced(53, name, price, stock, min, max,
                        companyName);
        }
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

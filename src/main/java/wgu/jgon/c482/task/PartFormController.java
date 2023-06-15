/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package wgu.jgon.c482.task;

import wgu.jgon.c482.task.Inventory;
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
import javafx.scene.control.Alert.AlertType;
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
    
    @FXML private Label mainLabel;
    @FXML private ToggleGroup group = new ToggleGroup();
    @FXML private RadioButton inhouse;
    @FXML private RadioButton outsourced;
    @FXML private Label machineCompany;
 
    @FXML private TextField partId;
    @FXML private TextField partName;
    @FXML private TextField partInv;
    @FXML private TextField partPrice;
    @FXML private TextField partMin;
    @FXML private TextField partMax;
    @FXML private TextField companyMachineField;
    
    public void init(String text, Part part) throws IOException{
        scene = new Scene(loadFXML("partForm"));
        stage.initModality(Modality.APPLICATION_MODAL);
        
        mainLabel = (Label) scene.lookup("#mainLabel");
        mainLabel.setText(text);
        
        // initialize part fields
        inhouse   = (RadioButton) scene.lookup("#inhouse");
        outsourced= (RadioButton) scene.lookup("outsourced");
        partId    = (TextField) scene.lookup("#partId");
        partName  = (TextField) scene.lookup("#partName");
        partInv   = (TextField) scene.lookup("#partInv");
        partPrice = (TextField) scene.lookup("#partPrice");
        partMin   = (TextField) scene.lookup("#partMin");
        partMax   = (TextField) scene.lookup("#partMax");
        companyMachineField = (TextField) scene.lookup("#companyMachineField");
        
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
                    machineCompany.setText("Company name");
                }
            }
        });
        
        // Set modify part fields
        if(part != null && mainLabel.getText() == "Modify Part"){
            this.setPartFields(part);
        } else if (part == null && mainLabel.getText() == "Modify Part"){
            return;
        }
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void onSave(ActionEvent e){
        Node source = (Node) e.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        Part part;
        try {
            String name = partName.getText();
            double price = Double.parseDouble(partPrice.getText());
            int stock = Integer.parseInt(partInv.getText());
            int min = Integer.parseInt(partMin.getText());
            int max = Integer.parseInt(partMax.getText());
            
            if(min >= max){
                Alert alert = new Alert(AlertType.WARNING, "Min value "
                        + "can not be more than Max value", ButtonType.OK);
                alert.showAndWait();
                return;
            } else if(stock < min || stock > max){
                Alert alert = new Alert(AlertType.WARNING, "Inv "
                        + "must be between min and max values.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            

            if(inhouse.isSelected()){
                int machineId = Integer.parseInt(companyMachineField.getText());
                part = new InHouse(0, name, price, stock, min, max,
                            machineId);
            } else {
                String companyName = companyMachineField.getText();
                part = new Outsourced(0, name, price, stock, min, max,
                            companyName);
            }

            if(mainLabel.getText() == "Add Part"){
                part.setId(Inventory.getAllParts().size());
                Inventory.addPart(part);
            } else {
                part.setId(Integer.parseInt(partId.getText()));
                Part oldPart = Inventory.lookupPart(part.getId());
                int index = Inventory.getAllParts().indexOf(oldPart);
                Inventory.updatePart(index, part);
            }
            win.close();    
        } catch (Exception error){
            Alert dialog = new Alert(AlertType.ERROR, 
                    "Please make sure each field contains the appropriate value\n"
                    + error,
                    ButtonType.OK);
            dialog.showAndWait();
        }       

    }
    
    @FXML
    private void onCancel(ActionEvent e){
        Node source = (Node) e.getSource();
        Stage win = (Stage) source.getScene().getWindow();
        
        win.close();
    }
    
    // Prepopulate modify part fields
    public void setPartFields(Part part){        
        if(part != null){
            partId.setText(Integer.toString(part.getId()));
            partName.setText(part.getName());
            partInv.setText(Integer.toString(part.getStock()));
            partPrice.setText(Double.toString(part.getPrice()));
            partMin.setText(Integer.toString(part.getMin()));
            partMax.setText(Integer.toString(part.getMax()));
            
            if(part.getClass().getName() == "wgu.jgon.c482.task.InHouse"){
                inhouse.setSelected(true);
                InHouse hpart = (InHouse) part;
                companyMachineField.setText(Integer.toString(hpart.getMachineId()));
            } else {
                Outsourced opart = (Outsourced) part;
                companyMachineField.setText(opart.getCompanyName());
            }
        }
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}

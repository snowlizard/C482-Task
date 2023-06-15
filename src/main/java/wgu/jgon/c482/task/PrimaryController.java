package wgu.jgon.c482.task;

import wgu.jgon.c482.task.Inventory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    @FXML private TextField searchParts;
    
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partId;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partLvl;
    @FXML private TableColumn<Part, Double> partPrice;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // initialize with parts
        Part p1 = new InHouse(0, "Gear", 1.99, 3, 1, 9, 940);
        Part p2 = new Outsourced(1, "Sprocket", 2.49, 5, 2, 13, "Nissin");
        Inventory.addPart(p1);
        Inventory.addPart(p2);
        
        partsTable.setItems(Inventory.getAllParts());
        partId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partLvl.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        // initailize with products

    }
    
    @FXML // Search parts
    private void onSearchPart() {
        String query = searchParts.getText();
        if(query.isEmpty()){
            partsTable.setItems(Inventory.getAllParts());
        } else{
            if(query.matches("\\d+")){
               ObservableList<Part> part = FXCollections.observableArrayList();
               part.add(Inventory.lookupPart(Integer.parseInt(query)));
               partsTable.setItems(part);
            } else {
                partsTable.setItems(Inventory.lookupPart(query));
            }
        }
    }
    
    @FXML // exit the program
    private void exit(ActionEvent e) throws IOException {
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML //open the add part window
    private void addPartWindow() throws IOException {
        PartFormController addParts = new PartFormController();
        addParts.init("Add Part", null);
    }
    
    @FXML
    private void onDeletePart() {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        if(part != null){
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure "
                    + "you want to delete this part?");
            alert.showAndWait();
            if(alert.getResult() == ButtonType.OK){
                Inventory.deletePart(part);
            }
        }
    }

    @FXML //open the modify part window
    private void modifyPartWindow() throws IOException {
        PartFormController partForm = new PartFormController();
        partForm.init("Modify Part", partsTable.getSelectionModel().getSelectedItem());
    }    
    
    @FXML // open the add product window
    private void addProductWindow() throws IOException {        
        ProductFormController productForm = new ProductFormController();
        productForm.init("Add Product");
    }
    
    @FXML // open the modify product window
    private void modifyProductWindow() throws IOException {
        ProductFormController productForm = new ProductFormController();
        productForm.init("Modify Product");
    }
}

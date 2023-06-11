package wgu.jgon.c482.task;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {
    private Stage stage = new Stage();
    private  Scene scene;
    private Inventory inventroy = new Inventory();
    
    @FXML private TableView<Part> partsTable;
    @FXML private TableColumn<Part, Integer> partId;
    @FXML private TableColumn<Part, String> partName;
    @FXML private TableColumn<Part, Integer> partLvl;
    @FXML private TableColumn<Part, Double> partPrice;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){        
        // initialize with parts
        ObservableList<Part> testParts = FXCollections.observableArrayList();
        Part p1 = new InHouse(1, "Gear", 1.99, 3, 1, 9, 940);
        Part p2 = new Outsourced(2, "Sprocket", 2.49, 5, 2, 13, "Nissin");
        testParts.add(p1);
        testParts.add(p2);
        
        partsTable.setItems(testParts);
        partId.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partLvl.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPrice.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        // initailize with products

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
        addParts.init("Add Part");
    }

    @FXML //open the modify part window
    private void modifyPartWindow() throws IOException {
        PartFormController partForm = new PartFormController();
        partForm.init("Modify Part");
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

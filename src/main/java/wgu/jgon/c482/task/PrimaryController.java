package wgu.jgon.c482.task;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class PrimaryController {
    private Stage stage = new Stage();
    private  Scene scene;
    
    
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

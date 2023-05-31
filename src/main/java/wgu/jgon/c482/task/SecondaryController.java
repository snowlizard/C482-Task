package wgu.jgon.c482.task;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.stage.Popup;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
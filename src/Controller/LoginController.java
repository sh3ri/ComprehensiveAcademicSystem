package Controller;

import IO.IO;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import mainpackage.Main;

public class LoginController {


    @FXML
    private HBox titlebar;
    @FXML
    private JFXButton minimizeBtn, maximizeBtn, closeBtn;

    public void initialize() {
//        stage = (Stage)minimizeBtn.getScene().getWindow();
        //unfocus pathField
//        Platform.runLater( () -> Main.primaryStage.requestFocus() );
        minimizeBtn.setDisableVisualFocus(true);
        new WindowMover(titlebar).setDragListener();
    }

    @FXML
    public void minimizeStage(ActionEvent e) {
        Main.primaryStage.setIconified(true);
    }

    public void maximizeStage(ActionEvent e) {
        Main.primaryStage.setMaximized(!Main.primaryStage.isMaximized());
    }

    public void closeStage(ActionEvent e) {
        IO.saveAll();
        Main.primaryStage.close();
    }
}

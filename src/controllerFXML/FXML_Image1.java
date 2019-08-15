/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Laptop
 */
public class FXML_Image1 implements Initializable{
    @FXML
    private Pane paneImage001;
    @FXML
    private Button ExitOne;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
            ExitOne.setOnMouseClicked((MouseEvent event) -> {
              
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            });
    }
    
}

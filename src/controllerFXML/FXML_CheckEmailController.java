/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author nguyenquang
 */
public class FXML_CheckEmailController implements Initializable {

    @FXML
    private JFXButton btnCheck;
    @FXML
    private JFXTextField code;
    public static String Mcode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCheck.setOnMouseClicked(((event) -> {
            Mcode = code.getText();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }));
        code.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                Mcode = code.getText();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

}

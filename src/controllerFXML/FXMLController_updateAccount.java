/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @autMhor nguyenquang
 */
public class FXMLController_updateAccount implements Initializable {

    @FXML
    private Label updateUser;
    @FXML
    private JFXTextField updateEmail;
    @FXML
    private JFXPasswordField updatePassword;
    @FXML
    private JFXButton btnUPDATE;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateUser.setText(controllerFXML.FXMLHomeController.userUpdate);
        updatePassword.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (checkdetailUpdate() == true) {
                    updateAccount();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }
            }
        });

        btnUPDATE.setOnMouseClicked((event) -> {
            if (checkdetailUpdate() == true) {
                updateAccount();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }

    private void updateAccount() {
        try {
            controllerSQL.AccountController.updateAccountSQL(updateUser.getText(), updateEmail.getText(), updatePassword.getText());
            Alert alt = new Alert(Alert.AlertType.INFORMATION);
            alt.setTitle("Successfully!");
            alt.setContentText("Update complete...");
            alt.showAndWait();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLController_updateAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkdetailUpdate() {
        Alert alt = new Alert(Alert.AlertType.ERROR);

        alt.setTitle("Error");
        if ((updateEmail.getText().isEmpty() == true) && (updatePassword.getText().isEmpty() == true)) {
            alt.setContentText("Please do not leave it empty");
            alt.showAndWait();
            return false;
        }
        if ((utils.PatternValided.PatternEmail(updateEmail.getText()) == false) && (!updateEmail.getText().isEmpty())) {
            alt.setContentText("Email Incorrect!!!");
            alt.showAndWait();
            return false;
        }
        return true;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import ControllerDB.connectDB;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
 *
 * @author nguyenquang
 */
public class FXML_ForgotPassController implements Initializable {

    @FXML
    private JFXTextField userForgot, emailForgot;
    @FXML
    private JFXPasswordField newPass;
    @FXML
    private JFXButton btnSubmit;
    private String User, Pass, email;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Alert alt = new Alert(null);
        btnSubmit.setOnMouseClicked((MouseEvent event) -> {
            try {
                if (checkAccount() == true) {
                    if (checkmail() == true) {
                        controllerSQL.AccountController.updateforgotAccount(User, email, Pass);
                        alt.setTitle("Notification");
                        alt.setContentText("Set Password successfully ! ");
                        alt.setAlertType(Alert.AlertType.INFORMATION);
                        alt.showAndWait();
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Your Code is not true");
                        alert.show();
                    }
                } else {
                    alt.setTitle("Notification");
                    alt.setContentText("No account information found");
                    alt.setAlertType(Alert.AlertType.INFORMATION);
                    alt.showAndWait();
                }
            } catch (SQLException ex) {
                Logger.getLogger(FXML_ForgotPassController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FXML_ForgotPassController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        newPass.addEventFilter(KeyEvent.KEY_PRESSED, (event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (checkAccount() == true) {
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(FXML_ForgotPassController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FXML_ForgotPassController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    private boolean checkAccount() throws SQLException, Exception {
        Connection conn = null;
        try {
            conn = ControllerDB.connectDB.connectSQL();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXML_ForgotPassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = " Select * from Account "
                + " where ( userName = ? ) and ( email = ?) ";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, userForgot.getText());
        pre.setString(2, emailForgot.getText());
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            return true;
        }
        return false;

    }

    private boolean checkmail() throws Exception {
        Random rd = new Random();
        int code = 0;
        while ((code <= 100000) | (code > 999999)) {
            code = rd.nextInt();
        }
        String mcode = String.valueOf(code);

        EMAIL.sendEmail.send_Email("smtp.gmail.com", emailForgot.getText(), "coffeemanagerTQD@gmail.com", "0967673814qwertyuiop", "CoffeeManagerTQD:", mcode);

        while (!mcode.equals(FXML_CheckEmailController.Mcode)) {
            Parent checkemail = FXMLLoader.load(getClass().getResource("/FXML/FXML_CheckEmail.fxml"));
            Scene scene = new Scene(checkemail);
            Stage check = new Stage();
            check.initModality(Modality.APPLICATION_MODAL);
            check.setResizable(false);
            check.setScene(scene);
            check.showAndWait();

        }
        User = userForgot.getText();
        email = emailForgot.getText();
        Pass = newPass.getText();
        return true;

    }
}

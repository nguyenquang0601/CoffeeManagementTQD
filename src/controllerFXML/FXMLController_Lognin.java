/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import ControllerDB.connectDB;
import View.StartLOGIN_LOGUP;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controllerFXML.FXMLController_Signup.StageSignin;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.bind.JAXBException;

/**
 *
 * @author nguyenquang
 */
public class FXMLController_Lognin implements Initializable {

    @FXML
    private JFXTextField user;
    @FXML
    private Button EXIT;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton SIGNIN;
    static Stage Homestage;
    @FXML
    private Label forgotPass;
    public static String idEMPLOY, idAccount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EXIT.setOnMouseClicked((MouseEvent evt) -> {
            System.exit(0);

        });

        SIGNIN.setOnMouseClicked((MouseEvent evt) -> {
            try {
                if (checkAccount() == true) {
                    showHomePage();
                } else {
                    signinWrong();
                }
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        SIGNIN.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            System.out.println(event.getCode().ENTER);
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (checkAccount() == true) {
                        showHomePage();
                    } else {
                        signinWrong();
                    }
                } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                    Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        password.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            System.out.println(event.getCode().ENTER);
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    if (checkAccount() == true) {
                        showHomePage();
                    } else {
                        signinWrong();
                    }
                } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                    Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        forgotPass.setOnMouseClicked((event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ForgotPass.fxml"));
                Scene scene = new Scene(root);
                Stage forgotStage = new Stage();
                forgotStage.initModality(Modality.APPLICATION_MODAL);
                forgotStage.setResizable(false);
                forgotStage.setScene(scene);
                forgotStage.show();
            } catch (IOException ex) {
                Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void showHomePage() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Home_PAGE1.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLController_Lognin.class.getName()).log(Level.SEVERE, null, ex);
        }

        StartLOGIN_LOGUP.MainSignup.close();
        if (StageSignin != null) {
            StageSignin.close();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/Css_Home.css").toExternalForm());

        Homestage = new Stage();
        Homestage.getIcons().add(new Image(getClass().getResourceAsStream("/images/iconBT.jpg")));
        Homestage.setResizable(false);
        //     Homestage.initStyle(StageStyle.UNDECORATED);
        Homestage.setScene(scene);
        Homestage.show();
    }

    private void signinWrong() {
        Alert alt = new Alert(Alert.AlertType.WARNING);
        alt.setTitle("Warning");
        alt.setContentText("User or Password incorrect");
        alt.showAndWait();
    }

    private boolean checkAccount() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException, NoSuchAlgorithmException {
        Connection conn = connectDB.connectSQL();
        String sql = " Select idAccount,userName,passWord,idEEmploy from Account"
                + " where ( userName = ? ) and ( passWord = ?) ";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1, user.getText());
        pre.setString(2, Encrypt.Encrypt_MD5.encrypt(password.getText()));
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            idEMPLOY = rs.getString("idEEmploy");
            idAccount = rs.getString("idAccount");
            System.out.println(idEMPLOY);
            String sql_1 = " select active from Employ "
                    + " where idEmploy = ? ";
            pre = conn.prepareStatement(sql_1);
            pre.setString(1, idEMPLOY);
            ResultSet rs_1 = pre.executeQuery();
            while (rs_1.next()) {
                System.out.println(rs_1.getString("active"));
                if (rs_1.getBoolean("active") == true) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;

        }
        return false;

    }

}

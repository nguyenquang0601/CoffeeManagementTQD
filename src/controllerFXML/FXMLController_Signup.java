/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import ControllerDB.connectDB;
import Model.ConnectSQL;
import View.StartLOGIN_LOGUP;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author nguyenquang
 */
public class FXMLController_Signup implements Initializable {

    @FXML
    private Button EXIT;
    @FXML
    private JFXButton SIGNUP;

    @FXML
    private JFXTextField localhost, UserDB, account, name, phonenumber, email, address, idCard;
    @FXML
    private DatePicker age;
    @FXML
    private JFXPasswordField PassDB, password;
    @FXML
    private CheckBox male, female;
    public static String user, url, passDB;
    public static Stage StageSignin;
    String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    String PASSWORD_REGEX = " ((?=.d)(?=.[a-z])(?=.[A-Z])(?=.[!.#$@_+,?-]).{8,50})";
    String NAME = "/^[a-z0-9_-]{3,16}$/";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        female.setOnMouseClicked((MouseEvent evt) -> {
            if (male.isSelected() == true) {
                male.setSelected(false);
            }
        });
        male.setOnMouseClicked((MouseEvent evt) -> {
            if (female.isSelected() == true) {
                female.setSelected(false);
            }
        });

        EXIT.setOnMouseClicked((MouseEvent evt) -> {
            System.exit(0);
        });
        PassDB.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
//            System.out.println(event.getCode().ENTER);
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    Action_Evt();
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLController_Signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FXMLController_Signup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FXMLController_Signup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        SIGNUP.setOnMouseClicked((MouseEvent evt) -> {
            try {
                Action_Evt();
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(FXMLController_Signup.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FXMLController_Signup.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    void Action_Evt() throws SQLException, ClassNotFoundException, Exception {
        try {
            boolean Error = true;

            Alert alt = new Alert(Alert.AlertType.ERROR);
            alt.setTitle("Error");
            if ((UserDB.getText().equals("")) | (localhost.getText().equals("")) | (PassDB.getText().equals(""))
                    | (account.getText().equals("")) | (name.getText().equals("")) | (password.getText().equals(""))
                    | (phonenumber.getText().equals("")) | (email.getText().equals("")) | (idCard.getText().isEmpty() == true)
                    | (address.getText().equals("")) | ((male.isSelected() == false) && (female.isSelected() == false)) | (age.getValue() == null)) {
                Error = false;
                alt.setContentText("Please do not leave it empty");
                alt.showAndWait();

            }
            if ((utils.PatternValided.PatternID(account.getText()) == false) && (Error == true)) {
                Error = false;
                alt.setContentText("ACCOUNT : (A-Z) or (a-z) and (0-9)"
                        + " Length : 6 characters longer ");
                alt.showAndWait();
            }
            if ((utils.PatternValided.PatternName(name.getText()) == false) && (Error == true)) {
                Error = false;
                alt.setContentText("NAME : A-Z or a-z");
                alt.showAndWait();
            }
            if ((Pattern.matches("^0\\d{9}$", phonenumber.getText()) == false) && (Error == true)) {
                Error = false;
                alt.setContentText("PHONE NUMBER : 0 - 9 !!!");
                alt.showAndWait();
            }
            if ((utils.PatternValided.PatternCMND(idCard.getText()) == false) && (Error == true)) {
                Error = false;
                alt.setContentText("Invalid ID card");
                alt.showAndWait();
            }
            if ((email.getText().matches(EMAIL_REGEX) == false) && (Error == true)) {
                Error = false;
                alt.setContentText("Email Incorrect!!!");
                alt.showAndWait();
            }
            LocalDate lc = LocalDate.now();
            if (((lc.getYear() - age.getValue().getYear()) <= 18) && (Error == true) ) {
                Error = false;
                alt.setContentText("Requires over 18 years old !! ");
                alt.showAndWait();
            } else {
            }

            // Ghi file XML
            if (Error == true) {

                user = UserDB.getText();
                passDB = PassDB.getText();
                url = localhost.getText();
                Model.ConnectSQL DB = new ConnectSQL(url, user, passDB);
                JAXBContext contextObj = JAXBContext.newInstance(ConnectSQL.class);
                Marshaller marshallerObj = contextObj.createMarshaller();
                marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshallerObj.marshal(DB, System.out);
                File file = new File("");
                file = new File(file.getAbsoluteFile() + "/src/XML/DB.xml");
                marshallerObj.marshal(DB, new FileOutputStream(file));
                if ((connectDB.checkConnect() == false) && (Error == true)) {
                    Error = false;
                    alt.setContentText("Connect SQLServer Incorrect!!!");
                    alt.showAndWait();
                }
                if (Error == true) {
                    if (checkmail() == true) {
                        ControllerDB.connectDB.creatDatabase();
                        ControllerDB.connectDB.creatTable();
                        Alert altIn = new Alert(Alert.AlertType.INFORMATION);
                        altIn.setTitle("INFORMATION");
                        altIn.setContentText("Register is successfully!!!");
                        altIn.showAndWait();
                        addADMIN();
                        Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Signin.fxml"));
                        StartLOGIN_LOGUP.MainSignup.close();
                        Scene scene = new Scene(root);
                        StageSignin = new Stage();
                        StageSignin.initStyle(StageStyle.UNDECORATED);
                        StageSignin.setScene(scene);
                        StageSignin.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Your Code is not true");
                        alert.show();
                    }
                }
            }
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(FXMLController_Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addADMIN() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException, NoSuchAlgorithmException, Exception {
        Connection conn = connectDB.connectSQL();
        // Insert Data Employ to Database
        String SQL = " Insert into Employ "
                + " values (?,?,?,?,?,?,?,?,?) ";
        PreparedStatement preparedStatement = conn.prepareStatement(SQL);
        preparedStatement.setString(1, "EMP001");
        preparedStatement.setString(2, name.getText());
        java.util.Date date = java.util.Date.from(age.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDATE = new java.sql.Date(date.getTime());
        preparedStatement.setDate(3, sqlDATE);
        System.out.println(age.getValue());
        if (male.isSelected() == true) {
            preparedStatement.setBoolean(4, true);
        } else {
            preparedStatement.setBoolean(4, false);
        }
        preparedStatement.setString(5, idCard.getText());
        preparedStatement.setString(6, phonenumber.getText());
        preparedStatement.setString(7, address.getText());
        preparedStatement.setString(8, "MANAGER");
        preparedStatement.setBoolean(9, true);
        int rs = preparedStatement.executeUpdate();
        String SQL_1 = " Insert into Account"
                + " Values (?,?,?,?,?) ";
        preparedStatement = conn.prepareStatement(SQL_1);
        preparedStatement.setString(1, "MAN001");
        preparedStatement.setString(2, account.getText());
        String codePass = Encrypt.Encrypt_MD5.encrypt(password.getText());
        preparedStatement.setString(3, codePass);
        preparedStatement.setString(4, email.getText());
        preparedStatement.setString(5, "EMP001");
        rs = preparedStatement.executeUpdate();
        // SET ACCESS CHO ACCOUNT

    }

    private boolean checkmail() throws Exception {
        Random rd = new Random();
        int code = 0;
        while ((code <= 100000) | (code > 999999)) {
            code = rd.nextInt();
        }
        String mcode = String.valueOf(code);
        EMAIL.sendEmail.send_Email("smtp.gmail.com", email.getText(), "coffeemanagerTQD@gmail.com", "0967673814qwertyuiop", "CoffeeManagerTQD:", mcode);

        while (!mcode.equals(FXML_CheckEmailController.Mcode)) {
            Parent checkemail = FXMLLoader.load(getClass().getResource("/FXML/FXML_CheckEmail.fxml"));
            Scene scene = new Scene(checkemail);
            Stage check = new Stage();
            check.initModality(Modality.APPLICATION_MODAL);
            check.setResizable(false);
            check.setScene(scene);
            check.showAndWait();

        }
        return true;
    }

}

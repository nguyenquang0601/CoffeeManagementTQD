/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import Model.Employee;
import Model.Menu;
import View.StartLOGIN_LOGUP;
import static controllerFXML.FXMLController_Lognin.idEMPLOY;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class FXMLHome_PAGE1_Controller implements Initializable {
//    @FXML
//    private AnchorPane centerPage;

    @FXML
    private Label nameINFO, ageINFO, genderINFO, idcardINFO, addINFO, phoneINFO;

    @FXML
    private Button btnManager;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAboutUs;
    @FXML
    private Button btnLognOut;
//    @FXML
//    private AnchorPane paneAll;
//    @FXML
//    private StackPane StackPaneAll;
    @FXML
    private AnchorPane paneHome;
    @FXML
    private AnchorPane paneAboutUs;
    static int idPAGE;

    //HOME
    @FXML
    private Pane paneImage001;
    @FXML
    private Pane paneImage2;
    @FXML
    private Pane paneImage3;
    @FXML
    private Pane paneImage4;
    @FXML
    private Pane paneImage5;
    @FXML
    private Pane paneImage6;
    @FXML
    private Pane paneImage7;
    @FXML
    private Pane paneImage8;
    ObservableList<Menu> obList = FXCollections.observableArrayList();

    public FXMLHome_PAGE1_Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Load Information staff
            loadINFO();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLHome_PAGE1_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        //HOME
        paneImage001.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
//
//
////image2
        paneImage2.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_1.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
        //image3
        paneImage3.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_2.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
        //image4
        paneImage4.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_3.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
        //image5
        paneImage5.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_4.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
        //image6
        paneImage6.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_5.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
        //image7
        paneImage7.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_6.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        );
        //image8
        paneImage8.setOnMouseClicked((MouseEvent evt) -> {
            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_ClickImage1_7.fxml"));

            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        btnAboutUs.setOnMouseClicked((event) -> {
            paneHome.setVisible(false);
            paneAboutUs.setVisible(true);
        });
        btnHome.setOnMouseClicked((event) -> {
            paneAboutUs.setVisible(false);
            paneHome.setVisible(true);
        });
        btnSell.setOnMouseClicked((event) -> {
            idPAGE = 1;
            showpane();
        });
        btnManager.setOnMouseClicked((event) -> {
            idPAGE = 2;
            showpane();
        });
        if (FXMLHome_PAGE1_Controller.idPAGE == 3) {
            paneHome.setVisible(true);
            paneAboutUs.setVisible(false);
        } else if (FXMLHome_PAGE1_Controller.idPAGE == 4) {
            paneHome.setVisible(false);
            paneAboutUs.setVisible(true);
        }
        if (controllerFXML.FXMLController_Lognin.idAccount.equals("MAN001")) {
            System.out.println(controllerFXML.FXMLController_Lognin.idAccount);
            btnManager.setDisable(false);
        } else {
            btnManager.setDisable(true);
        }
        btnLognOut.setOnMouseClicked((event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Signin.fxml"));
                Scene scene = new Scene(root);
                StartLOGIN_LOGUP.MainSignup.setScene(scene);
                StartLOGIN_LOGUP.MainSignup.show();
                FXMLController_Lognin.Homestage.close();
            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    private void showpane() {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Home.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        // Stage primaryStage = new Stage();
        FXMLController_Lognin.Homestage.setResizable(false);
        FXMLController_Lognin.Homestage.setScene(scene);
        FXMLController_Lognin.Homestage.show();
    }

    private void loadINFO() throws ClassNotFoundException {

        try {
            Employee emp = new Employee();
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " Select * from Employ "
                    + " where idEmploy = '" + idEMPLOY + "' ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                nameINFO.setText(rs.getString("name"));
                ageINFO.setText(rs.getDate("age").toString());
                phoneINFO.setText(rs.getString("phoneNumber"));
                if (rs.getBoolean("gender") == true) {
                    genderINFO.setText("MALE");
                } else {
                    genderINFO.setText("FEMALE");
                }
                
                addINFO.setText(rs.getString("address"));
                idcardINFO.setText(rs.getString("idCard"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLHome_PAGE1_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLHome_PAGE1_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(FXMLHome_PAGE1_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

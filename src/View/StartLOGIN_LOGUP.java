/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**0
 *
 * @author nguyenquang
 */
public class StartLOGIN_LOGUP extends Application {

    public static Stage MainSignup;

    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException, IOException {
        final boolean CheckCN;
        CheckCN = ControllerDB.connectDB.checkConnect();
        try {
            Parent root = null;
            if ((CheckCN == true) && (ControllerDB.connectDB.CheckDB() == true))  {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Signin.fxml"));
            } else {
                root = FXMLLoader.load(getClass().getResource("/FXML/FXML_SignUp.fxml"));
            }
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
            MainSignup = primaryStage;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

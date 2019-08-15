/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import static org.omg.CORBA.ORB.init;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FXML_inBillController implements Initializable {
    
    @FXML 
    private AnchorPane paneInBill;
    @FXML
    private JFXButton btn_InBill;
    @FXML
    private Label labelIDBillPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
//        dao = new DataAccessObject();
        init();
        btn_InBill.setOnAction((event) -> {
            print(paneInBill);
        });
//        loadService();
        // TODO
        initPayment();
    }    
    
    private  void initPayment(){
        labelIDBillPayment.setText(FXMLHomeController.idBillPayment);
    }
    
    private void print(Node node) {
        // Create a printer job for the default printer
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // Print the node
            boolean printed = job.printPage(node);
            if (printed) {
                // End the printer job
                job.endJob();
            
        } else {
            System.out.println("2");
        }
        }
    }
    
}


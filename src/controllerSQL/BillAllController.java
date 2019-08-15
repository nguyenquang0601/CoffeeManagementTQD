/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.BillAll;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class BillAllController {
    public static ObservableList<BillAll> loadDataBillAllSale() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        Connection cn = null;
        ObservableList listBillAllSale = FXCollections.observableArrayList();
        cn = ControllerDB.connectDB.connectSQL();
        String sql = "select * from BillAll";
        Statement stt = cn.createStatement();
        ResultSet rs = stt.executeQuery(sql);
        while (rs.next()) {
            BillAll billAll = new BillAll(rs.getString("idBill"), rs.getString("idEmploy_Bill"), rs.getString("DateCheckin"), rs.getFloat("totalBill"));
            listBillAllSale.add(billAll);
        }

        return listBillAllSale;
    }
}

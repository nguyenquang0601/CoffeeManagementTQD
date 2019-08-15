/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Order;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class OrderController {

    public static ObservableList<Order> loadDateOrder() {
        return null;

    }

    public static String inputBillAllSQL(String timeOrder, float total, String idEMPLOY) {
        String idBill = null;
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();

            String sql = " Insert into BillAll "
                    + " Values (?,?,?,?) ";

            PreparedStatement pre = connection.prepareStatement(sql);
            idBill = getNewIDBill();
            pre.setString(1, idBill);
            pre.setString(2, idEMPLOY);

            pre.setString(3, timeOrder);
            pre.setFloat(4, total);
            pre.executeUpdate();

        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idBill;
    }

    public static String getNewIDBill() {
        String ID = "";
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String sql = "select idBill from BillAll order by idBill desc ";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                ID = rs.getString("idBill");
            }
            rs.close();
            statement.close();
            connection.close();
            if (!ID.isEmpty()) {
                int i = Integer.valueOf(ID.substring(3, ID.length())) + 1;
                if (i >= 0 && i <= 9) {
                    ID = ID.substring(0, 3) + "00" + i;
                } else {
                    if (i >= 10 && i <= 99) {
                        ID = ID.substring(0, 3) + "0" + i;
                    } else {
                        ID = ID.substring(0, 3) + i;
                    }
                }
            } else {
                ID = "IDB001";
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }

    public static void inputBillDetailsSQL(ObservableList<Order> listOrder, String idBill) {
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String sql = " update Menu"
                    + " set  countExcess =  countExcess - ? "
                    + " where nameFood = ? ";
            String sql_1 = " Insert into Bill (idBill,idMenu,countOrder,price) "
                    + " values(?,?,?,?)  ";
            for (int i = 0; i < listOrder.size(); i++) {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1, listOrder.get(i).getCountOrder());
                pre.setString(2, listOrder.get(i).getNameMenuFood());
                pre.executeUpdate();
                pre = connection.prepareStatement(sql_1);
                pre.setString(1, idBill);
                String id;
                id = checkID(listOrder.get(i).getNameMenuFood());
                pre.setString(2, id);
                pre.setInt(3, listOrder.get(i).getCountOrder());
                pre.setFloat(4, listOrder.get(i).getPriceOrder());
                pre.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String checkID(String nameMenuFood) {
        String id = null;
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String sql = "select idMenu from Menu "
                    + " where nameFood = '"+ nameMenuFood + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                id = rs.getString("idMenu");
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Ingredients;
import Model.MenuCategory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class MenuCategoryController {

    public static ObservableList<MenuCategory> loadDataMenuCa() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        Connection cn = null;
        ObservableList listMenuCa = FXCollections.observableArrayList();
        cn = ControllerDB.connectDB.connectSQL();
        String sql = "select * from MEnuCategory";
        Statement stt = cn.createStatement();
        ResultSet rs = stt.executeQuery(sql);
        while (rs.next()) {
            MenuCategory mc = new MenuCategory(rs.getString("idMenuCategory"), rs.getString("name"));
            listMenuCa.add(mc);
        }

        return listMenuCa;
    }
    public static String getNewIDMenuCate() {
        String ID = "";
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String sql = "select idMenuCategory from MEnuCategory order by idMenuCategory desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                ID = rs.getString("idMenuCategory");
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
                ID = "IMC001";
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }

    public static void addMenuCate(String name) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String ID = getNewIDMenuCate();
            String sql = "Insert into MEnuCategory "
                    + " values (?,?) ";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, ID);
            pre.setString(2, name);
            pre.executeUpdate();
            pre.close();
            connection.close();

        } catch (SQLException | IOException | JAXBException | ClassNotFoundException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void updateMenuCate(String idMeCa,String nameMC){
        try {
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " update MEnuCategory "
                    + " set name = ?"
                    + " where idMenuCategory = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, nameMC);
            pre.setString(2, idMeCa);
            pre.executeUpdate();
            pre.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

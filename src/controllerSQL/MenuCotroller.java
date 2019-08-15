/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Ingredients;
import Model.Menu;
import Model.MenuCategory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
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
public class MenuCotroller {

    public static ObservableList<Menu> loadMenu() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        Connection cn = null;
        ObservableList listMenuFood = FXCollections.observableArrayList();
        cn = ControllerDB.connectDB.connectSQL();
        String sql = "select * from Menu";
        Statement stt = cn.createStatement();
        ResultSet rs = stt.executeQuery(sql);
        while (rs.next()) {

            String name = checkname(rs.getString("idMenuCategory"));
            Menu menu = new Menu(rs.getString("idMenu"), name, rs.getString("nameFood"), rs.getFloat("price"), rs.getFloat("promotion"), rs.getInt("countExcess"));
            listMenuFood.add(menu);
        }

        return listMenuFood;
    }

    public static String getNewIDMenuFood() {
        String ID = "";
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String sql = "select idMenu from Menu order by idMenu desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                ID = rs.getString("idMenu");
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
                ID = "IMF001";
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }

    public static void addMenuFood(String nameFoodType, String nameFood, float price, float promotion) throws SQLException {
        Connection conn = null;
        String idMenucategory = null;
        String ID = getNewIDMenuFood();
        try {
            conn = ControllerDB.connectDB.connectSQL();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(MenuCotroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql_1 = " select idMenuCategory from MEnuCategory"
                + " where name = ? ";

        PreparedStatement pre = conn.prepareStatement(sql_1);
        pre.setString(1, nameFoodType);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            idMenucategory = rs.getString("idMenuCategory");
        }
        String sql = "insert into Menu values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ID);

        ps.setString(3, nameFood);
        ps.setString(2, idMenucategory);
        ps.setFloat(5, price);
        ps.setFloat(4, promotion);
        ps.setInt(6, 0);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void deleteMenuFood(String idMenu) throws SQLException {
        Connection con = null;
        try {
            con = ControllerDB.connectDB.connectSQL();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(MenuCotroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = " delete from Menu where idMenu = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, idMenu);
        ps.executeUpdate();
        ps.close();
        con.close();
    }

    public static void updateMenuFood(String idMenu, String nameFoodType, String nameFood, float price, float promotion) throws SQLException {
        Connection conn = null;
        try {
            conn = ControllerDB.connectDB.connectSQL();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(MenuCotroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        // UPDATE  id CATEGORY
        String sql_1 = " select idMenuCategory from MEnuCategory"
                + " where name = ? ";
        String idMenucategory = null;
        PreparedStatement pre = conn.prepareStatement(sql_1);
        pre.setString(1, nameFoodType);

        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            idMenucategory = rs.getString("idMenuCategory");
        }
        String sql = " update Menu "
                + "set idMenuCategory = ?, nameFood = ?, price = ?, promotion = ?"
                + " where idMenu = ? ";
        pre = conn.prepareStatement(sql);
        pre.setString(1, idMenucategory);
        pre.setString(2, nameFood);
        pre.setFloat(3, price);
        pre.setFloat(4, promotion);
        pre.setString(5, idMenu);
        pre.executeUpdate();
        pre.close();
        conn.close();
    }

    public static String checkname(String idMEnuCategory) {
        String name = null;
        try {
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " select name from MEnuCategory "
                    + " where idMenuCategory = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, idMEnuCategory);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(MenuCotroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

}

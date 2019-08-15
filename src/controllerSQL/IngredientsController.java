/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Ingredients;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class IngredientsController {

    public static ObservableList<Ingredients> loadDataIngre() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        Connection cn = null;
        ObservableList listIngre = FXCollections.observableArrayList();
        cn = ControllerDB.connectDB.connectSQL();
        String sql = "select * from RawMaterials";
        Statement stt = cn.createStatement();
        ResultSet rs = stt.executeQuery(sql);
        while (rs.next()) {
            Ingredients igd = new Ingredients(rs.getString("idIngredients"), rs.getString("name"), rs.getFloat("price"), rs.getDate("DateInput"),rs.getInt("countInput"));
            listIngre.add(igd);
        }

        return listIngre;
    }

    public static String getNewIDEmp() {
        String ID = "";
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String sql = "select idIngredients from RawMaterials order by idIngredients desc";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                ID = rs.getString("idIngredients");
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
                ID = "IGD001";
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ID;
    }

    public static void addIngredients(String name, String Mass, float price, Date DateInput) throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        try {
            Connection connection = ControllerDB.connectDB.connectSQL();
            String ID = getNewIDEmp();
            // get ID cua namaeFood
            String IDmenu = getIDMF(name);
            String sql = "Insert into RawMaterials "
                    + " values (?,?,?,?,?,?) ";
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, ID);
            pre.setString(2, IDmenu);
            pre.setString(3, name);
            pre.setString(4, Mass);
            pre.setFloat(5, price);
            pre.setDate(6, DateInput);
            pre.executeUpdate();
            pre.close();
            connection.close();

        } catch (SQLException | IOException | JAXBException | ClassNotFoundException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void UpdateIngre(String idIngredients, String nameIngre, String countInput, float priceIngre, Date dateInputIngre) {

        try {
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " update RawMaterials "
                    + " set name = ?, countInput = ?,price = ?,DateInput = ?"
                    + " where idIngredients = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(2, countInput);
            pre.setString(1, nameIngre);
            pre.setFloat(3, priceIngre);
            pre.setDate(4, dateInputIngre);
            pre.setString(5, idIngredients);
            pre.executeUpdate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Update count Menu food
    public static void updateCountMenu(String nameFood, int countExcess) {
        try {
            Connection con = ControllerDB.connectDB.connectSQL();
            String sql = " update Menu"
                    + " set countExcess = countExcess + ? "
                    + " where nameFood = ? ";
            PreparedStatement pre  = con.prepareStatement(sql);
            pre.setInt(1,countExcess);
            pre.setString(2, nameFood);
            pre.executeUpdate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(IngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static void deleteIngre(String nameIngre, int countInput) {
        try {
            System.out.println(nameIngre + "   " +  countInput);
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql =  " update Menu "
                    + " set countExcess = countExcess - ? "
                    + " where nameFood = ? ";
            PreparedStatement pre  = conn.prepareStatement(sql);
            pre.setInt(1, countInput);
            pre.setString(2, nameIngre);
            pre.executeUpdate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(IngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String getIDMF(String name) {
        String IDF = null;
        try {
            Connection cn = null;
            cn = ControllerDB.connectDB.connectSQL();
            String sql = "select idMenu from Menu"
                    + " where nameFood = '" + name + "'";
            Statement stt = cn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                IDF = rs.getString("idMenu");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(IngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(IngredientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return IDF;
    }
}

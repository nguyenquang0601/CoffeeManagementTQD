/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Employee;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author nguyenquang
 */
public class EMPcontroller {

    
    public static void addNewEMP(String name, java.sql.Date sqlDATE, boolean gender, String idCard,String phoneNumber, String address, String position,boolean  active) {
        try {
            
            String idEmploy = "";
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = "Select top(1) idEmploy  from Employ order by idEmploy desc ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                idEmploy = rs.getString("idEmploy");
            }
            if (!idEmploy.isEmpty()) {
                int i = Integer.valueOf(idEmploy.substring(3, idEmploy.length())) + 1;
                if (i >= 0 && i <= 9) {
                    idEmploy = idEmploy.substring(0, 3) + "00" + i;
                } else {
                    if (i >= 10 && i <= 99) {
                        idEmploy = idEmploy.substring(0, 3) + "0" + i;
                    } else {
                        idEmploy = idEmploy.substring(0, 3) + i;
                    }
                }
            }
            sql = "Insert into Employ "
                    + " values (?,?,?,?,?,?,?,?,?) ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, idEmploy);
            pre.setString(2, name);
            pre.setDate(3, sqlDATE);
            pre.setBoolean(4, gender);
            pre.setString(5, idCard);
            pre.setString(6, phoneNumber);
            pre.setString(7, address);
            pre.setString(8, position);
            pre.setBoolean(9, active);
            int rs_1 = pre.executeUpdate();

        } catch (SQLException | IOException | JAXBException | ClassNotFoundException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ObservableList<Employee> loadEMP() throws ClassNotFoundException, SQLException,
            IOException, FileNotFoundException, JAXBException {
        Date date;
        String gender;
        String active;
        Connection conn = null;
        ObservableList listEmp = FXCollections.observableArrayList();
        try {
            conn = ControllerDB.connectDB.connectSQL();

            String sql = "Select * from Employ  ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getBoolean("gender") == true) {
                    gender = "male";
                } else {
                    gender = "female";
                }
                if(rs.getBoolean("active")==true){
                    active = "active";
                }else{
                    active = "unactive";
                }
                Employee emp = new Employee(rs.getString("idEmploy"), rs.getString("name"), rs.getDate("age"),
                     gender, rs.getString("idCard"), rs.getString("phoneNumber"), rs.getString("address"), rs.getString("position"),active);
                listEmp.add(emp);
            }

        } catch (JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listEmp;

    }


    public static void updateEmploy(String idEmploy, String name, java.sql.Date sqlDATE, boolean gender, String phoneNumber, String address, String position,boolean active) {

        try {
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " update Employ "
                    + " set name = ?,age = ?,gender = ?,phoneNumber = ?,address = ?,position = ?, active = ? "
                    + " where idEmploy = ? ";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(8, idEmploy);
            pre.setString(1, name);
            pre.setDate(2, sqlDATE);
            pre.setBoolean(3, gender);
            pre.setString(4, phoneNumber);
            pre.setString(5, address);
            pre.setString(6, position);
            pre.setBoolean(7,active);
            int rs_1 = pre.executeUpdate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

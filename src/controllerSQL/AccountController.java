/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Account;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class AccountController {

    public static void updateforgotAccount(String userAcc, String emailAcc, String passAcc) throws ClassNotFoundException,
            SQLException, IOException, FileNotFoundException, JAXBException, NoSuchAlgorithmException {
        Connection conn = ControllerDB.connectDB.connectSQL();
        String sql = " Update Account"
                + " set passWord = ?"
                + "  where ( userName = ? ) and ( email = ?) ";
        PreparedStatement pre = conn.prepareCall(sql);
        pre.setString(1, Encrypt.Encrypt_MD5.encrypt(passAcc));
        pre.setString(2, userAcc);
        pre.setString(3, emailAcc);
        int rs = pre.executeUpdate();
    }

    public static void addnewAccountSQL(String userAcc, String passAcc, String emailAcc, String idEMP) {
        try {
            String idAcount = "";
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = "Select top(1) idAccount  from Account order by idAccount desc ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            if (rs.next()) {
                idAcount = rs.getString("idAccount");
            }
            if (!idAcount.isEmpty()) {
                int i = Integer.valueOf(idAcount.substring(3, idAcount.length())) + 1;
                if (i >= 0 && i <= 9) {
                    idAcount = idAcount.substring(0, 3) + "00" + i;
                } else {
                    if (i >= 10 && i <= 99) {
                        idAcount = idAcount.substring(0, 3) + "0" + i;
                    } else {
                        idAcount = idAcount.substring(0, 3) + i;
                    }
                }
            }
            sql = " Insert into Account "
                    + " Values ( ?,?,?,?,? )";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idAcount);
            preparedStatement.setString(2, userAcc);
            preparedStatement.setString(3, Encrypt.Encrypt_MD5.encrypt(passAcc));
            preparedStatement.setString(4, emailAcc);
            preparedStatement.setString(5, idEMP);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException | NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void updateAccountSQL(String userAcc, String emailAcc, String passAcc) throws NoSuchAlgorithmException {
        try {
            String sql = "";
            Connection conn = ControllerDB.connectDB.connectSQL();
            if (!emailAcc.equals("") && (passAcc.equals(""))) {
                sql = " update Account "
                        + " set email = '" + emailAcc + "'"
                        + " where userName = '" + userAcc + "'";
            } else if (emailAcc.equals("") && (!passAcc.equals(""))) {
                sql = " update Account "
                        + " set passWord = '" + Encrypt.Encrypt_MD5.encrypt(passAcc) + "'"
                        + " where userName = '" + userAcc + "'";
            } else {
                sql = " update Account "
                        + " set email ='" + emailAcc + "' ,passWord = '" + Encrypt.Encrypt_MD5.encrypt(passAcc) + "'"
                        + " where userName = '" + userAcc + "'";
            }
            Statement stt = conn.createStatement();            
            int rs = stt.executeUpdate(sql);
            System.out.println(1);

        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteAccountSQL(String idAccount) {
        try {
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " Delete from Account"
                    + " where (idAccount = ?) and ( idAccount <> 'MAN001') ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, idAccount);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(AccountController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ObservableList<Account> loadAcc() throws ClassNotFoundException, SQLException,
            IOException, FileNotFoundException, JAXBException {

        Connection conn = null;
        ObservableList listacc = FXCollections.observableArrayList();
        try {
            conn = ControllerDB.connectDB.connectSQL();

            String sql = "Select * from Account  ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {

                Account account = new Account(rs.getString("idAccount"), rs.getString("userName"), rs.getString("passWord"), rs.getString("email"), rs.getString("idEEmploy"));
                listacc.add(account);
            }

        } catch (JAXBException ex) {
            Logger.getLogger(EMPcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listacc;

    }

}

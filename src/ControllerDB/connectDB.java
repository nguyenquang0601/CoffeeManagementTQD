/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerDB;

import Model.ConnectSQL;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Dell Laptop
 */
public class connectDB {

    String user, password;
    static ConnectSQL conSQL;

    public static void setconDB() throws FileNotFoundException, ClassNotFoundException, SQLException, JAXBException {
        // Đọc file XML
        
        File file = new File("");
        file = new File(file.getAbsoluteFile()+ "/src/XML/DB.xml");
        conSQL = new ConnectSQL();
        FileInputStream dos = new FileInputStream(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(ConnectSQL.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        conSQL = (ConnectSQL) jaxbUnmarshaller.unmarshal(dos);
     

    }

    public static String readFile(String fileName) throws FileNotFoundException, IOException {
        File file = new File("");
        file = new File(file.getAbsolutePath() + "/src/ControllerDB/" + fileName);
        String sql = "";
        try (FileReader fileReader = new FileReader(file)) {
            int ch = 0;
            while ((ch = fileReader.read()) != -1) {
                sql += String.valueOf((char) ch);
            }
        }
        return sql;
    }
    public static void creatDatabase() throws FileNotFoundException, IOException, ClassNotFoundException {
        String sql = readFile("createDB.txt");
        try (Connection connection = connectDB.connectSQLServerToCreateDB();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Complete");
        } catch (SQLException ex) {

        }
    }
    public static Connection connectSQLServerToCreateDB() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection connection = DriverManager.getConnection(conSQL.getLocalhost()+ "user=" + conSQL.getName() + ";password=" + conSQL.getPassword());
        return connection;
    }
    public static Connection connectSQL() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        setconDB();
        String url = null;
        String sql = readFile("createDB.txt");
        String DBname = sql.substring(sql.lastIndexOf(" ")).trim();
        Connection connection = DriverManager.getConnection(conSQL.getLocalhost() + "user=" + conSQL.getName() + ";password=" + conSQL.getPassword() + ";databasename=" + DBname);
        return connection;
    }
    public static void creatTable() throws IOException, ClassNotFoundException, SQLException, FileNotFoundException, JAXBException {
        String sql1 = connectDB.readFile("Employ.txt");
        String sql2 = connectDB.readFile("Account.txt");
    //    String sql4 = connectDB.readFile("AccessAll.txt");
        String sql5 = connectDB.readFile("Menucategory.txt");
        String sql6 = connectDB.readFile("Menu.txt");
        String sql7 = connectDB.readFile("StartOrder.txt");
        String sql8 = connectDB.readFile("Bill.txt");
        String sql9 = connectDB.readFile("RawMaterials.txt");
        try (Connection connection = connectDB.connectSQL();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
    //        statement.executeUpdate(sql4);
            statement.executeUpdate(sql5);
            statement.executeUpdate(sql6);
            statement.executeUpdate(sql7);
            statement.executeUpdate(sql8);
            statement.executeUpdate(sql9);
            System.out.println("table");
        } catch (SQLException ex) {   
            System.out.println(ex.getMessage());
        }
    }
    public static boolean CheckDB() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = connectSQLServerToCreateDB();
        String url = null;
        String sql = readFile("createDB.txt");
        String DBname = sql.substring(sql.lastIndexOf(" ")).trim();
        ResultSet rs = conn.getMetaData().getCatalogs();
        while (rs.next()) {
            String dbname = rs.getString(1);
            if (dbname.equals(DBname)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkConnect() throws SQLException {
        Connection conn = null;
        try {
            setconDB();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = connectSQLServerToCreateDB();
        } catch (FileNotFoundException | ClassNotFoundException | SQLException | JAXBException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

}

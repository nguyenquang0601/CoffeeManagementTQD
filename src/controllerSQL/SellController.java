/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerSQL;

import Model.Sell;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author nguyenquang
 */
public class SellController {

    public static ObservableList<Sell> loadSELL() {
        ObservableList listSell = FXCollections.observableArrayList();
        try {
            Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " select MEnuCategory.name,Menu.nameFood,Menu.promotion,Menu.price,Menu.countExcess"
                    + " from Menu right join MEnuCategory on Menu.idMenuCategory = MEnuCategory.idMenuCategory "
                    + " where not (Menu.idMenuCategory is null) "
                    + " order by Menu.nameFood ";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Sell sell = new Sell(rs.getString("name"), rs.getString("nameFood"),
                        rs.getFloat("promotion"), rs.getFloat("price"), rs.getInt("countExcess"));
                listSell.add(sell);
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(SellController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSell;
    }

}

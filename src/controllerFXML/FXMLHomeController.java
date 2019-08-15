/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerFXML;

import Model.Account;
import Model.BillAll;
import Model.Employee;
import Model.Ingredients;
import Model.Menu;
import Model.MenuCategory;
import Model.Sell;
import View.StartLOGIN_LOGUP;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static controllerFXML.FXMLController_Lognin.idEMPLOY;
import Model.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static controllerFXML.FXMLHome_PAGE1_Controller.idPAGE;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBException;

/**
 *
 * @author BoomIT wait MTV
 */
public class FXMLHomeController implements Initializable {
//    @FXML
//    private AnchorPane centerPage;

    public static String idBillPayment;
    private Stage stageConfirmPayment;
    @FXML
    private JFXButton btnLognOut;
    @FXML
    private Button btnManager;
    @FXML
    private Button btnSell;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAboutUs;
    @FXML
    private AnchorPane paneHome;
    @FXML
    private AnchorPane paneSell;
    @FXML
    private AnchorPane paneManager;
    @FXML
    private AnchorPane paneAboutUs;

    //
    @FXML
    private Button btnAcount;
    @FXML
    private Button btnEmployee;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnIgre;
    @FXML
    private Button btnSales;
    @FXML
    private Button btnMenuCategory;
    @FXML
    private Button btnMenuFood;
    //
    @FXML
    private AnchorPane paneAcount, paneEmployee, paneMenu, paneIgre, paneSales, paneMenuCategory, paneMenuFood;
    @FXML
    // EMPLOYEE
    private JFXButton btnEmpAdd, btnEmpUpdate;
    @FXML
    private JFXTextField textEmpSearch, textEmpName, textEmpPhone, textEmpAddress, textEmpidcard;
    @FXML
    private Label idEmployee;
    @FXML
    private ComboBox<String> textEmpPosition;
    @FXML
    private CheckBox male, female, unActiveEmp, activeEmp;
    @FXML
    private DatePicker empBirthday;
    @FXML
    private TableView<Employee> tableEmployee = new TableView<Employee>();
    @FXML
    private TableColumn<Employee, String> codeEMP = new TableColumn<>("CODE");
    @FXML
    private TableColumn<Employee, String> nameEMP = new TableColumn<>("NAME");
    @FXML
    private TableColumn<Employee, Date> birthdayEMP = new TableColumn<>("BIRTHDAY");
    @FXML
    private TableColumn<Employee, String> genderEMP = new TableColumn<>("GENDER");
    @FXML
    private TableColumn<Employee, String> idcardEMP = new TableColumn<>("IDCARD");
    @FXML
    private TableColumn<Employee, String> phoneEMP = new TableColumn<>("PHONE");
    @FXML
    private TableColumn<Employee, String> addressEMP = new TableColumn<>("ADDRESS");
    @FXML
    private TableColumn<Employee, String> positionEMP = new TableColumn<>("POSITION");
    @FXML
    private TableColumn<Employee, String> activeEMP = new TableColumn<>("ACTIVE");

    ObservableList<Employee> ListEMP = FXCollections.observableArrayList();

    //Account
    ObservableList<Account> ListAccount = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnAccountAdd, btnAccountDelete, btnAccountUpdate;
    @FXML
    private JFXTextField textAccountUsername, textAccountSearch, textEmailAccount;
    @FXML
    private JFXPasswordField textAccountPassword;
    @FXML
    private JFXComboBox<String> textIDEMPACC;
    @FXML
    private Label idAcount;
    @FXML
    private TableView<Account> tableAccount = new TableView<Account>();
    @FXML
    private TableColumn<Account, String> idAccount = new TableColumn<>("IDACC");
    @FXML
    private TableColumn<Account, String> userAccount = new TableColumn<>("USER");
    @FXML
    private TableColumn<Account, String> passAccount = new TableColumn<>("PASSWORD");
    @FXML
    private TableColumn<Account, String> emailAccount = new TableColumn<>("EMAIL");
    @FXML
    private TableColumn<Account, String> idEmployAcc = new TableColumn<>("IDEMP");
    public static String userUpdate;
    //xử lý Ingredients
    @FXML
    private Label idIngre;
    @FXML
    private TableView<Ingredients> tableIngredients = new TableView<Ingredients>();
    @FXML
    private TableColumn<Ingredients, String> idIngreColumn = new TableColumn<>("ID");
    @FXML
    private TableColumn<Ingredients, String> nameIngreColumn = new TableColumn<>("NAME");
    @FXML
    private TableColumn<Ingredients, Float> priceIngreColumn = new TableColumn<>("PRICE");
    @FXML
    private TableColumn<Ingredients, String> dateInputIngreColumn = new TableColumn<>("DATEINPUT");
    @FXML
    private TableColumn<Ingredients, Integer> countInputIngreColumn = new TableColumn<>("COUNT");
    @FXML
    private JFXTextField textSearchIngredients, textMassIngre, textPriceIngre;
    @FXML
    private ComboBox<String> textNameIngre;
    @FXML
    private DatePicker dateInputIngre;
    @FXML
    private JFXButton btnAddIngre, btnUpdateIngre;
    ObservableList<Ingredients> ListIngredients = FXCollections.observableArrayList();

    //xu ly MenuCategory
    @FXML
    private TableView<MenuCategory> tableCategory = new TableView<MenuCategory>();
    @FXML
    private TableColumn<MenuCategory, String> idMenuCateColumn = new TableColumn<>("ID MENUCATEGORY");
    @FXML
    private TableColumn<MenuCategory, String> nameFoodTypeColumn = new TableColumn<>("FOOD TYPE");
    @FXML
    private JFXTextField textFoodType, searchCategory;
    @FXML
    private Label textIdMC;
    @FXML
    private JFXButton btnAddMC, btnUpdateMC;

    ObservableList<MenuCategory> ListMenuCate = FXCollections.observableArrayList();
    // Xuli menu food and drink
    @FXML
    private TableView<Menu> tableMenuFood = new TableView<Menu>();
    @FXML
    private TableColumn<Menu, String> idMenuColumn = new TableColumn<>("ID MENU");
    @FXML
    private TableColumn<Menu, String> foodTypeColumn = new TableColumn<>("FOODTYPE");
    @FXML
    private TableColumn<Menu, String> nameFoodColumn = new TableColumn<>("NAME FOOD");
    @FXML
    private TableColumn<Menu, Float> priceFoodColumn = new TableColumn<>("PRICE");
    @FXML
    private TableColumn<Menu, Float> promotionColumn = new TableColumn<>("PROMOTION");
    @FXML
    private TableColumn<Menu, Integer> countExcessColumn = new TableColumn<>("COUNTEXCESS");
    @FXML
    private JFXButton btnAddfood, btnUpdateFood;
    @FXML
    private Label idMenuFood;
    @FXML
    private JFXComboBox<String> nameTypeFood;
    @FXML
    private JFXTextField searchMenuFood, textFoodName, textPriceFood, textPromotionFood;
    // TABLE SELL
    @FXML
    private TableView<Sell> tableMenuSell = new TableView<>();
    @FXML
    private TableColumn<Sell, String> typefoodSELL = new TableColumn<>("TYPE FOOD");
    @FXML
    private TableColumn<Sell, String> namefoodSELL = new TableColumn<>("NAME FOOD");
    @FXML
    private TableColumn<Sell, Float> promotionfoodSELL = new TableColumn<>("PROMOTION");
    @FXML
    private TableColumn<Sell, Float> pricefoodSELL = new TableColumn<>("PRICE");
    @FXML
    private TableColumn<Sell, Integer> countfoodSELL = new TableColumn<>("COUNT EXCESS");
    @FXML
    private Label idEMPSELL;
    ObservableList<Sell> ListSELL = FXCollections.observableArrayList();

//    xử ly Order
    @FXML
    private JFXButton btnDeleteOrder, btnNewOrder, btnOrder;
    @FXML
    private Label idBillOrder, labelTimeOrder, labelTotalBill;
    // SET VALUE LABEL TOTAL BILL
    float total = 0;
    @FXML
    private TableView<Order> tableOrder = new TableView<>();
    // Select table order delete
    Order order;

    @FXML
    private TableColumn<Order, String> nameFoodOrderColumn = new TableColumn<>("NAMEFOOD");
    @FXML
    private TableColumn<Order, Integer> countOrderColumn = new TableColumn<>("COUNTORDER");
    @FXML
    private TableColumn<Order, Float> priceFoodOrderColumn = new TableColumn<>("PRICE");
    ObservableList<Order> listOrder = FXCollections.observableArrayList();
    ObservableList<Order> listOrder_lie = FXCollections.observableArrayList();

    // xu ly table ingredients sale
    @FXML
    private JFXButton btnDoneSale;
    @FXML
    private JFXTextField textPriceIngredientsSale;
    @FXML
    private DatePicker fromDaySale, toDaySale;
    @FXML
    private TableView<Ingredients> tableIngredientsSale = new TableView<>();
    @FXML
    private TableColumn<Ingredients, String> nameIngredientsSale = new TableColumn<>("NAME");
    @FXML
    private TableColumn<Ingredients, Float> priceIngredientsSale = new TableColumn<>("PRICE");
    @FXML
    private TableColumn<Ingredients, Integer> countIngredientsSale = new TableColumn<>("COUNT");
    ObservableList<Ingredients> listIngredientsSale = FXCollections.observableArrayList();

    //xu ly Bill All sale
    @FXML
    private TableView<BillAll> tableBillAllSale = new TableView<>();
    @FXML
    private JFXTextField textPriceBillAllSale;
    @FXML
    private TableColumn<BillAll, String> idBillAllSaleColumn = new TableColumn<>("BILL ALL");
    @FXML
    private TableColumn<BillAll, String> idEmpBillAllSaleColumn = new TableColumn<>("ID EMP");
    @FXML
    private TableColumn<BillAll, LocalDateTime> timeBillAllSaleColumn = new TableColumn<>("TIME");
    @FXML
    private TableColumn<BillAll, Float> totalBillAllSaleColumn = new TableColumn<>("TOTAL");
    ObservableList<BillAll> listBillAllSale = FXCollections.observableArrayList();

    public FXMLHomeController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // EMPLOYEE
        try {
            // TODO
            // SET COLUMN TABLE EMPLOY
            setTableEMP();
            setListEMPPosition();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        female.setOnMouseClicked((MouseEvent evt) -> {
            if (male.isSelected() == true) {
                male.setSelected(false);
            }
        });
        male.setOnMouseClicked((MouseEvent evt) -> {
            if (female.isSelected() == true) {
                female.setSelected(false);
            }
        });
        activeEmp.setOnMouseClicked((MouseEvent evt) -> {
            if (unActiveEmp.isSelected() == true) {
                unActiveEmp.setSelected(false);
            }
        });
        unActiveEmp.setOnMouseClicked((MouseEvent evt) -> {
            if (activeEmp.isSelected() == true) {
                activeEmp.setSelected(false);
            }
        });
        btnEmpAdd.setOnMouseClicked((event) -> {
            addNewEMP();

        });
        tableEmployee.setOnMouseClicked((event) -> {
            selectEMP();
        });
        btnEmpUpdate.setOnMouseClicked((event) -> {
            updateEMP();
        });

        try {
            //Account
            setTableAcc();
            setListIDEMP();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAccountAdd.setOnMouseClicked((event) -> {
            addnewAccount();
        });
        btnAccountUpdate.setOnMouseClicked((event) -> {
            try {
                updateAccount();
                setListIDEMP();

            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnAccountDelete.setOnMouseClicked((event) -> {
            deleteAccount();
        });
        tableAccount.setOnMouseClicked((event) -> {
            selectAccount();
        });

        try {
            //Ingredients
            dateInputIngre.setValue(LocalDate.now());
            setTableIngre();
            setListNameINGRE();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAddIngre.setOnMouseClicked((MouseEvent e) -> {
            try {
                addNewIngre();
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btnUpdateIngre.setOnMouseClicked((MouseEvent e) -> {

            try {
                updateIngre();
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        tableIngredients.setOnMouseClicked((MouseEvent e) -> {
            selectIngre();
            // Click and DELETE ITEM
//              Ingredients ingredients = tableIngredients.getSelectionModel().getSelectedItem();
//              tableIngredients.getItems().remove(ingredients);
        });
        textSearchIngredients.setOnKeyReleased((KeyEvent e) -> {
            SearchInfoIngre();
        });

        try {
            //Menu Catelogy
            setTableMenuCate();

        } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAddMC.setOnMouseClicked((MouseEvent e) -> {
            try {
                addMC();
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnUpdateMC.setOnMouseClicked((MouseEvent e) -> {
            try {
                updateMENUCA();
            } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tableCategory.setOnMouseClicked((MouseEvent e) -> {
            selectMenuCate();
        });
        searchCategory.setOnKeyReleased((KeyEvent e) -> {
            SearchInfoMC();
        });
        try {
            // XU LY Menu
            setTableMenu();
            setListIDMenuCate();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnAddfood.setOnMouseClicked((MouseEvent e) -> {
            try {
                addNewMF();
                setListNameINGRE();
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tableMenuFood.setOnMouseClicked((MouseEvent e) -> {
            selectRowTableMenu();
        });
//        btnDelFood.setOnMouseClicked((MouseEvent e) -> {
//            try {
//                deleteRowMenuFood();
//            } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
//                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });
        btnUpdateFood.setOnMouseClicked((MouseEvent e) -> {
            try {
                updateRowMenuFood();
            } catch (SQLException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        searchMenuFood.setOnKeyReleased((KeyEvent e) -> {
            SearchInfoMF();
        });
        // Xử lí SELL FOOD
        setTableSELL();
        setTableOrder();
        listOrder.clear();
        listOrder_lie.clear();
        tableMenuSell.setOnMouseClicked((MouseEvent e) -> {
            selectRowTableSell();

        });
        //xử lý Order Food
        tableOrder.getColumns().clear();
        setTableOrder();

        tableOrder.setOnMouseClicked((MouseEvent event) -> {
            order = tableOrder.getSelectionModel().getSelectedItem();

        });
        total = 0;
        btnDeleteOrder.setOnMouseClicked((MouseEvent e) -> {
            total = total - order.getPriceOrder();
            // tableOrder.getItems().remove(order);
            int index = 0;
            for (int i = 0; i < listOrder.size(); i++) {
                if (listOrder.get(i).getNameMenuFood().equals(order.getNameMenuFood())) {
                    index = i;
                    break;
                }
            }
            for (int i = 0; i < tableMenuSell.getItems().size(); i++) {
                if (tableMenuSell.getItems().get(i).getFoodNAME().equals(order.getNameMenuFood())) {
                    tableMenuSell.getItems().get(i).setCount(tableMenuSell.getItems().get(i).getCount() + order.getCountOrder());
                }
            }
            tableMenuSell.refresh();
            listOrder.remove(index);
            tableOrder.setItems(listOrder);
            // - TOTAL BILL
            labelTotalBill.setText(String.valueOf(total));
            tableOrder.refresh();
        });
        // CLICK PAYMENT
        btnOrder.setOnMouseClicked((MouseEvent e) -> {
            if (!labelTotalBill.getText().equals("0.0")) {
                try {

                    inputBillALL();
                    inputBilldetail();
                    Alert alt = new Alert(Alert.AlertType.INFORMATION);
                    alt.setContentText("Payment access!");
                    alt.showAndWait();
                } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                    Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alt = new Alert(Alert.AlertType.ERROR);
                alt.setContentText("Please choose food");
                alt.showAndWait();
            }

            //inbill
//                idBillPayment = idBillOrder.getText();
//                Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_inBill.fxml"));
//                Scene scene = new Scene(root);
//                stageConfirmPayment = new Stage();
//                stageConfirmPayment.setScene(scene);
//                stageConfirmPayment.setResizable(false);
//                stageConfirmPayment.setTitle("Invoice");
////                stageConfirmPayment.getIcons().add(new Image(getClass().getResourceAsStream("/Images/Capture2.PNG")));
//                stageConfirmPayment.showAndWait();
        });
        resetOrder();

        // CLICK RESET ORDER
        btnNewOrder.setOnMouseClicked((MouseEvent evt) -> {
            Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
            alt.setTitle("COMFIRMMATION");
            alt.setContentText("Do you want to reset this order?");

            Optional<ButtonType> button = alt.showAndWait();
            if (button.get() == ButtonType.OK) {
                resetOrder();
            }

        });

        try {
            //xu ly ingredients sale
            setTableIngreSale();
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnDoneSale.setOnMouseClicked((MouseEvent e) -> {
            if (fromDaySale.getValue() == null | toDaySale.getValue() == null) {
                try {
                    listIngredientsSale.clear();
                    listBillAllSale.clear();

                    listBillAllSale = controllerSQL.BillAllController.loadDataBillAllSale();
                    tableBillAllSale.setItems(listBillAllSale);

                    listIngredientsSale = controllerSQL.IngredientsController.loadDataIngre();
                    tableIngredientsSale.setItems(listIngredientsSale);
                } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
                    Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    chooseDateShowIngre();
                    chooseDateShowBillAll();
                } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                    Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            totalPriceIngreSale();
            totalPriceBillAllSale();
        });
//        btnSales.setOnMouseClicked((MouseEvent e) -> {
//            try {
//
//            } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
//                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

        try {
            //xu ly BillAll sale
            setColumnTableBillSale();
        } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //click btn show pane
        btnSell.setOnMouseClicked((MouseEvent e) -> {
            paneHome.setVisible(false);
            paneSell.setVisible(true);
            paneManager.setVisible(false);
            paneAboutUs.setVisible(false);

            ListSELL = controllerSQL.SellController.loadSELL();
            tableMenuSell.setItems(ListSELL);
            tableMenuSell.setVisible(true);
        });
        if (controllerFXML.FXMLController_Lognin.idAccount.equals("MAN001")) {
            System.out.println(controllerFXML.FXMLController_Lognin.idAccount);
            btnManager.setDisable(false);
        } else {
            btnManager.setDisable(true);
        }
        btnManager.setOnMouseClicked((MouseEvent e) -> {
            try {
                paneHome.setVisible(false);
                paneSell.setVisible(false);
                paneManager.setVisible(true);
                paneAboutUs.setVisible(false);
                listIngredientsSale = controllerSQL.IngredientsController.loadDataIngre();
                tableIngredientsSale.setItems(listIngredientsSale);
                tableIngredientsSale.setVisible(true);
                listBillAllSale.clear();
                listBillAllSale = controllerSQL.BillAllController.loadDataBillAllSale();
                tableBillAllSale.setItems(listBillAllSale);
                tableBillAllSale.setVisible(true);
            } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        if (FXMLHome_PAGE1_Controller.idPAGE == 1) {
            paneSell.setVisible(true);
            paneManager.setVisible(false);
        } else if (FXMLHome_PAGE1_Controller.idPAGE == 2) {
            paneSell.setVisible(false);
            paneManager.setVisible(true);
        }
        btnHome.setOnMouseClicked((event) -> {
            idPAGE = 3;
            showpane();
        });
        btnAboutUs.setOnMouseClicked((event) -> {
            idPAGE = 4;
            showpane();
        });

        ///////
        btnAcount.setOnMouseClicked((MouseEvent e) -> {
            setListIDEMP();
            paneAcount.setVisible(true);
            paneEmployee.setVisible(false);
            paneMenu.setVisible(false);
            paneIgre.setVisible(false);
            paneSales.setVisible(false);
        });
        btnEmployee.setOnMouseClicked((MouseEvent e) -> {

            paneAcount.setVisible(false);
            paneEmployee.setVisible(true);
            paneMenu.setVisible(false);
            paneIgre.setVisible(false);
            paneSales.setVisible(false);

        });
        btnMenu.setOnMouseClicked((MouseEvent e) -> {
            paneAcount.setVisible(false);
            paneEmployee.setVisible(false);
            paneMenu.setVisible(true);
            paneIgre.setVisible(false);
            paneSales.setVisible(false);
        });
        btnIgre.setOnMouseClicked((MouseEvent e) -> {
            paneAcount.setVisible(false);
            paneEmployee.setVisible(false);
            paneMenu.setVisible(false);
            paneIgre.setVisible(true);
            paneSales.setVisible(false);
        });
        btnSales.setOnMouseClicked((MouseEvent e) -> {
            try {
                paneAcount.setVisible(false);
                paneEmployee.setVisible(false);
                paneMenu.setVisible(false);
                paneIgre.setVisible(false);
                paneSales.setVisible(true);
                listIngredientsSale = controllerSQL.IngredientsController.loadDataIngre();
                tableIngredientsSale.setItems(listIngredientsSale);
                tableIngredientsSale.setVisible(true);
                listBillAllSale.clear();
                listBillAllSale = controllerSQL.BillAllController.loadDataBillAllSale();
                tableBillAllSale.setItems(listBillAllSale);
                tableBillAllSale.setVisible(true);
            } catch (SQLException | ClassNotFoundException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        btnMenuCategory.setOnMouseClicked((MouseEvent e) -> {
            paneMenuCategory.setVisible(true);
            paneMenuFood.setVisible(false);
        });
        btnMenuFood.setOnMouseClicked((MouseEvent e) -> {
            setListIDMenuCate();
            try {
                Listmenu = controllerSQL.MenuCotroller.loadMenu();
                tableMenuFood.setItems(Listmenu);
                tableMenuFood.setVisible(true);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            paneMenuCategory.setVisible(false);
            paneMenuFood.setVisible(true);

        });

        textEmpSearch.setOnKeyReleased((KeyEvent e) -> {
            SearchInfoEmployee();
        });
        textAccountSearch.setOnKeyReleased((KeyEvent evt) -> {
            SearchInfoAccount();
        });
        // LOGN OUT
        btnLognOut.setOnMouseClicked((event) -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Signin.fxml"));
                Scene scene = new Scene(root);
                StartLOGIN_LOGUP.MainSignup.setScene(scene);
                StartLOGIN_LOGUP.MainSignup.show();
                FXMLController_Lognin.Homestage.close();
            } catch (IOException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    // Xử lí Employ
    private void setTableEMP() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        tableEmployee.getColumns().addAll(codeEMP, nameEMP, birthdayEMP, genderEMP, idcardEMP, phoneEMP, addressEMP, positionEMP, activeEMP);
        codeEMP.setCellValueFactory(new PropertyValueFactory<>("codeEMP"));
        nameEMP.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdayEMP.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        genderEMP.setCellValueFactory(new PropertyValueFactory<>("gender"));
        idcardEMP.setCellValueFactory(new PropertyValueFactory<>("idCard"));
        phoneEMP.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressEMP.setCellValueFactory(new PropertyValueFactory<>("address"));
        positionEMP.setCellValueFactory(new PropertyValueFactory<>("position"));
        activeEMP.setCellValueFactory(new PropertyValueFactory<>("active"));
        codeEMP.setPrefWidth(0.1 * tableEmployee.getPrefWidth());
        nameEMP.setPrefWidth(0.15 * tableEmployee.getPrefWidth());
        birthdayEMP.setPrefWidth(0.1 * tableEmployee.getPrefWidth());
        idcardEMP.setPrefWidth(0.2 * tableEmployee.getPrefWidth());
        genderEMP.setPrefWidth(0.1 * tableEmployee.getPrefWidth());
        phoneEMP.setPrefWidth(0.1 * tableEmployee.getPrefWidth());
        addressEMP.setPrefWidth(0.2 * tableEmployee.getPrefWidth());
        positionEMP.setPrefWidth(0.15 * tableEmployee.getPrefWidth());
        activeEMP.setPrefWidth(0.1 * tableEmployee.getPrefWidth());
        ListEMP = controllerSQL.EMPcontroller.loadEMP();
        tableEmployee.setItems(ListEMP);
        tableEmployee.setVisible(true);
    }

    private void addNewEMP() {
        boolean error = true;
        if (checkDetailEMP() == true) {
            for (int i = 0; i < ListEMP.size(); i++) {
                if (textEmpidcard.getText().equals(ListEMP.get(i).getIdCard()) == true) {
                    Alert alt = new Alert(Alert.AlertType.ERROR);
                    alt.setContentText("ID card already exists");
                    alt.showAndWait();
                    error = false;
                }

            }
            if (error == true) {
                java.util.Date date = java.util.Date.from(empBirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDATE = new java.sql.Date(date.getTime());
                boolean gender;
                if (male.isSelected() == true) {
                    gender = true;
                } else {
                    gender = false;
                }
                boolean active;
                if (activeEmp.isSelected() == true) {
                    active = true;
                } else {
                    active = false;
                }
                controllerSQL.EMPcontroller.addNewEMP(textEmpName.getText(), sqlDATE, gender, textEmpidcard.getText(), textEmpPhone.getText(), textEmpAddress.getText(), textEmpPosition.getValue(), active);
                // Reset Combox
                setListIDEMP();
                try {
                    ListEMP = controllerSQL.EMPcontroller.loadEMP();
                    tableEmployee.setItems(ListEMP);
                    tableEmployee.setVisible(true);

                } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                    Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                refreshEMP();
            }

        }
    }
    int indexselectEMP;

    private void selectEMP() {
        try {
            Employee employee = tableEmployee.getSelectionModel().getSelectedItem();
            indexselectEMP = tableEmployee.getSelectionModel().getSelectedIndex();
            idEmployee.setText(employee.getCodeEMP());
            textEmpName.setText(employee.getName());
            LocalDate date = LocalDate.parse(employee.getBirthday().toString());
            empBirthday.setValue(date);
            if (employee.getGender().equals("male")) {
                male.setSelected(true);
                female.setSelected(false);
            } else {
                male.setSelected(false);
                female.setSelected(true);
            }
            if (employee.getActive().equals("active")) {
                activeEmp.setSelected(true);
                unActiveEmp.setSelected(false);
            } else {
                activeEmp.setSelected(false);
                unActiveEmp.setSelected(true);
            }
            textEmpidcard.setText(employee.getIdCard());
            textEmpPhone.setText(employee.getPhoneNumber());
            textEmpAddress.setText(employee.getAddress());
            textEmpPosition.setValue(employee.getPosition());

        } catch (Exception e) {

        }

    }

    private void updateEMP() {
        if (checkDetailEMP() == true) {
            boolean error = true;
            Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
            alt.setTitle("COMFIRMMATION");
            alt.setContentText("Do you want to update this account?");

            Optional<ButtonType> button = alt.showAndWait();
            if (button.get() == ButtonType.OK) {
                for (int i = 0; i < ListEMP.size(); i++) {
                    if ((textEmpidcard.getText().equals(ListEMP.get(i).getIdCard()) == true)
                            && (i != indexselectEMP)) {
                        Alert alt1 = new Alert(Alert.AlertType.ERROR);
                        alt1.setContentText("ID card already exists");
                        alt1.showAndWait();
                        error = false;
                    }
                }

                if (error == true) {
                    try {
                        java.util.Date date = java.util.Date.from(empBirthday.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                        java.sql.Date sqlDATE = new java.sql.Date(date.getTime());
                        boolean gender;
                        if (male.isSelected() == true) {
                            gender = true;
                        } else {
                            gender = false;
                        }
                        boolean active;
                        if (activeEmp.isSelected() == true) {
                            active = true;
                        } else {
                            active = false;
                        }
                        controllerSQL.EMPcontroller.updateEmploy(idEmployee.getText(), textEmpName.getText(), sqlDATE, gender, textEmpPhone.getText(), textEmpAddress.getText(), textEmpPosition.getValue(), active);
                        ListEMP = controllerSQL.EMPcontroller.loadEMP();
                        tableEmployee.setItems(ListEMP);
                        tableEmployee.setVisible(true);
                        refreshEMP();
                    } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                        Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }

    private boolean checkDetailEMP() {

        Alert alt = new Alert(Alert.AlertType.ERROR);
        alt.setTitle("Error");
        if ((textEmpName.getText().equals("")) | (textEmpPhone.getText().equals("")) | (textEmpPosition.getValue().equals(""))
                | (textEmpAddress.getText().equals("")) | (textEmpidcard.getText().equals(""))
                | ((male.isSelected() == false) && (female.isSelected() == false)) | (empBirthday.getValue() == null)
                | ((activeEmp.isSelected() == false) && (unActiveEmp.isSelected() == false))) {

            alt.setContentText("Please do not leave it empty");
            alt.showAndWait();
            return false;

        }
        if ((utils.PatternValided.PatternName(textEmpName.getText()) == false)) {

            alt.setContentText("NAME : A-Z or a-z");
            alt.showAndWait();
            return false;
        }
        if ((Pattern.matches("^0\\d{9}$", textEmpPhone.getText()) == false)) {

            alt.setContentText("PHONE NUMBER : 0 - 9 !!!");
            alt.showAndWait();
            return false;
        }
        if (utils.PatternValided.PatternCMND(textEmpidcard.getText()) == false) {

            alt.setContentText("Invalid ID card");
            alt.showAndWait();
            return false;
        }

        return true;
    }

    private void refreshEMP() {
        idEmployee.setText(null);
        textEmpAddress.setText(null);
        textEmpName.setText(null);
        textEmpPhone.setText(null);
        textEmpidcard.setText(null);
        textEmpPosition.setValue("NULL");
        empBirthday.setValue(LocalDate.now());

    }

    //tìm kiếm gần đúng employee
    private void SearchInfoEmployee() {
        String gender;
        String active;
        if (textEmpSearch.getText().equals("")) {
            try {
                ListEMP = controllerSQL.EMPcontroller.loadEMP();
                tableEmployee.setItems(ListEMP);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                ListEMP.clear();
                String sql = "Select * from Employ where IdEmploy LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where name LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where age LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where gender LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where phoneNumber LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where address LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where position LIKE '%" + textEmpSearch.getText() + "%'"
                        + "UNION Select * from Employ where active LIKE '%" + textEmpSearch.getText() + "%'";
                java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
                PreparedStatement pm = conn.prepareStatement(sql);
                ResultSet rs = pm.executeQuery();
                while (rs.next()) {
                    if (rs.getBoolean("gender") == true) {
                        gender = "male";
                    } else {
                        gender = "female";
                    }
                    if (rs.getBoolean("active") == true) {
                        active = "active";
                    } else {
                        active = "unactive";
                    }
                    Employee emp = new Employee(rs.getString("idEmploy"), rs.getString("name"), rs.getDate("age"), rs.getString("idCard"), gender, rs.getString("phoneNumber"), rs.getString("address"), rs.getString("position"), active);
                    ListEMP.add(emp);
                }
                tableEmployee.setItems(ListEMP);

            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setListEMPPosition() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("NULL");
        list.add("MANAGER");
        list.add("EMPLOYEE");
        textEmpPosition.setItems(list);

    }

    //Xu ly Account
    public void LoadDataAccount() {
        try {
            ListAccount.clear();
            java.sql.Connection cn = ControllerDB.connectDB.connectSQL();
            PreparedStatement ps = cn.prepareStatement("select * from Account");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ListAccount.add(new Account(rs.getString("idAccount"), rs.getString("userName"), rs.getString("passWord"), rs.getString("email"), rs.getString("idEEmploy")));
            }
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableAccount.setItems(ListAccount);
    }

    private void setTableAcc() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        try {
            tableAccount.getColumns().addAll(idAccount, userAccount, passAccount, emailAccount, idEmployAcc);
            idAccount.setCellValueFactory(new PropertyValueFactory<>("idAccount"));
            userAccount.setCellValueFactory(new PropertyValueFactory<>("userAccount"));
            passAccount.setCellValueFactory(new PropertyValueFactory<>("passAccout"));
            passAccount.setVisible(false);
            emailAccount.setCellValueFactory(new PropertyValueFactory<>("emailAccount"));
            idEmployAcc.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));
            idAccount.setPrefWidth(0.2 * tableAccount.getPrefWidth());
            userAccount.setPrefWidth(0.2 * tableAccount.getPrefWidth());
            passAccount.setPrefWidth(0.0 * tableAccount.getPrefWidth());
            emailAccount.setPrefWidth(0.4 * tableAccount.getPrefWidth());
            idEmployAcc.setPrefWidth(0.2 * tableAccount.getPrefWidth());
            LoadDataAccount();
            tableAccount.setItems(ListAccount);
            tableAccount.setVisible(true);
        } catch (Exception e) {
        }

    }

    // ACCOUNT
    // Set COMBOBOX IDEMP IN ACCOUNT
    private void setListIDEMP() {
        try {
            ObservableList<String> listID = FXCollections.observableArrayList();
            java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " Select idEmploy  from Employ"
                    + " where active = 1  ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String code = rs.getString("idEmploy");
                listID.add(code);
            }
            listID.add("NULL");
            textIDEMPACC.setItems(listID);
            textIDEMPACC.setValue("NULL");
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void addnewAccount() {
        if (checkDetailAccount() == true) {
            try {
                Connection conn = ControllerDB.connectDB.connectSQL();
                String sql = " select userName from Account"
                        + " where userName = ? ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, textAccountUsername.getText());
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next() == true) {
                    Alert alt = new Alert(Alert.AlertType.ERROR);
                    alt.setTitle("Error");
                    alt.setContentText("Account already exists");
                    alt.showAndWait();
                } else {
                    controllerSQL.AccountController.addnewAccountSQL(textAccountUsername.getText(), textAccountPassword.getText(), textEmailAccount.getText(), textIDEMPACC.getValue());
                    LoadDataAccount();
                }

            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            refreshACC();
        }
    }

    private void selectAccount() {
        try {
            Account account = tableAccount.getSelectionModel().getSelectedItem();
            idAcount.setText(account.getIdAccount());
            textAccountUsername.setText(account.getUserAccount());
            textAccountPassword.setText(account.getPassAccout());
            textEmailAccount.setText(account.getEmailAccount());
            textIDEMPACC.setValue(account.getIdEmployee());
        } catch (Exception e) {
        }

    }

    private void updateAccount() throws NoSuchAlgorithmException {

        try {
            userUpdate = textAccountUsername.getText();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/FXML_UpdateAccount.fxml"));
            Stage update = new Stage();
            Scene scene = new Scene(root);
            update.setScene(scene);
            update.showAndWait();
            LoadDataAccount();
        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshACC();

    }

    private void deleteAccount() {
        Alert alt = new Alert(Alert.AlertType.CONFIRMATION);
        alt.setTitle("COMFIRMMATION");
        alt.setContentText("Do you want to delete this account?");

        Optional<ButtonType> button = alt.showAndWait();
        if (button.get() == ButtonType.OK) {
            controllerSQL.AccountController.deleteAccountSQL(idAcount.getText());
            LoadDataAccount();
        }
        refreshACC();
//        } else if ((button.get() == ButtonType.CANCEL) | (button.get() == ButtonType.CLOSE)) {
//            alt
//        }

    }

    private boolean checkDetailAccount() {
        Alert alt = new Alert(Alert.AlertType.ERROR);
        boolean Error = true;
        alt.setTitle("Error");
        if ((textAccountUsername.getText().equals("")) | (textAccountPassword.getText().equals("")
                | (textEmailAccount.getText().equals(""))
                | (textIDEMPACC.getValue().equals("NULL")))) {
            Error = false;
            alt.setContentText("Please do not leave it empty");
            alt.showAndWait();

        }
        if ((utils.PatternValided.PatternID(textAccountUsername.getText()) == false) && (Error == true)) {
            Error = false;
            alt.setContentText("ACCOUNT : (A-Z) or (a-z) and (0-9)"
                    + " Length : 6 characters longer ");
            alt.showAndWait();
        }

        if ((utils.PatternValided.PatternEmail(textEmailAccount.getText()) == false) && (Error == true)) {
            Error = false;
            alt.setContentText("Email Incorrect!!!");
            alt.showAndWait();
        }

        return Error;

    }

    private void refreshACC() {
        textEmailAccount.setText(null);
        textAccountPassword.setText(null);
        textAccountUsername.setText(null);
        textIDEMPACC.setValue("NULL");
    }

    //tìm kiếm gần đúng account
    private void SearchInfoAccount() {

        if (textAccountSearch.getText().equals("")) {
            try {
                ListAccount = controllerSQL.AccountController.loadAcc();
                tableAccount.setItems(ListAccount);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                ListAccount.clear();
                String sql = "Select * from Account where idAccount LIKE '%" + textAccountSearch.getText() + "%'"
                        + "UNION Select * from Account where userName LIKE '%" + textAccountSearch.getText() + "%'"
                        + "UNION Select * from Account where passWord LIKE '%" + textAccountSearch.getText() + "%'"
                        + "UNION Select * from Account where email LIKE '%" + textAccountSearch.getText() + "%'"
                        + "UNION Select * from Account where idEEmploy LIKE '%" + textAccountSearch.getText() + "%'";
                java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
                PreparedStatement pm = conn.prepareStatement(sql);
                ResultSet rs = pm.executeQuery();
                while (rs.next()) {

                    Account acc = new Account(rs.getString("idAccount"), rs.getString("userName"), rs.getString("passWord"), rs.getString("email"), rs.getString("idEEmploy"));
                    ListAccount.add(acc);
                }
                tableAccount.setItems(ListAccount);

            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //xử lý Ingredients
    private void setTableIngre() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        tableIngredients.getColumns().addAll(idIngreColumn, nameIngreColumn, countInputIngreColumn, priceIngreColumn, dateInputIngreColumn);
        idIngreColumn.setCellValueFactory(new PropertyValueFactory<>("idIngre"));
        nameIngreColumn.setCellValueFactory(new PropertyValueFactory<>("nameIngre"));
        countInputIngreColumn.setCellValueFactory(new PropertyValueFactory<>("countInput"));
        priceIngreColumn.setCellValueFactory(new PropertyValueFactory<>("priceIngre"));
        dateInputIngreColumn.setCellValueFactory(new PropertyValueFactory<>("dateInputIngre"));
        idIngreColumn.setPrefWidth(0.2 * tableAccount.getPrefWidth());
        nameIngreColumn.setPrefWidth(0.2 * tableAccount.getPrefWidth());
        countInputIngreColumn.setPrefWidth(0.2 * tableAccount.getPrefWidth());
        priceIngreColumn.setPrefWidth(0.2 * tableAccount.getPrefWidth());
        dateInputIngreColumn.setPrefWidth(0.2 * tableAccount.getPrefWidth());
        ListIngredients = controllerSQL.IngredientsController.loadDataIngre();
        tableIngredients.setItems(ListIngredients);
        tableIngredients.setVisible(true);
    }

    private void setListNameINGRE() {
        try {
            ObservableList<String> foodName = FXCollections.observableArrayList();
            java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " Select nameFood from Menu ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String typeFood = rs.getString("nameFood");
                foodName.add(typeFood);
            }
            foodName.add("NULL");
            textNameIngre.setItems(foodName);
            textNameIngre.setValue("NULL");
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addNewIngre() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        if (checkDeteailIngre() == true) {
            java.util.Date date = java.util.Date.from(dateInputIngre.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDATE = new java.sql.Date(date.getTime());
            controllerSQL.IngredientsController.addIngredients(textNameIngre.getValue(), textMassIngre.getText(), Float.parseFloat(textPriceIngre.getText()), sqlDATE);
            controllerSQL.IngredientsController.updateCountMenu(textNameIngre.getValue(), Integer.parseInt(textMassIngre.getText()));
            try {
                Listmenu = controllerSQL.MenuCotroller.loadMenu();
                tableMenuFood.setItems(Listmenu);
                tableMenuFood.setVisible(true);
                ListIngredients = controllerSQL.IngredientsController.loadDataIngre();
                tableIngredients.setItems(ListIngredients);
                tableIngredients.setVisible(true);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        System.out.println(controllerSQL.IngredientsController.getNewIDEmp());
    }
    Ingredients ingredients;

    private void selectIngre() {
        try {
            ingredients = tableIngredients.getSelectionModel().getSelectedItem();
            idIngre.setText(ingredients.getIdIngre());
            textNameIngre.setValue(ingredients.getNameIngre());
            textMassIngre.setText(String.valueOf(ingredients.getCountInput()));
            textPriceIngre.setText(String.valueOf(ingredients.getPriceIngre()));
            LocalDate date = LocalDate.parse(ingredients.getDateInputIngre().toString());
            dateInputIngre.setValue(date);
        } catch (Exception e) {
        }

    }

    public void updateIngre() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        if (checkDeteailIngre() == true) {
            controllerSQL.IngredientsController.deleteIngre(ingredients.getNameIngre(), ingredients.getCountInput());
            java.util.Date date = java.util.Date.from(dateInputIngre.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDATE = new java.sql.Date(date.getTime());
            controllerSQL.IngredientsController.UpdateIngre(idIngre.getText(), textNameIngre.getValue(), textMassIngre.getText(), Float.parseFloat(textPriceIngre.getText()), sqlDATE);

            controllerSQL.IngredientsController.updateCountMenu(textNameIngre.getValue(), Integer.parseInt(textMassIngre.getText()));
            Listmenu = controllerSQL.MenuCotroller.loadMenu();
            tableMenuFood.setItems(Listmenu);
            tableMenuFood.setVisible(true);
            ListIngredients = controllerSQL.IngredientsController.loadDataIngre();
            tableIngredients.setItems(ListIngredients);
            tableIngredients.setVisible(true);
        }
    }

    private void SearchInfoIngre() {

        if (textSearchIngredients.getText().equals("")) {
            try {
                ListIngredients = controllerSQL.IngredientsController.loadDataIngre();
                tableIngredients.setItems(ListIngredients);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                ListIngredients.clear();
                String sql = "Select * from RawMaterials where idIngredients LIKE '%" + textSearchIngredients.getText() + "%'"
                        + "UNION Select * from RawMaterials where name LIKE '%" + textSearchIngredients.getText() + "%'"
                        + "UNION Select * from RawMaterials where countInput LIKE '%" + textSearchIngredients.getText() + "%'"
                        + "UNION Select * from RawMaterials where price LIKE '%" + textSearchIngredients.getText() + "%'"
                        + "UNION Select * from RawMaterials where DateInput LIKE '%" + textSearchIngredients.getText() + "%'";
                java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
                PreparedStatement pm = conn.prepareStatement(sql);
                ResultSet rs = pm.executeQuery();
                while (rs.next()) {
                    Ingredients igd = new Ingredients(rs.getString("idIngredients"), rs.getString("name"), rs.getFloat("price"), rs.getDate("DateInput"), rs.getInt("countInput"));
                    ListIngredients.add(igd);
                }
                tableIngredients.setItems(ListIngredients);

            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean checkDeteailIngre() {
        // textNameIngre.getText(), textMassIngre.getText(), Float.parseFloat(textPriceIngre.getText()), sqlDATE)
        Alert alt = new Alert(Alert.AlertType.ERROR);
        alt.setTitle("Error");
        if ((textNameIngre.getValue().equals("NULL")) | (textPriceIngre.getText().equals(""))
                | (textMassIngre.getText().equals("")) | (dateInputIngre.getValue() == null)) {
            alt.setContentText("Please do not leave it empty");
            alt.showAndWait();
            return false;
        }
        if ((utils.PatternValided.PatternPrice(textPriceIngre.getText()) == false)
                | (utils.PatternValided.PatternPrice(textMassIngre.getText()) == false)) {
            alt.setContentText("Invalid price or mass");
            alt.showAndWait();
            return false;
        }
        return true;
    }

    //xu ly MENU CATE
    private void setTableMenuCate() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        tableCategory.getColumns().addAll(idMenuCateColumn, nameFoodTypeColumn);
        idMenuCateColumn.setCellValueFactory(new PropertyValueFactory<>("idMenuCategory"));
        nameFoodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("nameMenuCategory"));
        idMenuCateColumn.setPrefWidth(0.5 * tableCategory.getPrefWidth());
        nameFoodTypeColumn.setPrefWidth(0.5 * tableCategory.getPrefWidth());
        ListMenuCate = controllerSQL.MenuCategoryController.loadDataMenuCa();
        tableCategory.setItems(ListMenuCate);
        tableCategory.setVisible(true);
    }

    private void addMC() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        controllerSQL.MenuCategoryController.addMenuCate(textFoodType.getText());
        ListMenuCate = controllerSQL.MenuCategoryController.loadDataMenuCa();
        tableCategory.setItems(ListMenuCate);
        tableCategory.setVisible(true);
    }

    private void selectMenuCate() {
        try {
            MenuCategory MC = tableCategory.getSelectionModel().getSelectedItem();
            textIdMC.setText(MC.getIdMenuCategory());
            textFoodType.setText(MC.getNameMenuCategory());
        } catch (Exception e) {
        }

    }

    private void updateMENUCA() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        controllerSQL.MenuCategoryController.updateMenuCate(textIdMC.getText(), textFoodType.getText());
        ListMenuCate = controllerSQL.MenuCategoryController.loadDataMenuCa();
        tableCategory.setItems(ListMenuCate);
        tableCategory.setVisible(true);
    }

    private void SearchInfoMC() {

        if (searchCategory.getText().equals("")) {
            try {
                ListMenuCate = controllerSQL.MenuCategoryController.loadDataMenuCa();
                tableCategory.setItems(ListMenuCate);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                ListMenuCate.clear();
                String sql = "Select * from MEnuCategory where idMenuCategory LIKE '%" + searchCategory.getText() + "%'"
                        + "UNION Select * from MEnuCategory where name LIKE '%" + searchCategory.getText() + "%'";
                java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
                PreparedStatement pm = conn.prepareStatement(sql);
                ResultSet rs = pm.executeQuery();
                while (rs.next()) {
                    MenuCategory MC = new MenuCategory(rs.getString("idMenuCategory"), rs.getString("name"));
                    ListMenuCate.add(MC);
                }
                tableCategory.setItems(ListMenuCate);

            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    //MenuFOOD
    // Set COMBOBOX foodTyppe IN MenuFood
    private void setListIDMenuCate() {
        try {
            ObservableList<String> FoodType = FXCollections.observableArrayList();
            java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
            String sql = " Select name from MEnuCategory ";
            Statement stt = conn.createStatement();
            ResultSet rs = stt.executeQuery(sql);
            while (rs.next()) {
                String typeFood = rs.getString("name");
                FoodType.add(typeFood);
            }
            FoodType.add("NULL");
            nameTypeFood.setItems(FoodType);
            nameTypeFood.setValue("NULL");
        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ObservableList<Menu> Listmenu = FXCollections.observableArrayList();

    private void setTableMenu() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {

        tableMenuFood.getColumns().addAll(idMenuColumn, foodTypeColumn, nameFoodColumn, priceFoodColumn, promotionColumn, countExcessColumn);
        idMenuColumn.setCellValueFactory(new PropertyValueFactory<>("idMenu"));
        foodTypeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameFoodColumn.setCellValueFactory(new PropertyValueFactory<>("nameFood"));
        priceFoodColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        promotionColumn.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        countExcessColumn.setCellValueFactory(new PropertyValueFactory<>("countExcess"));

        idMenuColumn.setPrefWidth(0.2 * tableMenuFood.getPrefWidth());
        foodTypeColumn.setPrefWidth(0.2 * tableMenuFood.getPrefWidth());
        nameFoodColumn.setPrefWidth(0.2 * tableMenuFood.getPrefWidth());
        priceFoodColumn.setPrefWidth(0.2 * tableMenuFood.getPrefWidth());
        promotionColumn.setPrefWidth(0.2 * tableMenuFood.getPrefWidth());
        countExcessColumn.setPrefWidth(0.2 * tableMenuFood.getPrefWidth());
        Listmenu = controllerSQL.MenuCotroller.loadMenu();
        tableMenuFood.setItems(Listmenu);
        tableMenuFood.setVisible(true);
    }

    private void addNewMF() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        indexchoose = -1;
        if (checkDetailMenu(indexchoose) == true) {
            controllerSQL.MenuCotroller.addMenuFood(nameTypeFood.getValue(), textFoodName.getText(), Float.parseFloat(textPriceFood.getText()), Float.parseFloat(textPromotionFood.getText()));
            Listmenu = controllerSQL.MenuCotroller.loadMenu();
            tableMenuFood.setItems(Listmenu);
            tableMenuFood.setVisible(true);
        }

    }
    int indexchoose = -1;

    private void selectRowTableMenu() {
        try {
            Menu menu = tableMenuFood.getSelectionModel().getSelectedItem();
            indexchoose = tableMenuFood.getSelectionModel().getSelectedIndex();
            System.out.println(indexchoose);
            idMenuFood.setText(menu.getIdMenu());
            nameTypeFood.setValue(menu.getName());
            textFoodName.setText(menu.getNameFood());
            textPriceFood.setText(String.valueOf(menu.getPrice()));
            textPromotionFood.setText(String.valueOf(menu.getPromotion()));
        } catch (Exception e) {
        }
    }

    private void deleteRowMenuFood() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        controllerSQL.MenuCotroller.deleteMenuFood(idMenuFood.getText());
        Listmenu = controllerSQL.MenuCotroller.loadMenu();
        tableMenuFood.setItems(Listmenu);
        tableMenuFood.setVisible(true);
    }

    private void updateRowMenuFood() throws SQLException {
        if (checkDetailMenu(indexchoose) == true) {

            controllerSQL.MenuCotroller.updateMenuFood(idMenuFood.getText(), nameTypeFood.getValue(), textFoodName.getText(), Float.parseFloat(textPriceFood.getText()), Float.parseFloat(textPromotionFood.getText()));
//            controllerSQL.MenuCotroller.addMenuFood(nameTypeFood.getValue(), textFoodName.getText(), Float.parseFloat(textPriceFood.getText()), Float.parseFloat(textPromotionFood.getText()));

            try {
                Listmenu = controllerSQL.MenuCotroller.loadMenu();
            } catch (ClassNotFoundException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tableMenuFood.setItems(Listmenu);
            tableMenuFood.setVisible(true);
            setListNameINGRE();
        }
    }

    private void SearchInfoMF() {

        if (searchMenuFood.getText().equals("")) {
            try {
                Listmenu = controllerSQL.MenuCotroller.loadMenu();
                tableMenuFood.setItems(Listmenu);
            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                Listmenu.clear();
                String sql = "Select * from Menu where idMenu LIKE '%" + searchMenuFood.getText() + "%'"
                        + "UNION Select * from Menu where idMenuCategory LIKE '%" + searchMenuFood.getText() + "%'"
                        + "UNION Select * from Menu where nameFood LIKE '%" + searchMenuFood.getText() + "%'"
                        + "UNION Select * from Menu where price LIKE '%" + searchMenuFood.getText() + "%'"
                        + "UNION Select * from Menu where promotion LIKE '%" + searchMenuFood.getText() + "%'"
                        + "UNION Select * from Menu where countExcess LIKE '%" + searchMenuFood.getText() + "%'";
                java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
                PreparedStatement pm = conn.prepareStatement(sql);
                ResultSet rs = pm.executeQuery();
                while (rs.next()) {
                    Menu menu = new Menu(rs.getString("idMenu"), rs.getString("idMenuCategory"), rs.getString("nameFood"), rs.getFloat("price"), rs.getFloat("promotion"), rs.getInt("countExcess"));
                    Listmenu.add(menu);
                }
                tableMenuFood.setItems(Listmenu);

            } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
                Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean checkDetailMenu(int check) {
//        nameTypeFood.getValue(), textFoodName.getText(), Float.parseFloat(textPriceFood.getText()
//), Float.parseFloat(textPromotionFood.getText()
        Alert alt = new Alert(Alert.AlertType.ERROR);
        alt.setTitle("Error");

        if ((nameTypeFood.getValue().equals("NULL")) | (textPriceFood.getText().equals(""))
                | (textFoodName.getText().equals("")) | (textPromotionFood.getText().equals(""))) {
            alt.setContentText("Please do not leave it empty");
            alt.showAndWait();
            return false;
        }
        for (int i = 0; i < Listmenu.size(); i++) {
            if (i != indexchoose) {
                //System.out.println(Listmenu.get(i).getNameFood());
                if (textFoodName.getText().equals(Listmenu.get(i).getNameFood())) {
                    alt.setContentText("Menu already exists");
                    alt.showAndWait();
                    return false;
                }
            }
        }
        if ((utils.PatternValided.PatternPrice(textPriceFood.getText()) == false)
                | (utils.PatternValided.PatternPrice(textPromotionFood.getText()) == false)) {
            alt.setContentText("Invalid price or promotion");
            alt.showAndWait();
            return false;
        }
        return true;
    }
    // Xử lí của SELL

    private void setTableSELL() {
        tableMenuSell.getColumns().addAll(typefoodSELL, namefoodSELL, promotionfoodSELL, pricefoodSELL, countfoodSELL);
        typefoodSELL.setCellValueFactory(new PropertyValueFactory<>("foodTYPE"));
        namefoodSELL.setCellValueFactory(new PropertyValueFactory<>("foodNAME"));
        promotionfoodSELL.setCellValueFactory(new PropertyValueFactory<>("promotion"));
        pricefoodSELL.setCellValueFactory(new PropertyValueFactory<>("price"));
        countfoodSELL.setCellValueFactory(new PropertyValueFactory<>("count"));
        typefoodSELL.setPrefWidth(0.25 * tableMenuSell.getPrefWidth());
        namefoodSELL.setPrefWidth(0.25 * tableMenuSell.getPrefWidth());
        promotionfoodSELL.setPrefWidth(0.25 * tableMenuSell.getPrefWidth());
        pricefoodSELL.setPrefWidth(0.25 * tableMenuSell.getPrefWidth());
        countfoodSELL.setPrefWidth(0.25 * tableMenuSell.getPrefWidth());
        idEMPSELL.setText(idEMPLOY);
        ListSELL = controllerSQL.SellController.loadSELL();
        tableMenuSell.setItems(ListSELL);
        tableMenuSell.setVisible(true);
    }

    private void selectRowTableSell() {

        try {
            Sell sell = tableMenuSell.getSelectionModel().getSelectedItem();
            if (sell.getCount() > 0) {
                int index = tableMenuSell.getSelectionModel().getSelectedIndex();
                tableMenuSell.getItems().get(index).setCount(tableMenuSell.getItems().get(index).getCount() - 1);
                tableMenuSell.refresh();
                Order order = new Order();
                int count = 0;
//        listOrder = tableOrder.getSelectionModel().getSelectedItems();
                for (int i = 0; i < listOrder.size(); i++) {
                    if (listOrder.get(i).getNameMenuFood().equals(sell.getFoodNAME())) {
                        listOrder.get(i).setCountOrder(listOrder.get(i).getCountOrder() + 1);
                        listOrder.get(i).setPriceOrder(listOrder.get(i).getPriceOrder() + (sell.getPrice() * (1 - sell.getPrice() / 100)));
                        order = new Order(listOrder.get(i).getNameMenuFood(), listOrder.get(i).getCountOrder(), listOrder.get(i).getPriceOrder());

                        count = 1;

                        listOrder.add(order);
                        break;
                    }

                }
                if (count == 0) {
                    order = new Order(sell.getFoodNAME(), 1, (sell.getPrice() * (1 - sell.getPrice() / 100)));
                    listOrder.add(order);
                } else {
                    listOrder_lie.clear();
                    for (int i = 0; i < listOrder.size() - 1; i++) {
                        listOrder_lie.add(listOrder.get(i));
                    }
                    tableOrder.refresh();
                    tableOrder.setItems(listOrder_lie);
                    listOrder.remove(listOrder.size() - 1);

                }

                total = 0;
                for (int i = 0; i < listOrder.size(); i++) {
                    total = total + (listOrder.get(i).getPriceOrder() - (listOrder.get(i).getPriceOrder() * listOrder.get(i).getPromotion()));
                }
                labelTotalBill.setText(String.valueOf(total));
            } else {
                Alert alt = new Alert(Alert.AlertType.ERROR);
                alt.setContentText("Dont enough food!");
                alt.showAndWait();
            }

        } catch (Exception e) {

        }

    }

    //xử lý order
    private void setTableOrder() {
//        tableOrder.getColumns().clear();
        tableOrder.getColumns().addAll(nameFoodOrderColumn, countOrderColumn, priceFoodOrderColumn);
        nameFoodOrderColumn.setCellValueFactory(new PropertyValueFactory<>("nameMenuFood"));
        countOrderColumn.setCellValueFactory(new PropertyValueFactory<>("countOrder"));
        priceFoodOrderColumn.setCellValueFactory(new PropertyValueFactory<>("priceOrder"));
        nameFoodOrderColumn.setPrefWidth(0.3 * tableOrder.getPrefWidth());
        countOrderColumn.setPrefWidth(0.3 * tableOrder.getPrefWidth());
        priceFoodOrderColumn.setPrefWidth(0.3 * tableOrder.getPrefWidth());
//        listOrder = controllerSQL.OrderController.loadDateOrder();
        tableOrder.setItems(listOrder);
        tableOrder.setVisible(true);
    }

    private void inputBillALL() {
        LocalDateTime timeOrder = LocalDateTime.now();
        labelTimeOrder.setText(timeOrder.toString());
        String idOrder = null;
        idOrder = controllerSQL.OrderController.inputBillAllSQL(timeOrder.toString(), total, idEMPLOY);
        idBillOrder.setText(idOrder);
    }

    private void resetOrder() {

        listOrder.clear();
        listOrder_lie.clear();
        tableOrder.setItems(listOrder);
        labelTotalBill.setText("0.0");
        labelTimeOrder.setText("");
        idBillOrder.setText("IDB");
        ListSELL = controllerSQL.SellController.loadSELL();
        tableMenuSell.setItems(ListSELL);
        tableMenuSell.setVisible(true);

    }

    private void inputBilldetail() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException, DocumentException {
        controllerSQL.OrderController.inputBillDetailsSQL(listOrder, idBillOrder.getText());
        // in bill file PDF
        inBillPayment();
        ListSELL = controllerSQL.SellController.loadSELL();
        tableMenuSell.setItems(ListSELL);
        tableMenuSell.setVisible(true);
        Listmenu = controllerSQL.MenuCotroller.loadMenu();
        tableMenuFood.setItems(Listmenu);
        tableMenuFood.setVisible(true);
        listOrder.clear();
        listOrder_lie.clear();
        tableOrder.setItems(listOrder);

    }

    //xu ly Sale Ingre
    private void setTableIngreSale() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        tableIngredientsSale.getColumns().addAll(nameIngredientsSale, countIngredientsSale, priceIngredientsSale);
        nameIngredientsSale.setCellValueFactory(new PropertyValueFactory<>("nameIngre"));
        countIngredientsSale.setCellValueFactory(new PropertyValueFactory<>("countInput"));
        priceIngredientsSale.setCellValueFactory(new PropertyValueFactory<>("priceIngre"));
        nameIngredientsSale.setPrefWidth(0.15 * tableAccount.getPrefWidth());
        countIngredientsSale.setPrefWidth(0.15 * tableAccount.getPrefWidth());
        priceIngredientsSale.setPrefWidth(0.15 * tableAccount.getPrefWidth());
        listIngredientsSale = controllerSQL.IngredientsController.loadDataIngre();
        tableIngredientsSale.setItems(listIngredientsSale);
        tableIngredientsSale.setVisible(true);
    }

    private void chooseDateShowIngre() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        java.util.Date date1 = java.util.Date.from(fromDaySale.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlFromDATE = new java.sql.Date(date1.getTime());
        java.util.Date date2 = java.util.Date.from(toDaySale.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlToDATE = new java.sql.Date(date2.getTime());
        try {
            listIngredientsSale.clear();
//            String sql = "select * from RawMaterials where DateInput BETWEEN CAST("+sqlFromDATE.toString()+" AS DATE) and CAST("+sqlToDATE.toString()+" AS DATE)";
            String sql = "select * from RawMaterials where DateInput BETWEEN CAST('" + sqlFromDATE.toString() + "' AS DATE) and CAST('" + sqlToDATE.toString() + "' AS DATE)";
            java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                Ingredients igd = new Ingredients(rs.getString("idIngredients"), rs.getString("name"), rs.getFloat("price"), rs.getDate("DateInput"), rs.getInt("countInput"));
                listIngredientsSale.add(igd);
            }
            tableIngredientsSale.setItems(listIngredientsSale);

        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sqlFromDATE.toString());
    }

    private void totalPriceIngreSale() {
        total = 0;
        for (int i = 0; i < listIngredientsSale.size(); i++) {
            total = total + listIngredientsSale.get(i).getPriceIngre();
        }
        textPriceIngredientsSale.setText(String.valueOf(total));
    }

    //xu ly BillAll sale
    private void setColumnTableBillSale() throws SQLException, ClassNotFoundException, IOException, FileNotFoundException, JAXBException {
        tableBillAllSale.getColumns().addAll(idBillAllSaleColumn, idEmpBillAllSaleColumn, timeBillAllSaleColumn, totalBillAllSaleColumn);
        idBillAllSaleColumn.setCellValueFactory(new PropertyValueFactory<>("idBill"));
        idEmpBillAllSaleColumn.setCellValueFactory(new PropertyValueFactory<>("idEmpOrder"));
        timeBillAllSaleColumn.setCellValueFactory(new PropertyValueFactory<>("timeOrder"));
        totalBillAllSaleColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        idBillAllSaleColumn.setPrefWidth(0.25 * tableBillAllSale.getPrefWidth());
        idEmpBillAllSaleColumn.setPrefWidth(0.25 * tableBillAllSale.getPrefWidth());
        timeBillAllSaleColumn.setPrefWidth(0.25 * tableBillAllSale.getPrefWidth());
        totalBillAllSaleColumn.setPrefWidth(0.25 * tableBillAllSale.getPrefWidth());
        listBillAllSale = controllerSQL.BillAllController.loadDataBillAllSale();
        tableBillAllSale.setItems(listBillAllSale);
        tableBillAllSale.setVisible(true);
    }

    private void chooseDateShowBillAll() throws ClassNotFoundException, SQLException, IOException, FileNotFoundException, JAXBException {
        java.util.Date date1 = java.util.Date.from(fromDaySale.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlFromDATE = new java.sql.Date(date1.getTime());
        java.util.Date date2 = java.util.Date.from(toDaySale.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlToDATE = new java.sql.Date(date2.getTime());

        try {
            listBillAllSale.clear();
            String sql = "select * from BillAll where DateCheckin BETWEEN CAST('" + sqlFromDATE.toString() + "' AS DATE) and CAST('" + sqlToDATE.toString() + "' AS DATE)";
            java.sql.Connection conn = ControllerDB.connectDB.connectSQL();
            PreparedStatement pm = conn.prepareStatement(sql);
            ResultSet rs = pm.executeQuery();
            while (rs.next()) {
                BillAll billAll = new BillAll(rs.getString("idBill"), rs.getString("idEmploy_Bill"), rs.getString("DateCheckin"), rs.getFloat("totalBill"));
                listBillAllSale.add(billAll);
            }
            tableBillAllSale.setItems(listBillAllSale);

        } catch (ClassNotFoundException | SQLException | IOException | JAXBException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void totalPriceBillAllSale() {
        total = 0;
        for (int i = 0; i < listBillAllSale.size(); i++) {
            total = total + listBillAllSale.get(i).getTotal();
        }
        textPriceBillAllSale.setText(String.valueOf(total));
    }

    private void showpane() {
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("/FXML/FXML_Home_PAGE1.fxml"));

        } catch (IOException ex) {
            Logger.getLogger(FXMLHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);
        // Stage primaryStage = new Stage();
        FXMLController_Lognin.Homestage.setResizable(false);
        FXMLController_Lognin.Homestage.setScene(scene);
        FXMLController_Lognin.Homestage.show();
    }

    private void inBillPayment() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        // khởi tạo Document vao file new PDF 
        PdfWriter.getInstance(document, new FileOutputStream("D:/Bill.pdf"));
        document.open(); // Mở file để viết
        Paragraph paragraph1 = new Paragraph("BILL COFFEE");
        // Text Center set vi tri
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        paragraph1.setSpacingAfter(15);
        Paragraph paragraph2 = new Paragraph("ID BILL         :     " + idBillOrder.getText());
        paragraph2.setIndentationLeft(80);
        Paragraph paragraph3 = new Paragraph("ID EMP          :     " + idEMPSELL.getText());
        paragraph3.setIndentationLeft(80);
        Paragraph paragraph4 = new Paragraph("TIME ORDER   :     " + labelTimeOrder.getText());
        paragraph4.setIndentationLeft(80);
        paragraph4.setSpacingAfter(15);
        document.add(paragraph1);
        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph4);
        PdfPTable bill = new PdfPTable(3);
        bill.setSpacingBefore(25);
        bill.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(new Phrase("NAME"));
        bill.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("COUNT"));
        bill.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("PRICE"));
        bill.addCell(c3);
        for (int i = 0; i < listOrder.size(); i++) {
            bill.addCell(listOrder.get(i).getNameMenuFood());
            bill.addCell(String.valueOf(listOrder.get(i).getCountOrder()));
            bill.addCell(String.valueOf(listOrder.get(i).getPriceOrder()));
        }
        document.add(bill);
        Paragraph paragraph5 = new Paragraph("TOTAL :         " + labelTotalBill.getText() + "$");
        paragraph5.setSpacingBefore(15);
        paragraph5.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph5);
        document.close();

    }

}

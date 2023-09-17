package controller;

import helper.CollectionLists;
import helper.CustomersQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This controller class is for the ModifyCustomer screen.
 */

public class ModifyCustomer implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    Stage stage;
    Parent scene;

    /**
     * This singular value creates a dummy object that fields will be brought into when the form is initialized.
     */

    Customers modifiedCustomer = null;

    @FXML
    private ComboBox<Countries> CountryComboBox;

    @FXML
    private ComboBox<Divisions> DivisionComboBox;

    @FXML
    private Label errMsgLbl;

    @FXML
    private TextField modAddTxt;

    @FXML
    private TextField modIDTxt;

    @FXML
    private TextField modNameTxt;

    @FXML
    private TextField modPhoneTxt;

    @FXML
    private TextField modPostalTxt;

    /**
     * This method filters the Division Combo Box by what is selected in the Country Combo Box.
     *
     * @param event Division Combo Box is filtered.
     */

    @FXML
    void selectCountry(ActionEvent event) {

        int countryID = CountryComboBox.getValue().getCountID();
        DivisionComboBox.setItems(CollectionLists.getFilteredListD(countryID).sorted());
        DivisionComboBox.getSelectionModel().selectFirst();
        DivisionComboBox.setVisibleRowCount(8);

    }

    /**
     * This method cancels the screen and returns the user to Landing screen.
     *
     * @param event Return to Landing Screen
     * @throws IOException
     */

    @FXML
    void onActionCancel(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Landing.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method modifies the Customer in the database. It will first check if a valid value is in all fields. Then it
     * will modify the customer in the database.
     *
     * @param event Modify the customer in the database.
     * @throws SQLException
     * @throws IOException
     */

    @FXML
    void onActionModCus(ActionEvent event) throws SQLException, IOException {

        try {

            int cusID = modifiedCustomer.getCusID();
            String cusName = modNameTxt.getText();
            String cusAdd = modAddTxt.getText();
            String cusPos = modPostalTxt.getText();
            String cusPhone = modPhoneTxt.getText();
            int cusDivID = DivisionComboBox.getValue().getDivID();
            String cusDiv = modifiedCustomer.getCusDiv();

            if (cusName.isEmpty() || cusAdd.isEmpty() || cusPos.isEmpty() || cusPhone.isEmpty()) {
                errMsgLbl.setText("You must enter a valid value in all fields!");
                return;
            }

            modifiedCustomer.setCusID(cusID);
            modifiedCustomer.setCusName(cusName);
            modifiedCustomer.setCusAdd(cusAdd);
            modifiedCustomer.setCusPos(cusPos);
            modifiedCustomer.setCusPhone(cusPhone);
            modifiedCustomer.setCusDivID(cusDivID);
            modifiedCustomer.setCusDiv(cusDiv);

            CustomersQuery.cusUpdate(cusID, cusName, cusAdd, cusPos, cusPhone, cusDivID);
            CollectionLists.deleteCustomer(modifiedCustomer);
            CustomersQuery.cusUpdated(cusID);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Landing.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        }

        catch(NullPointerException e) {
            errMsgLbl.setText("Please enter a valid value in all fields!");
        }

    }

    /**
     * This method sends the customer from the Landing screen to Modify Customer screen. All the values sent over
     * are used to either populate fields or set the value to any combo box currently on the screen.
     *
     * @param customer The appointment sent from Landing to Modify Customer screen.
     */

    public void sendCustomer(Customers customer) {

            modifiedCustomer = customer;
            modIDTxt.setText(String.valueOf(customer.getCusID()));
            modNameTxt.setText(customer.getCusName());
            modAddTxt.setText(customer.getCusAdd());
            modPostalTxt.setText(customer.getCusPos());
            modPhoneTxt.setText(customer.getCusPhone());

            int d = customer.getCusDivID();
            DivisionComboBox.setItems(CollectionLists.getDivision(d).sorted());
            DivisionComboBox.getSelectionModel().selectFirst();

            int c = DivisionComboBox.getValue().getCountID();
            CountryComboBox.setItems(CollectionLists.getCountry(c));
            CountryComboBox.getSelectionModel().selectFirst();
            DivisionComboBox.setItems(CollectionLists.getFilteredListD(c).sorted());
            CountryComboBox.setItems(CollectionLists.getAllCountries());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

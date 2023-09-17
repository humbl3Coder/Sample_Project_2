package controller;

import helper.CollectionLists;
import helper.CustomersQuery;
import interfaces.Filtered;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Divisions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This controller class is for the AddCustomer screen.
 */

public class AddCustomer implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<Countries> CountryComboBox;

    @FXML
    private ComboBox<Divisions> DivisionComboBox;

    @FXML
    private TextField customerAddTxt;

    @FXML
    private TextField customerIDTxt;

    @FXML
    private TextField customerNameTxt;

    @FXML
    private TextField customerPhoneTxt;

    @FXML
    private TextField customerPostTxt;

    @FXML
    private Label errorMsgLbl;

    /**
     * This method uses a country ID selected from the Country Combo Box and filters the Division Combo Box to show
     * divisions that are inside this country.
     *
     * Lambda #1 used to filter a list down. This moves the  implementation from the CollectionLists to when the
     * country is selected in the combo box. This may increase speed as the implementation is actually at the event
     * instead of having to access another class and have the implementation run there. This improves locality of the
     * code as well as speed.
     *
     * @param event Filter Division Combo Box by selection in Country Combo Box.
     */

    @FXML
    void selectCountry(ActionEvent event) {

        final int countryID = CountryComboBox.getValue().getCountID();

        Filtered division = id -> {
            ObservableList<Divisions> filteredDivisions = FXCollections.observableArrayList();

            for (Divisions d : CollectionLists.allDivisions) {
                if (d.getCountID() == countryID) {
                    filteredDivisions.add(d);
                }
            }
            return filteredDivisions;
        };

        //DivisionComboBox.setItems(CollectionLists.getFilteredListD(countryID).sorted());
        DivisionComboBox.setItems(division.filter(countryID).sorted());
        DivisionComboBox.getSelectionModel().selectFirst();
        DivisionComboBox.setVisibleRowCount(8);

    }

    /**
     * This method will add a customer to the database. It will first check for valid values in fields before it
     * progresses to adding a customer to the database.
     *
     * @param event Adds the customer to the database.
     *
     * @throws SQLException
     * @throws IOException
     */

    @FXML
    void onActionAddCus(ActionEvent event) throws SQLException, IOException {

        try {

            int id = 0;
            String name = customerNameTxt.getText();
            String address = customerAddTxt.getText();
            String postal = customerPostTxt.getText();
            String phone =  customerPhoneTxt.getText();
            int div = DivisionComboBox.getValue().getDivID();

            if (name.isEmpty() || address.isEmpty() || postal.isEmpty() || phone.isEmpty()) {
                errorMsgLbl.setText("You must enter a valid value in all fields!");
                return;
            }

            CustomersQuery.cusInsert(id, name, address, postal, phone, div);
            CustomersQuery.cusAdded();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Landing.fxml"));
            loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        }

        catch (NullPointerException e) {
            errorMsgLbl.setText("Please enter a valid value in each field!");
        }

    }

    /**
     * The button will cancel and return the program to the Landing screen.
     *
     * @param event Return to Landing screen.
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
     * This method will initialize by setting the items in the Country Combo Box and the Division Combo Box.
     *
     * @param url
     * @param resourceBundle
     */

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        CountryComboBox.setItems(CollectionLists.getAllCountries());
        CountryComboBox.getSelectionModel().selectFirst();

        DivisionComboBox.setItems(CollectionLists.getAllDivisions());
        DivisionComboBox.setItems(CollectionLists.getFilteredListD(1).sorted());
        DivisionComboBox.getSelectionModel().selectFirst();
        DivisionComboBox.setVisibleRowCount(8);

    }
}

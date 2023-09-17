package controller;

import helper.CollectionLists;
import interfaces.Total;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This controller class is for the Reports screen.
 */

public class Reports implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<Contacts> conComboBox;

    @FXML
    private TableView<Appointments> conTableView;

    @FXML
    private ComboBox<Months> monthComboBox;

    @FXML
    private TableColumn<Appointments, Integer> schCusCol;

    @FXML
    private TableColumn<Appointments, Integer> schAppCol;

    @FXML
    private TableColumn<Appointments, String> schDescCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> schEndCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> schStartCol;

    @FXML
    private TableColumn<Appointments, String> schTitleCol;

    @FXML
    private TableColumn<Appointments, String> schTypeCol;

    @FXML
    private Label totAppLbl;

    @FXML
    private ComboBox<DistinctTypes> typeComboBox;

    @FXML
    private TableColumn<Appointments, Integer> useAppCol;

    @FXML
    private ComboBox<Users> useComboBox;

    @FXML
    private TableColumn<Appointments, Integer> useCusCol;

    @FXML
    private TableColumn<Appointments, String> useDescCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> useEndCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> useStartCol;

    @FXML
    private TableView<Appointments> useTableView;

    @FXML
    private TableColumn<Appointments, String> useTitleCol;

    @FXML
    private TableColumn<Appointments, String> useTypeCol;

    /**
     * This method sends the user back to the Landing Screen after clearing the Distinct Types list.
     *
     * @param event Sends user to landing screen.
     * @throws IOException
     */

    @FXML
    void onActionRepCancel(ActionEvent event) throws IOException {

        CollectionLists.allDistinctTypes.clear();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Landing.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method runs a report that totals the different types of reports each month and displays the total.
     *
     * Lambda #2 used to count up the different types of Appointments and then display the total. Using this lambda
     * will keep the implementation at the event of hitting the button, instead of having to call another class and do
     * the implementation there, which improves locality of the code. This will also hopefully increase speed.
     *
     * @param event Takes the month and appointment type and counts them up for that specific month, then displays the
     *             total.
     */

    @FXML
    void OnActionTotalAppsBtn(ActionEvent event) {

        int sMonth = monthComboBox.getValue().getMonthNum();
        String sType = typeComboBox.getValue().getAppType();
        int sTotal = 0;

        Total types = (b, c, d) -> {
            for (Appointments a : CollectionLists.getAllAppointments()) {
                if ((a.getStart().getMonth().getValue() == sMonth) && (a.getType().equals(sType))) {
                    d = d + 1;
                }
            }
            return d;
        };

        totAppLbl.setText((String.valueOf(types.sumType(sMonth, sType, sTotal))));

    }

    /**
     * This method runs the report that shows all of the appointments for the selected Contact.
     *
     * @param event Shows contact's appointments.
     */

    @FXML
    void onActionSelect(ActionEvent event) {

        int conID = conComboBox.getSelectionModel().getSelectedItem().getConID();
        conTableView.setItems(CollectionLists.getConApp(conID));

    }

    /**
     * This method runs the report that shows all the appointments for the selected user.
     *
     * @param event Shows user's appointments.
     */

    @FXML
    void onActionSelectUse(ActionEvent event) {

        int useID = useComboBox.getSelectionModel().getSelectedItem().getUseID();
        useTableView.setItems(CollectionLists.getUseApp(useID));

    }

    /**
     * This method initializes the values for all the combo boxes present and sets up the tables.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        monthComboBox.setItems(CollectionLists.getAllMonths());
        monthComboBox.getSelectionModel().selectFirst();
        monthComboBox.setVisibleRowCount(2);

        typeComboBox.setItems(CollectionLists.getAllDistinctTypes());
        typeComboBox.getSelectionModel().selectFirst();
        typeComboBox.setVisibleRowCount(2);

        conComboBox.setItems(CollectionLists.getAllContacts());
        conComboBox.getSelectionModel().selectFirst();

        schAppCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        schTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        schDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        schTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        schStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        schEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        schCusCol.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        conTableView.getSortOrder().add(schStartCol);

        useComboBox.setItems(CollectionLists.getAllUsers());
        useComboBox.getSelectionModel().selectFirst();
        useComboBox.setVisibleRowCount(2);

        useAppCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        useTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        useDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        useTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        useStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        useEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        useCusCol.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        useTableView.getSortOrder().add(useStartCol);

    }
}

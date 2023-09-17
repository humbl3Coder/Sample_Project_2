package controller;

import helper.AppointmentsQuery;
import helper.CollectionLists;
import helper.CustomersQuery;
import helper.ScheduleTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * This controller class is for the Landing screen.
 */

public class Landing implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Appointments> AppTableView;

    @FXML
    private TableView<Customers> CusTableView;

    @FXML
    private TableColumn<Appointments, Integer> appCIDCol;

    @FXML
    private TableColumn<Appointments, String> appConCol;

    @FXML
    private TableColumn<Appointments, String> appDescCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appEndCol;

    @FXML
    private TableColumn<Appointments, Integer> appIDCol;

    @FXML
    private TableColumn<Appointments, String> appLocCol;

    @FXML
    private TableColumn<Appointments, LocalDateTime> appStartCol;

    @FXML
    private TableColumn<Appointments, String> appTitleCol;

    @FXML
    private TableColumn<Appointments, String> appTypeCol;

    @FXML
    private TableColumn<Appointments, Integer> appUIDCol;

    @FXML
    private TableColumn<Customers, String> cusAddCol;

    @FXML
    private TableColumn<Customers, Integer> cusCIDCol;

    @FXML
    private TableColumn<Customers, String> cusCusNameCol;

    @FXML
    private TableColumn<Customers, Integer> cusDivCol;

    @FXML
    private TableColumn<Customers, String> cusPhoneCol;

    @FXML
    private TableColumn<Customers, String> cusPostCol;

    @FXML
    private Label errMessageLbl;

    @FXML
    private Label appMessageLbl;

    @FXML
    private RadioButton monthRBtn;

    @FXML
    private RadioButton weekRBtn;

    @FXML
    private RadioButton allRBtn;

    /**
     * This button, when pressed will log out the user and return to the Login screen.
     *
     * @param event Return to Login screen.
     * @throws IOException
     */

    @FXML
    void onActionLogOut(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This button, when pressed will send the user to the Add Appointment screen.
     *
     * @param event Go to Add Appointment screen.
     * @throws IOException
     */

    @FXML
    void onActionAddAppForm(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This button, when pressed will send the user to the Add Customer screen.
     *
     * @param event Go to Add Customer screen.
     * @throws IOException
     */

    @FXML
    void onActionAddCusForm(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * This method will delete an appointment from the database. If no appointment has been selected, a rejection
     * message will appear on the screen. When an appointment is deleted, a message will appear on the screen and the
     * appointment will be deleted from the database.
     *
     * @param event Delete appointment from database.
     * @throws IOException
     */

    @FXML
    void onActionDelApp(ActionEvent event) throws SQLException {

        try {

            String delType = AppTableView.getSelectionModel().getSelectedItem().getType();
            int delAppID = AppTableView.getSelectionModel().getSelectedItem().getAppID();

            AppointmentsQuery.appDelete(AppTableView.getSelectionModel().getSelectedItem().getAppID());
            CollectionLists.deleteAppointment(AppTableView.getSelectionModel().getSelectedItem());
            appMessageLbl.setText("Appointment # " + delAppID + "  (" + delType + ") has been cancelled." );

            /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ok to Delete?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                AppointmentsQuery.appDelete(AppTableView.getSelectionModel().getSelectedItem().getAppID());
                CollectionLists.deleteAppointment(AppTableView.getSelectionModel().getSelectedItem());
                appMessageLbl.setText("Appointment # " + delAppID + "  (" + delType + ") has been cancelled." );

            }*/
        }

        catch (NullPointerException e) {
            errMessageLbl.setText(("Please select an Appointment to delete."));
        }
    }

    /**
     * This method will delete a customer from the database. It will first check to see if the customer has any active
     * appointments. If so, an error message will be displayed on the screen. If the customer does not have an active
     * appointment, the customer will be deleted from the database.
     *
     * @param event Delete a customer from the database.
     * @throws SQLException
     */

    @FXML
    void onActionDelCus(ActionEvent event) throws SQLException{

        try {
            int delCusID = CusTableView.getSelectionModel().getSelectedItem().getCusID();

            boolean match = false;

            for(Appointments a : CollectionLists.allAppointments ) {

                if (a.getCusID() == delCusID) {
                    match = true;
                    break;
                }
            }

            if (match) {
                errMessageLbl.setText("Cannot delete. Customer has active appointment(s).");
            }

            else {

                CustomersQuery.cusDelete(CusTableView.getSelectionModel().getSelectedItem().getCusID());
                CollectionLists.deleteCustomer(CusTableView.getSelectionModel().getSelectedItem());
                errMessageLbl.setText("Customer has been deleted.");

                /*Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Ok to Delete?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    CustomersQuery.cusDelete(CusTableView.getSelectionModel().getSelectedItem().getCusID());
                    CollectionLists.deleteCustomer(CusTableView.getSelectionModel().getSelectedItem());
                    errMessageLbl.setText("Customer has been deleted.");
                }*/
            }
        }

        catch (NullPointerException e) {
            errMessageLbl.setText(("Please select a Customer to delete."));
        }
    }

    /**
     * This method will close the connection to the database and exit the program.
     *
     * @param event Exit the program.
     */

    @FXML
    void onActionExitApp(ActionEvent event) {
        ScheduleTable.closeConnection();
        System.exit(0);
    }

    /**
     * This method will send a selected appointment to Modify Appointment screen. If no appointment is selected, an
     * error message will appear on the screen.
     *
     * @param event Send selected appointment to Modify Appointment screen.
     * @throws IOException
     */

    @FXML
    void onActionModAppForm(ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyAppointment.fxml"));
            loader.load();

            ModifyAppointment MAController = loader.getController();
            MAController.sendAppointment(AppTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        catch (NullPointerException e) {
            errMessageLbl.setText("Please select an Appointment to modify.");
        }
    }

    /**
     * This methoid will send a selected customer to the Modify Customer screen. If no customer is selected, an
     * error message will appear on the screen.
     *
     * @param event Send selected customer to Modify Customer screen.
     * @throws IOException
     */

    @FXML
    void onActionModCusForm(ActionEvent event) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyCustomer.fxml"));
            loader.load();

            ModifyCustomer MCController = loader.getController();
            MCController.sendCustomer(CusTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        catch (NullPointerException e) {
            errMessageLbl.setText("Please select a Customer to modify.");
        }

    }

    /**
     * This button, when pressed will send the user to the Reports screen.
     *
     * @param event Go to Reports screen.
     * @throws IOException
     */

    @FXML
    void onActionReportsForm(ActionEvent event) throws IOException, SQLException {

        AppointmentsQuery.appTypes();

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * For this method, if the all Appointments button is selected, all Appointments will appear in the table.
     *
     * @param event Select the all button on the screen to show all Appointments.
     */

    @FXML
    void onAll(ActionEvent event) {

        if (allRBtn.isSelected()) {
            AppTableView.setItems(CollectionLists.getAllAppointments());
        }
    }

    /**
     * For this method, if the Current Week Appointments button is selected, all Appointments that are in the current
     * week will appear in the table.
     *
     * @param event Select the Current Week button on the screen to show current week Appointments.
     */

    @FXML
    void onMonthly(ActionEvent event) {

        if (monthRBtn.isSelected()) {
            AppTableView.setItems(CollectionLists.getMonthApp());
        }
    }

    /**
     * For this method, if the Current Month Appointments button is selected, all Appointments that are in the current
     * month will appear in the table.
     *
     * @param event Select the Current Month button on the screen to show current month Appointments.
     */

    @FXML
    void onWeekly(ActionEvent event) {

        if (weekRBtn.isSelected()) {
            AppTableView.setItems(CollectionLists.getWeekApp());
        }
    }

    /**
     * This method will intialize both the Customer Table View and the Appointments Table View and search for an
     * appointment about to start within 15 minutes. If appointment is found, an alert box will state on the gui, if not
     * an alert box will state that on the gui also.
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Appointments appointment = AppointmentsQuery.appAlert();
            if (appointment == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Appointment Status");
                    alert.setHeaderText(null);
                    alert.setContentText("No upcoming appointment!");
                    alert.showAndWait();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Status");
                alert.setHeaderText(null);
                alert.setContentText("Upcoming Appointment: " + appointment.getAppID() + "\n" +
                                     "Appointment Start: " + appointment.getStart().toLocalDate() + " " + appointment.getStart().toLocalTime() + "\n" +
                                     "Appointment End: " + appointment.getEnd().toLocalDate() + " " + appointment.getEnd().toLocalTime());
                alert.showAndWait();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        CusTableView.setItems(CollectionLists.getAllCustomers());
        cusCIDCol.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        cusCusNameCol.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        cusAddCol.setCellValueFactory(new PropertyValueFactory<>("cusAdd"));
        cusPostCol.setCellValueFactory(new PropertyValueFactory<>("cusPos"));
        cusPhoneCol.setCellValueFactory(new PropertyValueFactory<>("cusPhone"));
        cusDivCol.setCellValueFactory(new PropertyValueFactory<>("cusDiv"));
        CusTableView.getSortOrder().add(cusCIDCol);

        AppTableView.setItems(CollectionLists.getAllAppointments());
        appIDCol.setCellValueFactory(new PropertyValueFactory<>("appID"));
        appTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appDescCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        appLocCol.setCellValueFactory(new PropertyValueFactory<>("loc"));
        appConCol.setCellValueFactory(new PropertyValueFactory<>("conName"));
        appTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        appEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        appCIDCol.setCellValueFactory(new PropertyValueFactory<>("cusID"));
        appUIDCol.setCellValueFactory(new PropertyValueFactory<>("useID"));
        AppTableView.getSortOrder().add(appIDCol);

        //allRBtn.fire();

    }
}

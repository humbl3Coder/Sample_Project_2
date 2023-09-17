package controller;

import helper.AppointmentsQuery;
import helper.CollectionLists;
import helper.TimeConversions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This controller class is for the AddAppointment screen.
 */

public class AddAppointment implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    Stage stage;
    Parent scene;

    @FXML
    private ComboBox<Contacts> ContactComboBox;

    @FXML
    private ComboBox<Customers> cusComboBox;

    @FXML
    private ComboBox<Users> useComboBox;

    @FXML
    private TextField appDescTxt;

    @FXML
    private TextField appLocTxt;

    @FXML
    private TextField appTitleTxt;

    @FXML
    private TextField appTypeTxt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<EndHours> endAppComboBox;

    @FXML
    private ComboBox<StartHours> startAppComboBox;

    @FXML
    private Label errMessageLbl;

    @FXML
    private Label excMessageLbl;

    /**
     * This method adds the Appointment to the database. It will first check if a valid value is in all fields. Then it
     * will check for overlaps and if the appointment falls within Business Hours. If so, the appointment will be added
     * to the database.
     *
     * @param event Adds the appointment to the database.
     * @throws SQLException
     * @throws IOException
     */

    @FXML
    void onActionAddApp(ActionEvent event) throws SQLException, IOException {

        try{

            LocalDateTime open = TimeConversions.toEstLDT(LocalDateTime.of(datePicker.getValue(), LocalTime.of(8,0)));
            LocalDateTime close = TimeConversions.toEstLDT(LocalDateTime.of(datePicker.getValue(), LocalTime.of(22,0)));
            LocalDateTime startM = TimeConversions.toEstLDT(LocalDateTime.of(datePicker.getValue(), startAppComboBox.getValue().getStartLT()));
            LocalDateTime endM = TimeConversions.toEstLDT(LocalDateTime.of(datePicker.getValue(), endAppComboBox.getValue().getEndLT()));

            int id = 0;
            String title = appTitleTxt.getText();
            String desc = appDescTxt.getText();
            String loc = appLocTxt.getText();
            String type = appTypeTxt.getText();
            LocalDateTime start = LocalDateTime.of(datePicker.getValue(), startAppComboBox.getValue().getStartLT());
            LocalDateTime end = LocalDateTime.of(datePicker.getValue(), endAppComboBox.getValue().getEndLT());
            int cusID = cusComboBox.getValue().getCusID();
            int useID = useComboBox.getValue().getUseID();
            int conID = ContactComboBox.getValue().getConID();

            if (title.isEmpty() || desc.isEmpty() || loc.isEmpty() || type.isEmpty()) {
                errMessageLbl.setText("You must enter a valid value in all fields!");
                return;
            }

            for (Appointments a : CollectionLists.getAllAppointments()) {

                //if (a.getCusID() != cusID) {
                //    continue;
                //}

                //A start >= start && A start < end
                if ((a.getStart().isAfter(start) || a.getStart().isEqual(start)) && (a.getStart().isBefore(end))) {
                    errMessageLbl.setText("Appointment overlaps with another appointment!");
                    return;
                }
                //A end > Start && A End <= end
                if ((a.getEnd().isAfter(start)) && (a.getEnd().isBefore(end) || a.getEnd().isEqual(end)) ) {
                    errMessageLbl.setText("Appointment overlaps with another appointment!");
                    return;
                }
                //A start <= Start && A End >= End
                if ((a.getStart().isBefore(start) || (a.getStart().isEqual(start))) && (a.getEnd().isAfter(end) || a.getEnd().isEqual(end))) {
                    errMessageLbl.setText("Appointment overlaps with another appointment!");
                    return;
                }
            }

            if (startM.isBefore(open) || endM.isAfter(close) || endM.isBefore(startM)) {
                errMessageLbl.setText("Start or End of Appointment is not within Business Hours!");
            }

            else if ((startM.isAfter(start) || startM.isEqual(start)) && (endM.isBefore(end) || endM.isEqual(end))) {

                AppointmentsQuery.appInsert(id, title, desc, loc, type, start, end, cusID, useID, conID);
                AppointmentsQuery.appAdded();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/Landing.fxml"));
                loader.load();

                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Parent scene = loader.getRoot();
                stage.setScene(new Scene(scene));
                stage.show();

            }

        }

        catch(NullPointerException e) {
            errMessageLbl.setText("Please enter a valid value in all fields!");
        }
    }

    /**
     * This button, when pressed will return the program to the main Landing screen.
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
     * This method initializes the date in the datePicker and sets the Items in the following combo boxes: Contact Combo
     * Box, Start App Combo Box, End App Combo Box, Cus Combo Box, and the Use Combo Box.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datePicker.setValue(LocalDate.now());

        ContactComboBox.setItems(CollectionLists.getAllContacts());
        ContactComboBox.getSelectionModel().selectFirst();
        ContactComboBox.setVisibleRowCount(4);

        startAppComboBox.setItems(CollectionLists.getAllStartHours());
        startAppComboBox.getSelectionModel().selectFirst();
        startAppComboBox.setVisibleRowCount(6);

        endAppComboBox.setItems(CollectionLists.getAllEndHours());
        endAppComboBox.getSelectionModel().selectFirst();
        endAppComboBox.setVisibleRowCount(6);

        cusComboBox.setItems(CollectionLists.getAllCustomers());
        cusComboBox.setVisibleRowCount(3);

        useComboBox.setItems(CollectionLists.getAllUsers());
        useComboBox.setVisibleRowCount(3);

    }
}

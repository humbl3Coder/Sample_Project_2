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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

/**
 * This controller class is for the ModifyAppointment screen.
 */

public class ModifyAppointment implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    Stage stage;
    Parent scene;

    /**
     * This singular value creates a dummy object that fields will be brought into when the form is initialized.
     */

    Appointments modifiedAppointment = null;

    @FXML
    private Label errMessageLbl;

    @FXML
    private Label excMessageLbl;

    @FXML
    private TextField modAppDescTxt;

    @FXML
    private TextField modAppIDTxt;

    @FXML
    private TextField modAppLocTxt;

    @FXML
    private TextField modAppTitleTxt;

    @FXML
    private TextField modAppTypeTxt;

    @FXML
    private ComboBox<Contacts> modContactComboBox;

    @FXML
    private ComboBox<Customers> modCusComboBox;

    @FXML
    private ComboBox<Users> modUseComboBox;

    @FXML
    private DatePicker modDatePicker;

    @FXML
    private ComboBox<EndHours> modEndAppComboBox;

    @FXML
    private ComboBox<StartHours> modStartAppComboBox;

    /**
     * This method modifies the Appointment in the database. It will first check if a valid value is in all fields. Then it
     * will check for overlaps and if the appointment falls within Business Hours. If so, the appointment will be
     * modified in the database.
     *
     * @param event Appointment is modified.
     * @throws SQLException
     * @throws IOException
     */


    @FXML
    void onActionModApp(ActionEvent event) throws SQLException, IOException {

        try {
            LocalDateTime open = TimeConversions.toEstLDT(LocalDateTime.of(modDatePicker.getValue(), LocalTime.of(8,0)));
            LocalDateTime close = TimeConversions.toEstLDT(LocalDateTime.of(modDatePicker.getValue(), LocalTime.of(22,0)));
            LocalDateTime startM = TimeConversions.toEstLDT(LocalDateTime.of(modDatePicker.getValue(), modStartAppComboBox.getValue().getStartLT()));
            LocalDateTime endM = TimeConversions.toEstLDT(LocalDateTime.of(modDatePicker.getValue(), modEndAppComboBox.getValue().getEndLT()));

            int appID = modifiedAppointment.getAppID();
            String title = modAppTitleTxt.getText();
            String desc = modAppDescTxt.getText();
            String loc = modAppLocTxt.getText();
            String type = modAppTypeTxt.getText();
            LocalDateTime start = LocalDateTime.of(modDatePicker.getValue(), modStartAppComboBox.getValue().getStartLT());
            LocalDateTime end = LocalDateTime.of(modDatePicker.getValue(), modEndAppComboBox.getValue().getEndLT());
            int cusID = modCusComboBox.getValue().getCusID();
            int useID = modUseComboBox.getValue().getUseID();
            int conID = modContactComboBox.getValue().getConID();

            if (title.isEmpty() || desc.isEmpty() || loc.isEmpty() || type.isEmpty()) {
                errMessageLbl.setText("You must enter a valid value in all fields!");
                return;
            }

            for (Appointments a : CollectionLists.getAllAppointments()) {

                //if (a.getCusID() != cusID) {
                //    continue;
                //}

                if (a.getAppID() == appID) {
                    continue;
                }

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

            if (startM.isBefore(open) || endM.isAfter(close)) {
                errMessageLbl.setText("Start or End of Appointment is not within Business Hours!");
            }

            //if ((startM.isEqual(modifiedAppointment.getStart())) && (endM.isEqual(modifiedAppointment.getEnd()))) {
            //    errMessageLbl.setText("No change made to times of appointment!");
            //}

            else if ((startM.isAfter(start) || startM.isEqual(start)) && (endM.isBefore(end) || endM.isEqual(end))) {
                AppointmentsQuery.appUpdate(appID, title, desc, loc, type, start, end, cusID, useID, conID);
                CollectionLists.deleteAppointment(modifiedAppointment);
                AppointmentsQuery.appUpdated(appID);

                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/Landing.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }

        catch (NullPointerException e) {
            errMessageLbl.setText("Please enter a valid value for each field!");
        }

    }

    /**
     * This button cancels the current screen and returns the user to the Landing screen.
     *
     * @param event Returns user to Landing screen.
     * @throws IOException
     */

    @FXML
    void onModActionCancel(ActionEvent event) throws IOException {

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Landing.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * This method sends the appointment from the Landing screen to Modify Appointment screen. All the values sent over
     * are used to either populate fields or set the value to any combo box or datePicker currently on the screen.
     *
     * @param appointment The appointment sent from Landing to Modify Appointment screen.
     */

    public void sendAppointment (Appointments appointment) {

        modifiedAppointment =  appointment;
        modAppIDTxt.setText(String.valueOf(appointment.getAppID()));
        modAppTitleTxt.setText(appointment.getTitle());
        modAppDescTxt.setText(appointment.getDesc());
        modAppLocTxt.setText(appointment.getLoc());
        modAppTypeTxt.setText(appointment.getType());

        int cu = appointment.getCusID();
        modCusComboBox.setItems(CollectionLists.getCustomer(cu).sorted());
        modCusComboBox.getSelectionModel().selectFirst();
        modCusComboBox.setItems(CollectionLists.getAllCustomers());

        int u = appointment.getUseID();
        modUseComboBox.setItems(CollectionLists.getUser(u).sorted());
        modUseComboBox.getSelectionModel().selectFirst();
        modUseComboBox.setItems(CollectionLists.getAllUsers());

        int c = appointment.getConID();
        modContactComboBox.setItems(CollectionLists.getContact(c).sorted());
        modContactComboBox.getSelectionModel().selectFirst();
        modContactComboBox.setItems(CollectionLists.getAllContacts());

        LocalTime s = appointment.getStart().toLocalTime();
        modStartAppComboBox.setItems(CollectionLists.getStart(s).sorted());
        modStartAppComboBox.getSelectionModel().selectFirst();
        modStartAppComboBox.setItems(CollectionLists.getAllStartHours());
        modStartAppComboBox.setVisibleRowCount(6);

        LocalTime e = appointment.getEnd().toLocalTime();
        modEndAppComboBox.setItems(CollectionLists.getEnd(e).sorted());
        modEndAppComboBox.getSelectionModel().selectFirst();
        modEndAppComboBox.setItems(CollectionLists.getAllEndHours());
        modEndAppComboBox.setVisibleRowCount(6);

        modDatePicker.setValue(appointment.getStart().toLocalDate());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

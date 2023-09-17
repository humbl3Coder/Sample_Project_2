package controller;

import helper.CollectionLists;
import helper.UsersQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.Appointments;
import model.Users;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

/**
 * This controller class is for the Login screen.
 */

public class Login implements Initializable {

    /**
     * These attributes (stage/scene) are declared to be able to use the UI.
     */

    @FXML
    private Button loginLOGIN;

    @FXML
    private Label loginUsername;

    @FXML
    public Label loginPassword;

    @FXML
    private Label errorMsgLbl;

    @FXML
    private TextField passWordTxt;

    @FXML
    private TextField userIDTxt;

    @FXML
    private Label ZoneIDLbl;

    /**
     * This method enables or deters a user from being able to log into the system. It checks a username and password
     * against the Users table. If you have the correct information it will send the user to the main Landing screen .
     * It also sends information to login_activity about each login.
     *
     * @param event Checks username/password against Users table.
     * @throws IOException
     * @throws SQLException
     */

    @FXML
    void onActionLogIn(ActionEvent event) throws IOException, SQLException {

        FileWriter loginFile = new FileWriter("src/login_activity.txt", true);
        PrintWriter loginPrint = new PrintWriter(loginFile);

        //found help for time formatting on https://www.baeldung.com/java-datetimeformatter

        String date = "yyyy-MM-dd";
        String time = "HH:mm:ss";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(date);
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern(time);

        String userName = userIDTxt.getText();
        String password = passWordTxt.getText();

        if (UsersQuery.userLogin(userName, password) == true) {
            loginPrint.println("Username: " + userName + " Date/Time: " + dateFormat.format(LocalDate.now()) + " " + timeFormat.format(LocalTime.now()) + " Login: Successful!");
            loginPrint.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Landing.fxml"));
            loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }

        else {
            loginPrint.println("Username: " + userName + " Date/Time: " + dateFormat.format(LocalDate.now()) + " " + timeFormat.format(LocalTime.now()) + " Login: Not successful!");
            loginPrint.close();

            errorMsgLbl.setText(Main.rb.getString("Error"));
        }

        /*boolean match = false;

        for (Users u: CollectionLists.allUsers) {

            if (u.getPass().equals(password) && u.getUseName().equals(userName)) {
                match = true;
                break;
            }

        }

        if (match) {

            loginPrint.println("Username: " + userName + " Date/Time: " + dateFormat.format(LocalDate.now()) + " " + timeFormat.format(LocalTime.now()) + " Login: Successful!");
            loginPrint.close();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/Landing.fxml"));
            loader.load();

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();


        }

        else {

            loginPrint.println("Username: " + userName + " Date/Time: " + dateFormat.format(LocalDate.now()) + " " + timeFormat.format(LocalTime.now()) + " Login: Not successful!");
            loginPrint.close();

            errorMsgLbl.setText(Main.rb.getString("Error"));
        }*/

    }

    /**
     * This method initializes the language displayed on the login screen depending on the Locale that the user is
     * logging in from.
     *
     * @param url
     * @param resourceBundle
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginLOGIN.setText(Main.rb.getString("Login"));
        loginUsername.setText(Main.rb.getString("Username"));
        loginPassword.setText(Main.rb.getString("Password"));

        ZoneIDLbl.setText(String.valueOf(ZoneId.systemDefault()));

    }



}

package main;

import helper.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.EndHours;
import model.Months;
import model.StartHours;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;


public class Main extends Application {

   public static ResourceBundle rb;

    /**
     * This method opens and sets the stage for the Login form
     * @param stage the Login Form
     * @throws IOException
     */

   @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        stage.setTitle("Welcome to Schedule Maker");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        /**
         * These variables establish a resource bundle for the automatic language selection on the log in screen and
         * gets the initial values from the database.
         */

        rb = ResourceBundle.getBundle("main/Nat", Locale.getDefault());

        ScheduleTable.openConnection();

        /**
         * These statements create the objects for the various classes in the program.
         */

        UsersQuery.userSelect();
        CountriesQuery.countSelect();
        DivisionsQuery.divSelect();
        CustomersQuery.cusSelect();
        ContactsQuery.conSelect();
        AppointmentsQuery.appSelect();

        /**
         * These values add hours to both the StartHours and EndHours lists as well as the months to the allMonths list.
         */

        for (int i = 0; i < 24; ++i) {
            CollectionLists.allStartHours.add( new StartHours(LocalTime.of(i, 0)));
        }

        for (int i = 2; i < 25; ++i) {
            CollectionLists.allEndHours.add( new EndHours(LocalTime.of(i - 1, 0)));
        }

        CollectionLists.allEndHours.add(new EndHours(LocalTime.of(0,0)));

        CollectionLists.allMonths.add(new Months(1,"January"));
        CollectionLists.allMonths.add(new Months(2,"February"));
        CollectionLists.allMonths.add(new Months(3,"March"));
        CollectionLists.allMonths.add(new Months(4,"April"));
        CollectionLists.allMonths.add(new Months(5,"May"));
        CollectionLists.allMonths.add(new Months(6,"June"));
        CollectionLists.allMonths.add(new Months(7,"July"));
        CollectionLists.allMonths.add(new Months(8,"August"));
        CollectionLists.allMonths.add(new Months(9,"September"));
        CollectionLists.allMonths.add(new Months(10,"October"));
        CollectionLists.allMonths.add(new Months(11,"November"));
        CollectionLists.allMonths.add(new Months(12,"December"));

        launch(args);

        ScheduleTable.closeConnection();
    }
}

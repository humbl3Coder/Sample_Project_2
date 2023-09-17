package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * This helper class is for collection lists.
 */

public abstract class CollectionLists {

    /**
     * This method declares the allAppointments list.
     */

    public static ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    /**
     * This method declares the allCustomers list.
     */

    public static ObservableList<Customers> allCustomers = FXCollections.observableArrayList();

    /**
     * This method declares the allContacts list.
     */

    public static ObservableList<Contacts> allContacts = FXCollections.observableArrayList();

    /**
     * This method declares the allCountries list.
     */

    public static ObservableList<Countries> allCountries = FXCollections.observableArrayList();

    /**
     * This method declares the allDivisions list.
     */

    public static ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();

    /**
     * This method declares the allUsers list.
     */

    public static ObservableList<Users> allUsers = FXCollections.observableArrayList();

    /**
     * This method declares the allStartHours list.
     */

    public static ObservableList<StartHours> allStartHours = FXCollections.observableArrayList();

    /**
     * This method declares the allEndHours list.
     */

    public static ObservableList<EndHours> allEndHours = FXCollections.observableArrayList();

    /**
     * This method declares the allDistinctTypes list.
     */

    public static ObservableList<DistinctTypes> allDistinctTypes = FXCollections.observableArrayList();

    /**
     * This method declares the allMonths list.
     */

    public static ObservableList<Months> allMonths = FXCollections.observableArrayList();

    /**
     * This method returns the allAppointments list.
     * @return allAppointments list
     */

    public static ObservableList<Appointments> getAllAppointments() {return allAppointments;}

    /**
     * This method deletes an appointment from the AllAppointments list.
     *
     * @param selectedAppointment The appointment to be deleted.
     * @return AllAppointments with appointment removed
     */

    public static boolean deleteAppointment(Appointments selectedAppointment) {
        return getAllAppointments().remove(selectedAppointment);}

    /**
     * This method returns the allCustomers list.
     *
     * @return allCustomers list
     */

    public static ObservableList<Customers> getAllCustomers() {return allCustomers;}

    /**
     * This method deletes a customer from the allCustomers list.
     *
     * @param selectedCustomer The customer to be deleted.
     * @return AllCustomers with customer removed
     */

    public static boolean deleteCustomer(Customers selectedCustomer) { return getAllCustomers().remove(selectedCustomer);}

    /**
     * This method returns the allDistinctTypes list.
     *
     * @return allDistinctTypes list
     */

    public static ObservableList<DistinctTypes> getAllDistinctTypes() {return allDistinctTypes;}

    /**
     * This method returns the allCountries list.
     *
     * @return allCountries list
     */

    public static ObservableList<Countries> getAllCountries() {return allCountries;}

    /**
     * This method returns the allDivisions list.
     *
     * @return allDivisions list
     */

    public static ObservableList<Divisions> getAllDivisions() {return allDivisions;}

    /**
     * This method returns the allContacts list.
     *
     * @return allContacts list
     */

    public static ObservableList<Contacts> getAllContacts() {return allContacts;}

    /**
     * This method returns the allUsers list.
     *
     * @return allUsers list
     */

    public static ObservableList<Users> getAllUsers() {return allUsers;}

    /**
     * This method returns the allStartHours list.
     *
     * @return allStartHours list
     */

    public static ObservableList<StartHours> getAllStartHours() {return allStartHours;}

    /**
     * This method returns the allEndHours list.
     *
     * @return allEndHours list
     */

    public static ObservableList<EndHours> getAllEndHours() {return allEndHours;}

    /**
     * This method returns the allMonths list.
     *
     * @return allMonths list
     */

    public static ObservableList<Months> getAllMonths() {return allMonths;}

    /**
     * This method returns the filteredDivisions list filtered by countryID.
     *
     * @param countryID The countryID used to filter divisions.
     * @return filteredDivisions list
     */

    public static ObservableList<Divisions> getFilteredListD(int countryID) {
        ObservableList<Divisions> filteredDivisions = FXCollections.observableArrayList();

        for (Divisions d : allDivisions) {
            if (d.getCountID() == countryID)
                filteredDivisions.add(d);
        }
        return filteredDivisions;
    }

    /**
     * This method returns the filteredCountry list filtered by countryID.
     *
     * @param countryID The countryID used to filter countries.
     * @return filteredCountry list
     */

    public static ObservableList<Countries> getCountry(int countryID) {
        ObservableList<Countries> filteredCountry = FXCollections.observableArrayList();

        for (Countries c : allCountries) {
            if(c.getCountID() == countryID)
                filteredCountry.add(c);
        }
        return filteredCountry;
    }

    /**
     * This method returns the filteredDivision by divisionID.
     *
     * @param CusDivID The CusDivID used to filtered divisions.
     * @return filteredDivision list
     */

    public static ObservableList<Divisions> getDivision (int CusDivID) {
        ObservableList<Divisions> filteredDivision =  FXCollections.observableArrayList();

        for (Divisions d : allDivisions) {
            if (d.getDivID() == CusDivID)
                filteredDivision.add(d);
        }
        return filteredDivision;
    }

    /**
     * This method returns the filteredContact list filtered by contactID.
     *
     * @param conID The conID used to filter contacts.
     * @return filteredContact list
     */

    public static ObservableList<Contacts> getContact (int conID) {
        ObservableList<Contacts> filteredContact = FXCollections.observableArrayList();

        for (Contacts c : allContacts) {
            if (c.getConID() == conID)
                filteredContact.add(c);
        }
        return filteredContact;
    }

    /**
     * This method returns the filteredCustomer list filtered by customerID.
     *
     * @param cusID the cusID used to filter customers.
     * @return filteredCustomer list
     */

    public static ObservableList<Customers> getCustomer (int cusID) {
        ObservableList<Customers> filteredCustomer = FXCollections.observableArrayList();

        for (Customers cu : allCustomers) {
            if (cu.getCusID() == cusID)
                filteredCustomer.add(cu);
        }
        return filteredCustomer;
    }

    /**
     * This method returns the filteredUser list filtered by userID.
     *
     * @param useID the useID used to filter users.
     * @return filteredUser list
     */

    public static ObservableList<Users> getUser (int useID) {
        ObservableList<Users> filteredUser = FXCollections.observableArrayList();

        for (Users u : allUsers) {
            if (u.getUseID() == useID)
                filteredUser.add(u);
        }
        return filteredUser;
    }

    /**
     * The method returns the filteredStart list filtered by start.
     *
     * @param start the start used to filter start times.
     * @return filteredStart list
     */

    public static ObservableList<StartHours> getStart (LocalTime start) {
        ObservableList<StartHours> filteredStart = FXCollections.observableArrayList();
        for (StartHours s : allStartHours) {
            if (s.getStartLT() == start)
                filteredStart.add(s);
        }
        return filteredStart;
    }

    /**
     * The method returns the filteredEnd list filtered by start.
     *
     * @param end the end used to filter end times.
     * @return filteredEnd list
     */

    public static ObservableList<EndHours> getEnd (LocalTime end) {
        ObservableList<EndHours> filteredEnd = FXCollections.observableArrayList();
        for (EndHours e : allEndHours) {
            if (e.getEndLT() == end)
                filteredEnd.add(e);
        }
        return filteredEnd;
    }

    /**
     * This method returns weekAps list filtered by startDate.
     *
     * @return weekApps list
     */

    public static ObservableList<Appointments> getWeekApp () {
        ObservableList<Appointments> weekApps = FXCollections.observableArrayList();

        for (Appointments w : allAppointments) {

            LocalDate startDate = w.getStart().toLocalDate();
            LocalDate currentDate = LocalDate.now();
            //found help on line 150 on stack overflow, link: https://stackoverflow.com/questions/25912455/get-the-weeknumber-from-a-given-date-in-java-fx
            Locale locale = Locale.getDefault();

            if (startDate.get(WeekFields.of(locale).weekOfWeekBasedYear()) == currentDate.get(WeekFields.of(locale).weekOfWeekBasedYear())) {
                weekApps.add(w);
            }
        }
        return weekApps;
    }

    /**
     * This method returns monthApps list filtered by startDate.
     *
     * @return monthApps list
     */

    public static ObservableList<Appointments> getMonthApp() {
        ObservableList<Appointments> monthApps = FXCollections.observableArrayList();

        for (Appointments m : allAppointments) {

            if (m.getStart().getMonth() == LocalDate.now().getMonth()) {
                monthApps.add(m);
            }
        }
        return monthApps;
    }

    /**
     * This method returns conApp list filtered by contactID.
     *
     * @param conID the conID used to filter contacts
     * @return conApp list
     */

    public static ObservableList<Appointments> getConApp (int conID) {
        ObservableList<Appointments> conApp = FXCollections.observableArrayList();

        for (Appointments c : allAppointments) {
            if (c.getConID() == conID) {
                conApp.add(c);
            }
        }
        return conApp;
    }

    /**
     * This method returns useApp list filtered by useID.
     *
     * @param useID the useID used to filter users
     * @return useApp list
     */

    public static ObservableList<Appointments> getUseApp (int useID) {
        ObservableList<Appointments> useApp = FXCollections.observableArrayList();

        for (Appointments u : allAppointments) {
            if (u.getUseID() == useID) {
                useApp.add(u);
            }
        }
        return useApp;
    }

}

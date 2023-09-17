package helper;

import model.Appointments;
import model.DistinctTypes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * This helper class is for the appointment queries.
 */

public abstract class AppointmentsQuery {

        /**
         * This method inserts an appointment into the Appointments table.
         * @param appID this is the appID of the appointment
         * @param title this is the title of the appointment
         * @param desc this is the desc of the appointment
         * @param loc this is the loc of the appointment
         * @param type this is the type of the appointment
         * @param start this is the start of the appointment
         * @param end this is the end of the appointment
         * @param cusID this is the cusID of the appointment
         * @param useID this is the useID of the appointment
         * @param conID this is the conID of the appointment
         * @return This inserts the new Appointment into the database
         * @throws SQLException
         */

        public static int appInsert(int appID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int cusID, int useID, int conID) throws SQLException {
                String sql = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);

                ps.setInt(1, appID);
                ps.setString(2, title);
                ps.setString(3, desc);
                ps.setString(4, loc);
                ps.setString(5, type);
                ps.setTimestamp(6, Timestamp.valueOf(start));
                ps.setTimestamp(7, Timestamp.valueOf(end));
                ps.setInt(8, cusID);
                ps.setInt(9, useID);
                ps.setInt(10, conID);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
        }

        /**
         * This method updates an appointment in the Appointments table.
         * @param appID this is the appID that SQL will use to find the appointment to be updated
         * @param title this is the title of the updated appointment
         * @param desc this is the desc of the updated appointment
         * @param loc this is the loc of the updated appointment
         * @param type this is the type of the updated appointment
         * @param start this is the start of the updated appointment
         * @param end this is the end of the updated appointment
         * @param cusID this is the cusID of the updated appointment
         * @param useID this is the useID of the updated appointment
         * @param conID this is the conID of the updated appointment
         * @return This updates the Appointment in the database
         * @throws SQLException
         */

        public static int appUpdate(int appID, String title, String desc, String loc, String type, LocalDateTime start, LocalDateTime end, int cusID, int useID, int conID) throws SQLException {
                String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, desc);
                ps.setString(3, loc);
                ps.setString(4, type);
                ps.setTimestamp(5, Timestamp.valueOf(start));
                ps.setTimestamp(6, Timestamp.valueOf(end));
                ps.setInt(7, cusID);
                ps.setInt(8, useID);
                ps.setInt(9, conID);
                ps.setInt(10, appID);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;
        }

        /**
         * This method deletes the appointment from the Appointments table
         * @param appID this is the appID of the appointment
         * @return This deletes the Appointment in the database
         * @throws SQLException
         */

        public static int appDelete(int appID) throws SQLException {
                String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ps.setInt(1, appID);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected;

        }

        /**
         * This method selects appointments with the contact name added.
         * @throws SQLException
         */

        public static void appSelect() throws SQLException {
                String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID, a.Contact_ID, c.Contact_Name FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int appID = rs.getInt("Appointment_ID");
                        String title = rs.getString("Title");
                        String desc = rs.getString("Description");
                        String loc = rs.getString("Location");
                        String type = rs.getString("Type");
                        LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                        LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                        int cusID = rs.getInt("Customer_ID");
                        int useID = rs.getInt("User_ID");
                        int conID = rs.getInt("Contact_ID");
                        String conName = rs.getString("Contact_Name");

                        CollectionLists.allAppointments.add(new Appointments(appID, title, desc, loc, type, start, end, cusID, useID, conID, conName));

                }
        }

        /**
         * This method selects appointments updated from within the database.
         * @throws SQLException
         */

        public static void appAdded() throws SQLException {
                String sql = "SELECT a.Appointment_ID, a.Title, a.Description, a.Location, a.Type, a.Start, a.End, a.Customer_ID, a.User_ID, a.Contact_ID, c.Contact_Name FROM appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID where Appointment_ID = (select max(Appointment_ID) from appointments);";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int appID = rs.getInt("Appointment_ID");
                        String title = rs.getString("Title");
                        String desc = rs.getString("Description");
                        String loc = rs.getString("Location");
                        String type = rs.getString("Type");
                        LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                        LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                        int cusID = rs.getInt("Customer_ID");
                        int useID = rs.getInt("User_ID");
                        int conID = rs.getInt("Contact_ID");
                        String conName = rs.getString("Contact_Name");

                        CollectionLists.allAppointments.add(new Appointments(appID, title, desc, loc, type, start, end, cusID, useID, conID, conName));

                }
        }

        /**
         * This method selects appointment updated with the contact name also.
         * @param appointID This is the appointID of the appointment.
         * @throws SQLException
         */

        public static void appUpdated(int appointID) throws SQLException {
                String sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE Appointment_ID = ?;";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ps.setInt(1, appointID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int appID = rs.getInt("Appointment_ID");
                        String title = rs.getString("Title");
                        String desc = rs.getString("Description");
                        String loc = rs.getString("Location");
                        String type = rs.getString("Type");
                        LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                        LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                        int cusID = rs.getInt("Customer_ID");
                        int useID = rs.getInt("User_ID");
                        int conID = rs.getInt("Contact_ID");
                        String conName = rs.getString("Contact_Name");

                        CollectionLists.allAppointments.add(new Appointments(appID, title, desc, loc, type, start, end, cusID, useID, conID, conName));
                }
        }

        /**
         * This method returns the appointment types from each appointment for a report.
         * @throws SQLException
         */

        public static void appTypes() throws SQLException {
                String sql = "SELECT Appointment_ID, Type FROM appointments GROUP BY Type;";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int appID = rs.getInt("Appointment_ID");
                        String appType = rs.getString("Type");

                        CollectionLists.allDistinctTypes.add(new DistinctTypes(appID, appType));
                }
        }

        /**
         * This method searches the appointments table for an appointment that is in the next upcoming 15 minutes. If so
         * it will return an Appointments object. If not, it will return a null.
         * @return return either an Appointments object or a null
         * @throws SQLException
         */

        public static Appointments appAlert() throws SQLException {
                String sql = "SELECT * FROM client_schedule.appointments WHERE date_format(Start, '%Y-%m-%d') >= curDate() and date_format(Start, '%Y-%m-%d') <= DATE_ADD(curDate(), INTERVAL 1 DAY) and Start >= curtime() limit 1;";
                PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                long difference = 0;
                if (rs.next()) {
                        LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                        difference = ChronoUnit.MINUTES.between(LocalTime.now().truncatedTo(ChronoUnit.MINUTES), start);
                        if (difference <= 15 && difference >= 1 ) {
                                return new Appointments(rs.getInt("Appointment_ID"), start, rs.getTimestamp("End").toLocalDateTime());
                        }
                        else {
                                return null;
                        }
                }
                return null;
        }
}

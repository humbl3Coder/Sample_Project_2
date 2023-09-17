package helper;

import model.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This helper class is for the contact queries.
 */

public abstract class ContactsQuery {

    /**
     * This method selects all contacts from database.
     *
     * @throws SQLException
     */

    public static void conSelect() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int conID = rs.getInt("Contact_ID");
            String conName = rs.getString("Contact_Name");
            String conEmail = rs.getString("Email");
            CollectionLists.allContacts.add(new Contacts(conID, conName, conEmail));
        }
    }
}

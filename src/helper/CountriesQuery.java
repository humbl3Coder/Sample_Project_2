package helper;

import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This helper class is for the Country queries.
 */
public abstract class CountriesQuery {

    /**
     * This method select all countries from the database.
     *
     * @throws SQLException
     */

    public static void countSelect() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countID = rs.getInt("Country_ID");
            String count = rs.getString("Country");
            CollectionLists.allCountries.add(new Countries(countID, count));
        }
    }
}

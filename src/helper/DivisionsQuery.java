package helper;

import model.Divisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This helper class is for the divisions queries.
 */

public abstract class DivisionsQuery {

    /**
     * This method selects all divisions from the database.
     * @throws SQLException
     */

    public static void divSelect() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divID = rs.getInt("Division_ID");
            String div = rs.getString("Division");
            int countID = rs.getInt("Country_ID");
            CollectionLists.allDivisions.add(new Divisions(divID, div, countID));
        }
    }
}

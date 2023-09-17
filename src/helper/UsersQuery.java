package helper;

import model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This helper class is for the users queries.
 */

public abstract class UsersQuery {

    /**
     * This method select all users from the database.
     * @throws SQLException
     */

    public static void userSelect() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int useID = rs.getInt("User_ID");
            String useName = rs.getString("User_Name");
            String pass = rs.getString("Password");
            CollectionLists.allUsers.add(new Users(useID, useName, pass));
        }
    }

    /**
     * This method selects a specific user giver the user name.
     * @param user The user name of the user to be searched for.
     * @return returns a User ID
     * @throws SQLException
     */

    public static int userGetID (String user) throws SQLException {
        String sql = "SELECT User_ID FROM users WHERE User_Name = ?";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int userID = rs.getInt("User_ID");
        return userID;

    }

    /**
     * This method will search the users table for a record with the username and password
     * that the user has entered. If it does find a record, it will return true. If not, it will
     * return a false.
     * @param user the entered UserName
     * @param pass the entered Password
     * @return returns boolean value whether true or false
     * @throws SQLException
     */

    public static boolean userLogin (String user, String pass) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
        PreparedStatement ps = ScheduleTable.connection.prepareStatement(sql);
        ps.setString(1, user);
        ps.setString(2, pass);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}

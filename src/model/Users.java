package model;

/**
 * This class creates Users.
 */

public class Users {

    private int useID;
    private String useName;
    private String pass;

    /**
     * This method creates a Users object.
     * @param useID the useID of the User
     * @param useName the useName of the User
     * @param pass the pass of the User
     */

    public Users(int useID, String useName, String pass) {
        this.useID = useID;
        this.useName = useName;
        this.pass = pass;
    }

    /**
     *
     * @return useID
     */

    public int getUseID() {
        return useID;
    }

    /**
     *
     * @param useID the useID to set
     */

    public void setUseID(int useID) {
        this.useID = useID;
    }

    /**
     *
     * @return useName
     */

    public String getUseName() {
        return useName;
    }

    /**
     *
     * @param useName the useName to set
     */

    public void setUseName(String useName) {
        this.useName = useName;
    }

    /**
     *
     * @return pass
     */

    public String getPass() {
        return pass;
    }

    /**
     *
     * @param pass the pass to set
     */

    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     *
     * @return the String useID for Combo Box
     */

    @Override
    public String toString() {
        return (Integer.toString(useID));
    }
}

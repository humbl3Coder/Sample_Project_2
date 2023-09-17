package model;

/**
 * This class creates Contacts.
 */

public class Contacts {

    private int conID;
    private String conName;
    private String conEmail;

    /**
     * The method creates the Contacts object.
     * @param conID the conID of the contact
     * @param conName the conName of the contact
     * @param conEmail the conEmail of the contanct
     */

    public Contacts(int conID, String conName, String conEmail) {
        this.conID = conID;
        this.conName = conName;
        this.conEmail = conEmail;
    }

    /**
     *
     * @return the conID
     */

    public int getConID() {
        return conID;
    }

    /**
     *
     * @param conID the conID to set
     */

    public void setConID(int conID) {
        this.conID = conID;
    }

    /**
     *
     * @return the conName
     */

    public String getConName() {
        return conName;
    }

    /**
     *
     * @param conName the conName to set
     */

    public void setConName(String conName) {
        this.conName = conName;
    }

    /**
     *
     * @return conEmail
     */

    public String getConEmail() {
        return conEmail;
    }

    /**
     *
     * @param conEmail the conEmail to set
     */

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    /**
     *
     * @return a String conName in the Combo Box
     */

    @Override
    public String toString() {
        return (conName);
    }
}

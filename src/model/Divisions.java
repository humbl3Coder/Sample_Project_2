package model;

/**
 * This class creates Divisions.
 */

public class Divisions {

    private int divID;
    private String div;
    private int countID;

    /**
     * This method creates Divisions object.
     * @param divID the divID of the Division
     * @param div the div of the Division
     * @param countID the countID of the Divisioin
     */

    public Divisions(int divID, String div, int countID) {
        this.divID = divID;
        this.div = div;
        this.countID = countID;
    }

    /**
     *
     * @return divID
     */

    public int getDivID() {
        return divID;
    }

    /**
     *
     * @param divID the divID to set
     */

    public void setDivID(int divID) {
        this.divID = divID;
    }

    /**
     *
     * @return div
     */

    public String getDiv() {
        return div;
    }

    /**
     *
     * @param div the div to set
     */

    public void setDiv(String div) {
        this.div = div;
    }

    /**
     *
     * @return countID
     */

    public int getCountID() {
        return countID;
    }

    /**
     *
     * @param countID the countID to set
     */

    public void setCountID(int countID) {
        this.countID = countID;
    }

    /**
     *
     * @return a String div in the Combo Box
     */

    @Override
    public String toString() {
        return (div);
    }
}

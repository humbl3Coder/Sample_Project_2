package model;

/**
 * This class creates Customers.
 */

public class Customers {

    private int cusID;
    private String cusName;
    private String cusAdd;
    private String cusPos;
    private String cusPhone;
    private int cusDivID;
    private String cusDiv;

    /**
     * This method creates a Customers object.
     * @param cusID the cusID of the Customer
     * @param cusName the cusName of the Customer
     * @param cusAdd the cusAdd of the Customer
     * @param cusPos the cusPos of the Customer
     * @param cusPhone the cusPhone of the Customer
     * @param cusDivID the cusDivID of the Customer
     * @param cusDiv the cusDiv of the Customer
     */

    public Customers(int cusID, String cusName, String cusAdd, String cusPos, String cusPhone, int cusDivID, String cusDiv) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.cusAdd = cusAdd;
        this.cusPos = cusPos;
        this.cusPhone = cusPhone;
        this.cusDivID = cusDivID;
        this.cusDiv = cusDiv;
    }

    /**
     *
     * @return cusID
     */

    public int getCusID() {
        return cusID;
    }

    /**
     *
     * @param cusID the cusID to set
     */

    public void setCusID(int cusID) {
        this.cusID = cusID;
    }

    /**
     *
     * @return cusName
     */

    public String getCusName() {
        return cusName;
    }

    /**
     *
     * @param cusName the cusName to set
     */

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    /**
     *
     * @return cusAdd
     */

    public String getCusAdd() {
        return cusAdd;
    }

    /**
     *
     * @param cusAdd the cusAdd to set
     */

    public void setCusAdd(String cusAdd) {
        this.cusAdd = cusAdd;
    }

    /**
     *
     * @return cusPos
     */

    public String getCusPos() {
        return cusPos;
    }

    /**
     *
     * @param cusPos the cusPos to set
     */

    public void setCusPos(String cusPos) {
        this.cusPos = cusPos;
    }

    /**
     *
     * @return cusPhone
     */

    public String getCusPhone() {
        return cusPhone;
    }

    /**
     *
     * @param cusPhone the cusPhone to set
     */

    public void setCusPhone(String cusPhone) { this.cusPhone = cusPhone; }

    public int getCusDivID() {
        return cusDivID;
    }

    /**
     *
     * @param cusDivID the cusDivID to set
     */

    public void setCusDivID(int cusDivID) {
        this.cusDivID = cusDivID;
    }

    /**
     *
     * @return cusDiv
     */

    public String getCusDiv() { return cusDiv; }

    /**
     *
     * @param cusDiv the cusDiv to set
     */

    public void setCusDiv(String cusDiv) {
        this.cusDiv = cusDiv;
    }

    /**
     *
     * @return a String cusID in the Combo Box
     */

    @Override
    public String toString() {
        return Integer.toString(cusID);
    }
}

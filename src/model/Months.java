package model;

/**
 * This class creates Months.
 */

public class Months {

    private int monthNum;
    private String month;

    /**
     * This method creates a Months object.
     * @param monthNum the monthNum of the Month
     * @param month the month of the Month
     */

    public Months(int monthNum, String month) {
        this.monthNum = monthNum;
        this.month = month;
    }

    /**
     *
     * @return monthNum
     */

    public int getMonthNum() {
        return monthNum;
    }

    /**
     *
     * @param monthNum the monthNum to set
     */

    public void setMonthNum(int monthNum) {
        this.monthNum = monthNum;
    }

    /**
     *
     * @return month
     */

    public String getMonth() {
        return month;
    }

    /**
     *
     * @param month the month to set
     */

    public void setMonth(String month) {
        this.month = month;
    }

    /**
     *
     * @return the String month for the Combo Box
     */

    @Override
    public String toString() {
        return (month);
    }
}

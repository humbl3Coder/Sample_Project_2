package model;

/**
 * This class creates Countries.
 */

public class Countries {

    private int countID;
    private String count;

    /**
     * This method creates a Countries object.
     * @param countID the countID of the Country
     * @param count the count of the Country
     */

    public Countries(int countID, String count) {
        this.countID = countID;
        this.count = count;
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
     * @return count
     */

    public String getCount() {
        return count;
    }

    /**
     *
     * @param count the count to set
     */

    public void setCount(String count) {
        this.count = count;
    }

    /**
     *
     * @return a String count in the Combo Box
     */

    @Override
    public String toString() {
        return (count);
    }


}

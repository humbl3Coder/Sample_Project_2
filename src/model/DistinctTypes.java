package model;

/**
 * This class creates DistinctTypes.
 */

public class DistinctTypes {

    private int appID;
    private String appType;

    /**
     * This method creates a Distincttypes object.
     * @param appID the appID of the DistinctType
     * @param appType the appType of the DistinctType
     */

    public DistinctTypes(int appID, String appType) {
        this.appID = appID;
        this.appType = appType;
    }

    /**
     *
     * @return appID
     */

    public int getAppID() {
        return appID;
    }

    /**
     *
     * @param appID the appID to set
     */

    public void setAppID(int appID) {
        this.appID = appID;
    }

    /**
     *
     * @return appType
     */

    public String getAppType() {
        return appType;
    }

    /**
     *
     * @param appType the appType to set
     */

    public void setAppType(String appType) {
        this.appType = appType;
    }

    /**
     *
     * @return a String appType for the Combo Box
     */

    @Override
    public String toString() {
        return (appType);
    }
}

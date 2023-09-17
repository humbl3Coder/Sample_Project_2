package model;

import java.time.LocalTime;

/**
 * This class creates EndHours.
 */

public class EndHours {

    private LocalTime endTime;

    /**
     * This method creates an EndHours object.
     * @param localTime the Localtime of the EndHours
     */

    public EndHours(LocalTime localTime) {
        this.endTime = localTime;
    }

    /**
     *
     * @return endTime
     */

    public LocalTime getEndLT() {
        return endTime;
    }

    /**
     *
     * @param localTime the endTime to be set
     */

    public void setEndLT(LocalTime localTime) {
        this.endTime = localTime;
    }

    /**
     *
     * @return the String endTime for the Combo Box
     */

    @Override
    public String toString() {
        return (endTime.toString());
    }
}

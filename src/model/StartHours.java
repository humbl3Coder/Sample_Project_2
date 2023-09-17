package model;

import java.time.LocalTime;

/**
 * This class creates StartHours.
 */

public class StartHours {

    private LocalTime startTime;

    /**
     * This method creates a StartHours object.
     * @param localTime the LocalTime of the StartHours
     */

    public StartHours(LocalTime localTime) {
        this.startTime = localTime;
    }

    /**
     *
     * @return startTime
     */

    public LocalTime getStartLT() {
        return startTime;
    }

    /**
     *
     * @param localTime the LocalTime to set
     */

    public void setStartLT(LocalTime localTime) {
        this.startTime = localTime;
    }

    /**
     *
     * @return a String startTime for Combo Box
     */

    @Override
    public String toString() {
        return (startTime.toString());
    }


}

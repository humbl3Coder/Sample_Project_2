package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This helper class is for time conversions.
 */

public abstract class TimeConversions {

    /**
     * This method converts LocalTime to EST.
     * @param LDT the LocalTime to be converted.
     * @return the new time in EST
     */

    public static LocalDateTime toEstLDT (LocalDateTime LDT) {

        ZoneId myZoneID = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(LDT, myZoneID);

        ZoneId estZoneId = ZoneId.of("America/New_York");
        ZonedDateTime estZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), estZoneId);
        LocalDateTime newLDT = estZDT.toLocalDateTime();

        return newLDT;

    }

    /**
     * This method converts UTC to LocalTime.
     * @param SDT the UTC time to be converted.
     * @return the new time in LocalTime.
     */

    public static LocalDateTime toLocalLDT (LocalDateTime SDT) {

        ZoneId zoneID = ZoneId.of("UTC");
        ZonedDateTime UDT = ZonedDateTime.of(SDT, zoneID);

        ZoneId locZoneId = ZoneId.systemDefault();
        ZonedDateTime locZDT = ZonedDateTime.ofInstant(UDT.toInstant(), locZoneId);
        LocalDateTime locLDT = locZDT.toLocalDateTime();

        return locLDT;
    }
}

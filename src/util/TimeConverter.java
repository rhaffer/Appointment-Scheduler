package util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class handles the Time Conversions necessary throughout the application.
 */
public class TimeConverter {

    /**
     * This method converts a ZonedDateTime to EST
     * @param time The time to be converted
     * @return Returns the passed time in EST time
     */
    public LocalDateTime convertToEST(ZonedDateTime time){
        ZoneId estZoneID = ZoneId.of("America/New_York");
        ZonedDateTime estTime = time.withZoneSameInstant(estZoneID);
        return estTime.toLocalDateTime();
    }

    /**
     * This method converts a ZonedDateTime to GMT
     * @param time The time to be converted
     * @return Returns the passed time in GMT time
     */
    public LocalDateTime convertToGMT(ZonedDateTime time){
        ZoneId gmtZoneID = ZoneId.of("Etc/UTC");
        ZonedDateTime gmtTime = time.withZoneSameInstant(gmtZoneID);
        return gmtTime.toLocalDateTime();
    }

    /**
     * This method converts a ZonedDateTime to Local time zone
     * @param time The time to be converted
     * @return Returns the passed time in Local time
     */
    public LocalDateTime convertToLocal(ZonedDateTime time){
        ZoneId localZoneID = ZoneId.systemDefault();
        ZonedDateTime localTime = time.withZoneSameInstant(localZoneID);
        return localTime.toLocalDateTime();
    }
}

package util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeConverter {

    public LocalDateTime convertToEST(ZonedDateTime time){
        ZoneId estZoneID = ZoneId.of("America/New_York");
        ZonedDateTime estTime = time.withZoneSameInstant(estZoneID);
        return estTime.toLocalDateTime();
    }

    public LocalDateTime convertToGMT(ZonedDateTime time){
        ZoneId gmtZoneID = ZoneId.of("Etc/UTC");
        ZonedDateTime gmtTime = time.withZoneSameInstant(gmtZoneID);
        return gmtTime.toLocalDateTime();
    }

    public LocalDateTime convertToLocal(ZonedDateTime time){
        ZoneId localZoneID = ZoneId.systemDefault();
        ZonedDateTime localTime = time.withZoneSameInstant(localZoneID);
        return localTime.toLocalDateTime();
    }
}

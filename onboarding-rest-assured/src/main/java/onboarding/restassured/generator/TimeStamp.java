package onboarding.restassured.generator;

import java.sql.Timestamp;

/**
 * Generate timestamp
 */
public class TimeStamp {

    /**
     * Return timestamps
     *
     * @param ts1 format timestamps
     * @return long
     */
    public long TimeStamps(long ts1) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long ts = timestamp.getTime();
        return ts;
    }
}
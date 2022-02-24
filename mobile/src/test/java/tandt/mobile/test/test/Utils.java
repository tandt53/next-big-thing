package tandt.mobile.test.test;

public class Utils {

    public static void delay(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

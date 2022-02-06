package tandt.mobile.test.test;

public class TestUtils {

    public static void delay(long timeout){
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

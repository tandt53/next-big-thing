package onboarding.restassured.generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * FunctionRandom supports mainly for generating random data
 */
public class FunctionRandom {
    private Random random;

    /**
     * Init Random object
     */
    public FunctionRandom() {
        random = new Random();
    }

    /**
     * Function random : id, tobe_run , amount , ticket_type_id , received_ticket_type_id
     *
     * @param min giá trị min
     * @param max giá trị max
     * @return int
     */
    public int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Generate random email with custom length
     *
     * @param length độ dài của email cần random
     * @return email
     */
    public String generateRandomEmail(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyz";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = temp.substring(0, temp.length() - 9) + "@gmail.com";
        return email;
    }

    /**
     * Generate a sequence of characters with expected length
     *
     * @param lengthChar độ dài ký tự cần random
     * @return string
     */
    public String generateCharacter(int lengthChar) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < lengthChar; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s.append(c);
        }
        return s.toString();

    }

    /**
     * generate id number based on expected length
     *
     * @param length độ dài của số cần random
     * @return number
     */
    public String generateIdNumber(int length) {
        String alphabet = "0123456789";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = alphabet.charAt(random.nextInt(9));
            s.append(c);
        }
        return s.toString();

    }


}

	

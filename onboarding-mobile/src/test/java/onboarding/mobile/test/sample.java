package onboarding.mobile.test;

import java.util.Arrays;

public class sample {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "cbdea";
        // find added char in t after shuffle s
        System.out.println(findTheDifference(s, t));

    }

    private static char findTheDifference(String s, String t) {
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);

        for (int i = 0; i < sa.length; i++) {
            if (sa[i] != ta[i]) {
                return ta[i];
            }
        }
        return ' ';
    }

    private static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}

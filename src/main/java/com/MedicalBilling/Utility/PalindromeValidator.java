package com.MedicalBilling.Utility;

public class PalindromeValidator {

    /**
     * Validates if a string is a palindrome, ignoring case and non-alphanumeric characters.
     *
     * @param input the string to validate
     * @return true if the string is a palindrome (ignoring case and non-alphanumeric chars), false otherwise
     */
    public static boolean isPalindrome(String input) {
        if (input == null || input.isEmpty()) {
            return true;
        }

        // Convert to lowercase and filter only alphanumeric characters
        String cleaned = input.toLowerCase().replaceAll("[^a-z0-9]", "");

        // Check if cleaned string is a palindrome
        int left = 0;
        int right = cleaned.length() - 1;

        while (left < right) {
            if (cleaned.charAt(left) != cleaned.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

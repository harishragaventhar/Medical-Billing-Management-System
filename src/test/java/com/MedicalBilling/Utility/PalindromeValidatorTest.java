package com.MedicalBilling.Utility;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PalindromeValidatorTest {

    @Test
    public void testSimplePalindrome() {
        assertTrue(PalindromeValidator.isPalindrome("racecar"));
    }

    @Test
    public void testPalindromeWithMixedCase() {
        assertTrue(PalindromeValidator.isPalindrome("RaceCar"));
    }

    @Test
    public void testPalindromeWithSpacesAndPunctuation() {
        assertTrue(PalindromeValidator.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void testNonPalindrome() {
        assertFalse(PalindromeValidator.isPalindrome("hello"));
    }

    @Test
    public void testEmptyString() {
        assertTrue(PalindromeValidator.isPalindrome(""));
    }

    @Test
    public void testNullString() {
        assertTrue(PalindromeValidator.isPalindrome(null));
    }
}

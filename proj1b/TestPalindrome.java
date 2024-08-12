import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
//    Uncomment this class once you've created your Palindrome class.

    @Test
    public void testIsPalindrome1() {
        String palindrome1 = "";
        String palindrome2 = "a";
        String palindrome3 = "aaccbccaa";
        String nonPalindrome1 = "abc";
        String nonPalindrome2 = "ab";


        assertTrue(palindrome.isPalindrome(palindrome1));
        assertTrue(palindrome.isPalindrome(palindrome2));
        assertTrue(palindrome.isPalindrome(palindrome3));
        assertFalse(palindrome.isPalindrome(nonPalindrome1));
        assertFalse(palindrome.isPalindrome(nonPalindrome2));
    }

    @Test
    public void testIsPalindrome2() {
        String palindrome1 = "";
        String palindrome2 = "abb";
        String palindrome3 = "a";
        String nonPalindrome1 = "abc";
        String nonPalindrome2 = "aa";

        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome(palindrome1, cc));
        assertTrue(palindrome.isPalindrome(palindrome2, cc));
        assertTrue(palindrome.isPalindrome(palindrome3, cc));
        assertFalse(palindrome.isPalindrome(nonPalindrome1, cc));
        assertFalse(palindrome.isPalindrome(nonPalindrome2, cc));

    }
}

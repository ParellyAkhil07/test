
public class AnagramOfPalindrome {

    // Determine if the given word is a re-scrambling of a palindrome.
    public static boolean isAnagramOfPalindrome(String word) {
        // Create a HashMap to count occurrences of each character
        HashMap<Character, Integer> wordDict = new HashMap<>();

        // Populate the HashMap with character counts
        for (char l : word.toCharArray()) {
            wordDict.put(l, wordDict.getOrDefault(l, 0) + 1);
        }

        // Check the number of characters with odd counts
        int oddCount = 0;
        for (int count : wordDict.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        // A word can be an anagram of a palindrome if it has at most one odd character count
        return oddCount <= 1;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isAnagramOfPalindrome("a")); // true
        System.out.println(isAnagramOfPalindrome("ab")); // false
        System.out.println(isAnagramOfPalindrome("aab")); // true
        System.out.println(isAnagramOfPalindrome("arceace")); // true
        System.out.println(isAnagramOfPalindrome("arceaceb")); // false

        // If all tests pass
        System.out.println("ALL TESTS PASSED!");
    }
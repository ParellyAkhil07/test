import java.util.Set;

public class IsUnique {

    // Method to check if a string has all unique characters
    public static boolean isUnique(String string) {
        // Create a set to store characters
        Set<Character> charSet = new HashSet<>();

        // Iterate over each character in the string
        for (char c : string.toCharArray()) {
            // If character is already in the set, return false
            if (!charSet.add(c)) {
                return false;
            }
        }
        // All characters are unique
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isUnique("asdfghjkl") == true);
        System.out.println(isUnique("1234567asdf") == true);
        System.out.println(isUnique("!@#$%^&asdfg123") == true);
        System.out.println(isUnique("abcdABCD") == true);

        System.out.println(isUnique("asdfghjkll") == false);
        System.out.println(isUnique("1qwerty1") == false);
        System.out.println(isUnique("poiu$asdf$") == false);
    }
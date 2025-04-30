import java.util.ArrayList;
import java.util.List;

/**
 * Generates all permutations of a given string using a recursive approach.
 */
public class RecursivePermutations {

    /**
     * Public entry point for generating permutations recursively.
     * Handles initial validation and sets up the recursive call.
     *
     * @param inputString The string for which to generate permutations.
     * @return A List of strings containing all permutations. Returns an empty list
     *         if the input is null. Returns a list containing "" if input is empty.
     */
    public List<String> generatePermutationsRecursive(String inputString) {
        // Input Validation
        if (inputString == null) {
            System.err.println("Error: Input string cannot be null.");
            return new ArrayList<>(); // Return empty list for null input
        }

        List<String> permutations = new ArrayList<>();
        // Start the recursive process with an empty prefix and the full input string as the suffix.
        permuteHelper("", inputString, permutations);
        return permutations;
    }

    /**
     * Recursive helper method to generate permutations.
     *
     * The logic is: for each character in the 'suffix' (remaining characters),
     * fix it to the 'prefix' (characters already chosen), and recursively find
     * permutations of the characters left in the suffix.
     *
     * @param prefix The string built so far in the current permutation path.
     * @param suffix The remaining characters to be permuted.
     * @param results The list to accumulate the final permutations.
     */
    private void permuteHelper(String prefix, String suffix, List<String> results) {
        int n = suffix.length();

        // Base Case
        // If there are no characters left to permute (suffix is empty),
        // the current prefix represents a complete permutation. Add it to the results.
        if (n == 0) {
            results.add(prefix);
        } else {
            // Iterate through each character in the current suffix.
            for (int i = 0; i < n; i++) {
                // 1. Choose the i-th character from the suffix.
                char chosenChar = suffix.charAt(i);

                // 2. Form the new prefix by adding the chosen character.
                String newPrefix = prefix + chosenChar;

                // 3. Form the new suffix by removing the chosen character.
                //    Combine the part before the chosen character with the part after it.
                String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);

                // 4. Recursively call permuteHelper with the new prefix and suffix.
                permuteHelper(newPrefix, newSuffix, results);
            }
        }
    }

    /**
     * Main method for demonstrating the recursive permutation generator.
     * Takes the string from command line arguments or uses a default.
     * @param args Command line arguments (optional, first arg used as input string).
     */
    public static void main(String[] args) {
        RecursivePermutations generator = new RecursivePermutations();
        String input = "fedcba"; // Default input

        if (args.length > 0) {
            input = args[0];
            System.out.println("Using input string from command line: \"" + input + "\"");
        } else {
            System.out.println("Using default input string: \"" + input + "\"");
        }
        if (input.length() > 8) { // Input size constraint
             System.out.println("Warning: Input string is long, permutation generation might be slow and could lead to StackOverflowError.");
        }


        List<String> permutations = generator.generatePermutationsRecursive(input);

         if (permutations.isEmpty() && input != null && !input.isEmpty()) {
             System.out.println("Permutation generation failed or resulted in an empty list unexpectedly.");
        } else {
             System.out.println("Generated Permutations (" + permutations.size() + "):");
             int count = 1;
             for (String p : permutations) {
                 System.out.println(count + ": " + p);
                 count++;
             }
         }
    }
}
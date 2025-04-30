import java.util.ArrayList;
import java.util.List;

/**
 * Generates all permutations of a given string using an iterative approach.
 * This implementation builds permutations incrementally without using recursion.
 */
public class IterativePermutations {

    /**
     * Generates all unique permutations of the input string iteratively.
     *
     * The algorithm works by incrementally building permutations. It starts with
     * the first character. Then, for each subsequent character, it inserts that
     * character into all possible positions of all permutations generated so far.
     *
     * Example for "abc":
     * 1. Start with "a". Permutations: ["a"]
     * 2. Insert 'b': Insert 'b' into "a" at pos 0 -> "ba", pos 1 -> "ab". Permutations: ["ba", "ab"]
     * 3. Insert 'c':
     *    - Insert 'c' into "ba": "cba", "bca", "bac"
     *    - Insert 'c' into "ab": "cab", "acb", "abc"
     *    Final Permutations: ["cba", "bca", "bac", "cab", "acb", "abc"]
     *
     * @param inputString The string for which to generate permutations.
     * @return A List of strings, each representing a unique permutation of the input.
     *         Returns an empty list if the input is null or empty.
     *         Returns a list with the single character if the input has length 1.
     */
    public List<String> generatePermutations(String inputString) {
        if (inputString == null) {
            System.err.println("Error: Input string cannot be null.");
            return new ArrayList<>(); // Return empty list for null input
        }

        List<String> currentPermutations = new ArrayList<>();

        if (inputString.isEmpty()) {
            // Return list containing the empty string as the only permutation
            currentPermutations.add(""); 
            return currentPermutations;
        }

        // Start with the first character as the initial permutation.
        currentPermutations.add(String.valueOf(inputString.charAt(0)));

        // Iterate through the rest of the characters in the input string.
        for (int i = 1; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            List<String> nextPermutations = new ArrayList<>();

            for (String perm : currentPermutations) {
                // Insert the current character at every possible position.
                for (int insertionPoint = 0; insertionPoint <= perm.length(); insertionPoint++) {
                    // Build the new permutation by inserting currentChar
                    String prefix = perm.substring(0, insertionPoint);
                    String suffix = perm.substring(insertionPoint); // From insertionPoint to end
                    String newPermutation = prefix + currentChar + suffix;
                    nextPermutations.add(newPermutation);
                }
            }
            currentPermutations = nextPermutations;
        }

        return currentPermutations;
    }

    /**
     * Main method for demonstrating the iterative permutation generator.
     * Takes the string from command line arguments or uses a default.
     * @param args Command line arguments (optional, first arg used as input string).
     */
    public static void main(String[] args) {
        IterativePermutations generator = new IterativePermutations();
        String input = "abcdef"; // Default input

        if (args.length > 0) {
            input = args[0];
            System.out.println("Using input string from command line: \"" + input + "\"");
        } else {
            System.out.println("Using default input string: \"" + input + "\"");
        }

        if (input.length() > 8) { // Example constraint: avoid huge output for demo
             System.out.println("Warning: Input string is long, permutation generation might be slow and produce large output.");
             // Consider adding a confirmation step or limiting length further
        }


        List<String> permutations = generator.generatePermutations(input);

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
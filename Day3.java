import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3 {

    String input;

    public Day3(String input) {
        this.input = input;
    }

    public int solve(int part) {
        Scanner scanner = new Scanner(input);
        String token, begin, end;
        int total = 0;

        do {
            token = scanner.nextLine();
            char dup;

            if (part == 1) {
                int half = token.length() / 2;
                begin = token.substring(0, half);
                end = token.substring(half);
                dup = findDuplicate(begin, end);
            } else {
                dup = findTriplicate(token, scanner.nextLine(), scanner.nextLine());
            }
            total += getPrio(dup);

        } while (scanner.hasNextLine());
        scanner.close();

        return total;
    }

    /*
     * The ASCII value of lowercase alphabets are from 97 to 122. And, the ASCII
     * value of uppercase alphabets are from 65 to 90
     * Lowercase item types a through z have priorities 1 through 26.
     * Uppercase item types A through Z have priorities 27 through 52.
     */
    private int getPrio(char dup) {
        if (Character.isUpperCase(dup)) {
            return (int) dup - 38;
        } else {
            return (int) dup - 96;
        }
    }

    private char findDuplicate(String start, String end) {
        List<Character> startChars = start.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        List<Character> endChars = end.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        for (Character char1 : startChars) {
            for (Character char2 : endChars) {
                if (char1 == char2) {
                    return char1;
                }
            }
        }
        return 0;
    }

    char findTriplicate(String group1, String group2, String group3) {
        List<Character> chars1 = group1.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        for (Character character : chars1) {
            if (group2.indexOf(character) != -1 && group3.indexOf(character) != -1) {
                return character;
            }
        }
        return 0;
    }
}

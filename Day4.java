import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day4 {

    String input = "2-4,6-8\n" +
            "2-3,4-5\n" +
            "5-7,7-9\n" +
            "2-8,3-7\n" +
            "6-6,4-6\n" +
            "2-6,4-8";

    public Day4(String input) {
        this.input = input;
    }

    public int solve(int part) {
        Scanner scanner = new Scanner(input);
        String token;
        int total = 0;
        String[] pair, one, two;

        do {
            token = scanner.nextLine();
            pair = token.split(",");

            one = pair[0].split("-");
            two = pair[1].split("-");

            if(part == 1) {
                total += rangeContainsOther(one, two);
            } else {
                total += overlap(one, two);
            }

        } while (scanner.hasNextLine());
        scanner.close();

        return total;
    }

    int rangeContainsOther(String[] one, String[] two) {
        int[] oneInt = new int[one.length], twoInt = new int[two.length];
        convertStringToInt(one, two, oneInt, twoInt);

        if ((oneInt[0] <= twoInt[0] && oneInt[1] >= twoInt[1]) ||
                (twoInt[0] <= oneInt[0] && twoInt[1] >= oneInt[1])) {
            return 1;
        }
        return 0;
    }

    /*
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
     */

    int overlap (String[] one, String[] two) {
        int[] oneInt = new int[one.length], twoInt = new int[two.length];
        convertStringToInt(one, two, oneInt, twoInt);

        if (oneInt[0] > twoInt[1] || oneInt[1] < twoInt[0]) {
            return 0;
        }
        return 1;
    }

    private static void convertStringToInt(String[] one, String[] two, int[] oneInt, int[] twoInt) {
        for (int i = 0; i < one.length; i++) {
            oneInt[i] = Integer.valueOf(one[i]);
            twoInt[i] = Integer.valueOf(two[i]);
        }
    }
}

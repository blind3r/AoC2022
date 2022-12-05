import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    String input;

    public Day1(String input) {
        this.input = input;
    }

    public int solve(int part) {
        Scanner scanner = new Scanner(input);
        String token;

        LinkedList<Integer> elfs = new LinkedList<Integer>();
        List<Integer> calories = new ArrayList<>();

        do {
            token = scanner.nextLine();
            if (token.equals("")) {
                int sum = sumElfCalories(calories);
                elfs.add(sum);
                calories = new ArrayList<>();

            } else {
                calories.add(Integer.valueOf(token));
            }
        } while (scanner.hasNextLine());

        scanner.close();
        Integer[] ordered = elfs.toArray(new Integer[elfs.size()]);
        Arrays.sort(ordered, Collections.reverseOrder());
        if (part == 1) {
            return ordered[0];
        } else {
            return ordered[0] + ordered[1] + ordered[2];
        }

    }

    int sumElfCalories(List<Integer> calories) {
        int sum = 0;
        for (Integer calorie : calories) {
            sum += calorie;
        }
        return sum;
    }
}

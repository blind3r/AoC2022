import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AoC {

    private static final String FILE_PATH = "inputs/";

    public static void main(String[] args) {
        System.out.println("AoC 2022");
/* 
        Day1 day1 = new Day1(parseFile(FILE_PATH + "input1.txt"));
        System.out.println("Day 1 part 1: " + day1.solve(1));
        System.out.println("Day 1 part 2: " + day1.solve(2));

        Day2 day2 = new Day2(parseFile(FILE_PATH + "input2.txt"));
        System.out.println("Day 2 part 1: " + day2.solve(1));
        System.out.println("Day 2 part 2: " + day2.solve(2));

        Day3 day3 = new Day3(parseFile(FILE_PATH + "input3.txt"));
        System.out.println("Day 3 part 1: " + day3.solve(1));
        System.out.println("Day 3 part 2: " + day3.solve(2));

        Day4 day4 = new Day4(parseFile(FILE_PATH + "input4.txt"));
        System.out.println("Day 4 part 1: " + day4.solve(1));
        System.out.println("Day 4 part 2: " + day4.solve(2));

        Day5 day5 = new Day5(parseFile(FILE_PATH + "input5.txt"));
        System.out.println("Day 5 part 1: " + day5.solve(1));
        System.out.println("Day 5 part 2: " + day5.solve(2));

        Day6 day6 = new Day6(parseFile(FILE_PATH + "input6.txt"));
        System.out.println("Day 6 part 1: " + day6.solve(1));
        System.out.println("Day 6 part 2: " + day6.solve(2));
*/
        Day7 day7 = new Day7(parseFile(FILE_PATH + "input7.txt"));
        System.out.println("Day 7 part 1: " + day7.solve(1));
        System.out.println("Day 7 part 2: " + day7.solve(2));
    }

    private static String parseFile(String fileName) {
        String input = null;
        try {
            Path path = Path.of(fileName);
            input = Files.readString(path);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return input;
    }
}
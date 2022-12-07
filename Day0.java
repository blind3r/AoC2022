import java.util.Scanner;

public class Day0 {

    String input;

    public Day0(String input) {
        this.input = input;
    }

    public int solve(int part) {
        Scanner scanner = new Scanner(input);
        String token;
        int total = 0;

        do {
            token = scanner.nextLine();
            //TODO

        } while (scanner.hasNextLine());
        scanner.close();

        return total;
    }
}

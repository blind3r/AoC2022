import java.util.Scanner;

public class Day2 {
    /*
    The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
    plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

    The first column is what your opponent is going to play: A for Rock, B for Paper, and C for Scissors
    The second column must be what you should play in response: X for Rock, Y for Paper, and Z for Scissors.
    */
    int ROCK_VALUE = 1;
    int PAPER_VALUE = 2;
    int SCISSORS_VALUE = 3;

    int LOST = 0;
    int DRAW = 3;
    int WON = 6;
    String input;

    public Day2(String input) {
        this.input = input;
    }

    public int solve(int part) {
        String i = "A Y\n" +
                "B X\n" +
                "C Z";
        Scanner scanner = new Scanner(input);
        String token;
        int score = 0;

        do {
            token = scanner.nextLine();
            char me, them;
            them = token.charAt(0);
            me = token.charAt(2);

            if (part == 1) {
                score += rockPaperScissors(them, me);
            } else {
                score += rockPaperScissors2(them, me);
            }
        } while (scanner.hasNextLine());

        scanner.close();
        return score;
    }

    int rockPaperScissors2(char themOriginal, char meOriginal) {
        int points = 0;

        char them = translate(false, themOriginal);
        char me = translate2(meOriginal);


        switch (them) {
            case ROCK:
                if (me == 'L') {
                    points += LOST + SCISSORS_VALUE;
                } else if (me == 'D') {
                    points += DRAW + ROCK_VALUE;
                } else {
                    points += WON + PAPER_VALUE;
                }
                break;
            case PAPER:
                if (me == 'L') {
                    points += LOST + ROCK_VALUE;
                } else if (me == 'D') {
                    points += DRAW + PAPER_VALUE;
                } else {
                    points += WON + SCISSORS_VALUE;
                }
                break;
            case SCISSORS:
                if (me == 'L') {
                    points += LOST + PAPER_VALUE;
                } else if (me == 'D') {
                    points += DRAW + SCISSORS_VALUE;
                } else {
                    points += WON + ROCK_VALUE;
                }
                break;
        }
        return points;
    }

    //X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"

    static final char ROCK = 'r';
    static final char PAPER = 'p';
    static final char SCISSORS = 's';

    int rockPaperScissors(char themOriginal, char meOriginal) {
        int points = 0;

        char me = translate(true, meOriginal);
        char them = translate(false, themOriginal);

        switch (me) {
            case ROCK:
                points += ROCK_VALUE;
                if (them == ROCK) {
                    points += DRAW;
                } else if (them == PAPER) {
                    points += LOST;
                } else {
                    points += WON;
                }
                break;
            case PAPER:
                points += PAPER_VALUE;
                if (them == ROCK) {
                    points += WON;
                } else if (them == PAPER) {
                    points += DRAW;
                } else {
                    points += LOST;
                }
                break;
            case SCISSORS:
                points += SCISSORS_VALUE;
                if (them == ROCK) {
                    points += LOST;
                } else if (them == PAPER) {
                    points += WON;
                } else {
                    points += DRAW;
                }
                break;
        }
        return points;
    }

    char translate2(char input) {
        char out = 'Z';
        switch (input) {
            case 'X':
                out = 'L';
                break;
            case 'Y':
                out = 'D';
                break;
            case 'Z':
                out = 'W';
                break;
        }
        return out;
    }

    char translate(boolean me, char input) {
        char out = 'Z';
        if (me) {
            switch (input) {
                case 'X':
                    out = ROCK;
                    break;
                case 'Y':
                    out = PAPER;
                    break;
                case 'Z':
                    out = SCISSORS;
                    break;
            }
        } else {
            switch (input) {
                case 'A':
                    out = ROCK;
                    break;
                case 'B':
                    out = PAPER;
                    break;
                case 'C':
                    out = SCISSORS;
                    break;
            }
        }

        return out;
    }
}

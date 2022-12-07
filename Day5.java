import java.util.*;

public class Day5 {

    static final String DEFAULT_INPUT =
                    "    [D]    \n" +
                    "[N] [C]    \n" +
                    "[Z] [M] [P]\n" +
                    " 1   2   3 \n" +
                    "\n" +
                    "move 1 from 2 to 1\n" +
                    "move 3 from 1 to 3\n" +
                    "move 2 from 2 to 1\n" +
                    "move 1 from 1 to 2";
    String input = DEFAULT_INPUT;

    Map<Integer, List<Character>> crates = new HashMap<Integer, List<Character>>();

    public Day5(String input) {
        this.input = input;
    }

    public Day5() {
    }

    public String solve(int part) {
        Scanner scanner = new Scanner(input);
        String token;

        initialCrateStack(scanner);
        printCrates(crates);
        do {
            token = scanner.nextLine();
            System.out.println(token);
            moveCrates(token, part);
            printCrates(crates);
        } while (scanner.hasNextLine());
        scanner.close();

        return printTopCrates();
    }

    String printTopCrates() {
        String topCrates = "";
        for (List<Character> stack: crates.values()) {
            topCrates += getTopCrate(stack, false);
        }
        return topCrates;
    }

    void moveCrates(String token, int part) {
        int quantity, from, to;
        List<Character> fromStack, toStack;

        Scanner scanner = new Scanner(token).useDelimiter("\\D+");
        quantity = scanner.nextInt();
        from = scanner.nextInt();
        to  = scanner.nextInt();

        if(part == 1) {
            for (int i = 0; i < quantity; i++) {
                Character top = getTopCrate(crates.get(from), true);
                addToTop(crates.get(to), top);
            }
        } else {

        }
    }

    void addToTop(List<Character> toStack, Character top) {
        Character c;
        for (int i = 0; i < toStack.size(); i++) {
            c = toStack.get(i);
            if (i == 0 && c != ' ') {
                toStack.add(0, top);
                balanceCrates(toStack.size());
                break;
            } else if (c != ' ') {
                toStack.set(i - 1, top);
                break;
            } else if (i == toStack.size() - 1){
                toStack.set(toStack.size() - 1, top);
                break;
            }
        }
    }

    void balanceCrates(int newSize){
        for (List<Character> crate :crates.values()) {
            if(crate.size() < newSize){
                crate.add(0, ' ');
            }
        }
    }

    Character getTopCrate(List<Character> fromStack, boolean clean) {
        Character c;
        for (int i = 0; i < fromStack.size(); i++) {
            c = fromStack.get(i);
            if (c != ' ') {
                if(clean)
                    fromStack.set(i,' ');
                return c;
            }
        }
        return 0;
    }


    void printCrates(Map<Integer, List<Character>> crates) {
        StringBuilder sb = new StringBuilder();
        int crateIndex = 1;
        for (List<Character> crateStack : crates.values()) {
            sb.append(crateIndex++ + " ");
            for (Character ch : crateStack) {
                sb.append("[" + ch + "] ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    void initialCrateStack(Scanner scanner) {
        String token = scanner.nextLine();
        do {
            parseFloor(token);

            token = scanner.nextLine();
        } while (token.charAt(1) != '1');
        token = scanner.nextLine();
    }

    void parseFloor(String token) {
        for (int i = 1, f = 1; i < token.length(); i += 4, f++) {
            if (crates.get(f) == null) {
                crates.put(f, new ArrayList<Character>());
            }
            crates.get(f).add(token.charAt(i));
        }
    }
}

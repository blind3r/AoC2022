import java.util.*;

public class Day7 {

    String input;

    static final int MAX_SIZE = 100000;
    static final String PROMPT = "$";
    static final String CD = "cd";
    static final String LS = "ls";
    static final String BACK = "..";
    static final String ROOT = "/";
    static final String DIR = "dir";
    MyFile fs;
    MyFile current;
    List<MyFile> directoriesToDelete;

    public Day7(String input) {
        this.input = input;
        fs = new MyFile(ROOT, null);

        current = fs;
        directoriesToDelete = new ArrayList<>();
    }

    public int solve(int part) {
        Scanner scanner = new Scanner(input);
        String token;
        int total = 0;

        do {
            token = scanner.nextLine();

            if (token.startsWith(PROMPT)) {
                handlePrompt(token.substring(2), scanner);
            }

        } while (scanner.hasNextLine());
        scanner.close();

        calculateDirSize(fs);

        if (part == 1) {
            return sumSizeToDelete();
        } else {
            return sizeOfDirToDelete();
        }
    }

    int sizeOfDirToDelete() {
        int spaceAvailable = 70000000 - fs.size;
        int spaceNeeded = 30000000 - spaceAvailable;
        List<Integer> candidates = new ArrayList<>();

        findCandidates(fs, spaceNeeded, candidates);

        Collections.sort(candidates);
        return candidates.get(0);
    }

    void findCandidates(MyFile fs, int space, List<Integer> candidates) {
        if (fs.content != null) {
            for (MyFile f : fs.content.values()) {
                if (f.content != null && f.size >= space) {
                    candidates.add(f.size);
                }
                findCandidates(f, space, candidates);
            }
        }
    }

    int sumSizeToDelete() {
        int sum = 0;
        for (MyFile f : directoriesToDelete) {
            sum += f.size;
        }
        return sum;
    }

    int calculateDirSize(MyFile folder) {
        int size = 0;
        for (MyFile f : folder.content.values()) {
            if (f.content == null) {
                size += f.size;
            } else {
                size += calculateDirSize(f);
            }
        }
        folder.size = size;
        if (size < MAX_SIZE) {
            directoriesToDelete.add(folder);
        }
        return size;
    }

    void handlePrompt(String prompt, Scanner scanner) {
        String[] commands = prompt.split(" ");

        if (commands[0].equals(CD)) {
            changeDirectory(commands[1]);
        } else if (commands[0].equals(LS)) {
            listDirectory(scanner);
        } else {
            System.err.println("Invalid command: " + commands[0]);
        }
    }

    void listDirectory(Scanner scanner) {
        String token;

        do {
            token = scanner.nextLine();
            if (token.startsWith(PROMPT)) {
                handlePrompt(token.substring(2), scanner);
                break;
            }
            String[] out = token.split(" ");
            if (out[0].equals(DIR)) {
                current.content.put(out[1], new MyFile(out[1], current));
            } else {
                current.content.put(out[1], new MyFile(out[1], current, Integer.valueOf(out[0])));
            }

        } while (scanner.hasNextLine());
    }

    void changeDirectory(String where) {
        if (where.equals(ROOT)) {
            current = fs;
        } else if (where.equals(BACK)) {
            goBack();
        } else {
            boolean exists = false;
            for (String name : current.content.keySet()) {
                if (name.equals(where)) {
                    current = current.content.get(name);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                System.out.println("Directory " + where + "does not exist");
            }
        }
    }

    void goBack() {
        if (current.parent != null) {
            current = current.parent;
        }
    }

    class MyFile {
        int size;
        String name;
        Map<String, MyFile> content;
        MyFile parent;

        MyFile(String name, MyFile parent) {
            this.name = name;
            this.parent = parent;
            content = new HashMap<>();
        }

        MyFile(String name, MyFile parent, int size) {
            this.name = name;
            this.parent = parent;
            this.size = size;
        }
    }
}

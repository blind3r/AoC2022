import java.util.Scanner;

public class Day6 {

    String input;
    int BUFFER_SIZE;
    
    public Day6(String input) {
        this.input = input;
    }

    public int solve(int part) {
        Scanner scanner = new Scanner(input);
        String token;
        int marker = 0, bufferIndex = 0;
        

        if (part == 1) {
            BUFFER_SIZE = 4;
        } else {
            BUFFER_SIZE = 14;
        }

        char[] buffer = new char[BUFFER_SIZE];

        do {
            token = scanner.nextLine();
            char[] signal = token.toCharArray();
            for (char c : signal) {
                marker++;
                if (!isInBuffer(c, buffer)) {
                    buffer[bufferIndex++] = c;
                } else {
                    bufferIndex = purgeBuffer(c, buffer);
                }

                if (bufferIndex == BUFFER_SIZE) {
                    break;
                }
            }

        } while (scanner.hasNextLine());
        scanner.close();

        return marker;
    }

    int purgeBuffer(char c, char[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == c) {
                for (int j = 0; j < BUFFER_SIZE - 1 && i + j + 1 < BUFFER_SIZE; j++) {
                    buffer[j] = buffer[i + j + 1];
                }

                break;
            }
        }
        int offset = 0;
        for (char d : buffer) {
            if (d != 0) {
                offset++;
            } else {
                buffer[offset++] = c;
                int z = offset;
                while (z < BUFFER_SIZE - 1) {
                    buffer[z] = 0;
                    z++;
                }

                break;
            }
        }
        return offset;
    }

    boolean isInBuffer(char c, char[] buffer) {
        for (char d : buffer) {
            if (d == c) {
                return true;
            }
        }
        return false;
    }
}

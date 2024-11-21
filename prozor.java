/**
 * https://open.kattis.com/problems/prozor
 */

import java.util.Scanner;
import java.util.stream.Stream;

public class prozor {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String line;
        line = in.nextLine();

        int[] numbers = Stream.of(line.split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = numbers[0];
        int S = numbers[1];
        int K = numbers[2];
        int k = K - 2; // remove borders

        char[][] marinsWindow = new char[R][S];

        for (int r = 0; r < R; r++) {
            line = in.nextLine();
            for (int s = 0; s < S; s++) {
                marinsWindow[r][s] = line.charAt(s);
            }
        }

        int maxCount = 0;
        int maxR = 0;
        int maxS = 0;
        for (int r = 1; r < R - 1; r++) {
            for (int s = 1; s < S - 1; s++) {
                int count = 0;
                // System.out.print("(");
                for (int i = r; i < r + k; i++) {
                    if (i > R - 2)
                        break;
                    // System.out.print("(");
                    for (int j = s; j < s + k; j++) {
                        if (j > S - 2)
                            break;
                        // System.out.print(marinsWindow[i][j]);
                        if (marinsWindow[i][j] == '*')
                            count++;
                    }
                    // System.out.print(")");
                }
                // System.out.println(")");


                if (count > maxCount) {
                    maxCount = count;
                    maxR = r;
                    maxS = s;
                }
            }
            // System.out.println();
        }

        System.out.println(maxCount);
        for (int r = 0; r < R; r++) {
            for (int s = 0; s < S; s++) {
                if (r + 1 == maxR && s + 1 == maxS) { // top left corner
                    System.out.print("+");
                } else if (r >= maxR && r < maxR + K - 2 && s + 1 == maxS) { // left border
                    System.out.print("|");
                } else if (r + 1 == maxR && s >= maxS && s < maxS + K - 2) { // top border
                    System.out.print("-");
                } else if (r == maxR + K - 2 && s + 1 == maxS) { // bottom left corner
                    System.out.print("+");
                } else if (r == maxR + K - 2 && s >= maxS && s < maxS + K - 2) { // bottom border
                    System.out.print("-");
                } else if (r + 1 == maxR && s - K + 2 == maxS) { // top right corner
                    System.out.print("+");
                } else if (r >= maxR && r < maxR + K - 2 && s - K + 2 == maxS) { // right border
                    System.out.print("|");
                } else if (r - K + 2 == maxR && s - K + 2 == maxS) { // bottom right corner
                    System.out.print("+");
                } else {
                    System.out.print(marinsWindow[r][s]);
                }
            }
            System.out.println();
        }
        in.close();

    }
}

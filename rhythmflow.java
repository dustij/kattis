import java.util.Arrays;
import java.util.Scanner;

public class rhythmflow {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] e = new int[n];
    int[] a = new int[m];

    // Expected
    for (int i = 0; i < n; i++) {
      e[i] = sc.nextInt();
    }

    // Actual
    for (int i = 0; i < m; i++) {
      a[i] = sc.nextInt();
    }

    // FIXME: if there is 1 actual but more than 1 expected, not allowed to sum scores across row

    // Populate scores
    int[][] scores = new int[m][n];
    for (int row = 0; row < m; row++) {
      boolean flagged = false;
      for (int col = 0; col < n; col++) {
        if (flagged)
          break;

        int score = getScore(Math.abs(e[col] - a[row]));

        if (row - 1 < 0) { // This is fist row
          // If the previous is same or higher then continue
          if (col - 1 < 0) {
            scores[row][col] = score;
            continue;
          } else {
            if (scores[row][col - 1] >= score) {
              flagged = true;
            }
          }
        } else {
          if (score > scores[row - 1][col]) {
            scores[row][col] = score;
            flagged = true;
          } else {
            scores[row][col] = scores[row - 1][col];
          }
        }

      }
    }

    int finalScore = 0;
    if (scores.length == 1) {
      finalScore = Arrays.stream(scores[0]).max().getAsInt();
    } else {
      finalScore = Arrays.stream(scores[scores.length - 1]).sum();
    }

    System.out.println(finalScore);
    return; // just a breakpoint anchor

  }

  static int getScore(int a) {
    if (0 <= a && a <= 15)
      return 7;
    if (15 < a && a <= 23)
      return 6;
    if (23 < a && a <= 43)
      return 4;
    if (43 < a && a <= 102)
      return 2;
    return 0;
  }

}

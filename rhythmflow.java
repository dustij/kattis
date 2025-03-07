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

    // Populate scores
    int[][] scores = new int[m][n];
    for (int row = 0; row < m; row++) {
      boolean flagged = false;
      for (int col = 0; col < n; col++) {
        if (flagged)
          break;

        int score = getScore(Math.abs(e[col] - a[row]));

        if (row - 1 < 0) { // This is fist row
          if (col - 1 < 0) { // This is the first col in the first row
            scores[row][col] = score; // Just save it and continue
            continue;
          } else { // This is all other cols in the first row
            if (scores[row][col - 1] >= score) { // If score is going down, flag to move to next row
              // note: should I use ">=" here?
              flagged = true;
            } else { // Save score
              scores[row][col] = score;
            }
          }
        } else { // This is all rows after the first
          if (score > scores[row - 1][col]) {
            // If this is a better score for the col, save it and move to next row (flag)
            scores[row][col] = score;
            flagged = true;
          } else {
            // Take the better score from the cell above
            scores[row][col] = scores[row - 1][col];
            // This here, do if we know is optimal later, remapping scores lower to gain net +1
            // i.e. 6-7=-1 6-4=2, thus net +1
            // for the row above, col 1 greater to the end, 0 out
            // for (int c = col + 1; c < n; c++) {
            // scores[row - 1][c] = 0;
            // }
            // Actualy, it might be good to look at diagnols, the abs diff between current cell and
            // the cell up and to the left, vs. cell to the right and cell above
            // 1 0 0 || 0 1 0
            // 0 1 0 || 0 0 1
          }
        }

      }
    }

    int finalScore = 0;
    if (scores.length == 1) { // There was one actual (only 1 row)
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

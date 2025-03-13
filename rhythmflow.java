import java.util.Scanner;

public class rhythmflow {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] e = new int[n]; // goal times
    int[] a = new int[m]; // user times

    // Expected
    for (int i = 0; i < n; i++) {
      e[i] = sc.nextInt();
    }

    // Actual
    for (int i = 0; i < m; i++) {
      a[i] = sc.nextInt();
    }

    // DP table
    int[][] dp = new int[n + 1][m + 1];

    // Fill DP table
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= m; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);

        int diff = Math.abs(e[i - 1] - a[j - 1]);

        if (diff <= 102) {
          int score = getScore(diff);
          // Take max of either adding this score to diagonal or keeping current max
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + score);
        }
      }
    }

    System.out.println(dp[n][m]);
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

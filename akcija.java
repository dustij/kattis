import java.util.Arrays;
import java.util.Scanner;

public class akcija {
  public static void main(String[] args) {
    var input = new Scanner(System.in);
    var N = input.nextInt();
    var prices = new int[N];
    var total = 0;

    for (int i = 0; i < N; i++) {
      prices[i] = input.nextInt();
      total += prices[i];
    }

    Arrays.sort(prices);

    for (int i = N - 1; i >= 0; i -= 3) {
      var j = i - 1;
      var k = i - 2;
      if (k < 0) {
        break;
      }

      total -= Math.min(Math.min(prices[j], prices[k]), prices[i]);
    }

    System.out.println(total);
  }
}

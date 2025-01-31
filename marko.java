
/**
 * https://open.kattis.com/problems/marko
 */

import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class marko {
  private static final Map<Integer, Set<Character>> T9_MAP = Map.of(
      2, Set.of('a', 'b', 'c'),
      3, Set.of('d', 'e', 'f'),
      4, Set.of('g', 'h', 'i'),
      5, Set.of('j', 'k', 'l'),
      6, Set.of('m', 'n', 'o'),
      7, Set.of('p', 'q', 'r', 's'),
      8, Set.of('t', 'u', 'v'),
      9, Set.of('w', 'x', 'y', 'z'));

  public static void main(String[] args) {
    var input = new Scanner(System.in);

    var N = Integer.parseInt(input.nextLine());
    var words = new String[N];
    for (int i = 0; i < N; i++) {
      words[i] = input.nextLine();
    }

    var S = input.nextLine();
    var digits = extractDigits(S);

    var possibilities = N;
    for (int i = 0; i < N; i++) {
      // Word length must equal S length
      if (words[i].length() != digits.length) {
        possibilities--;
        continue;
      }

      // Each char in word must map to digit
      for (int j = 0; j < digits.length; j++) {
        if (!T9_MAP.get(digits[j]).contains(words[i].charAt(j))) {
          possibilities--;
          break;
        }
      }
    }

    // Answer
    System.out.println(possibilities);
    input.close();
  }

  private static int[] extractDigits(String s) {
    var digits = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      digits[i] = Integer.parseInt(String.valueOf(s.charAt(i)));
    }
    return digits;
  }
}

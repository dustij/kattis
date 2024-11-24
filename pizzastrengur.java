/**
 * Today is game day at Tommi's Pizzas, so Georg and his friends are going to order some pizzas.
 * The game starts with the employees at Tommi's Pizzas choosing a secret passcode, consisting only of the letters {"P", "I", "Z", "A"},
 * and telling the customer the length of the passcode. If Georg and his friends manage to guess the passcode in at most m attempts,
 * they get their pizza for free!
 * 
 * Georg and his friends may pick words shorter than the passcode as guesses, in which case they will be told whether the guess is a prefix of the passcode.
 * The goal is to help Georg and his friends in guessing the passcode, so they can get their pizza for free.
 * 
 * A word A is considered a prefix of a word B if B starts with A. For example, "PIZ" is a prefix of "PIZZA", but "ZZA" is not a prefix of "PIZZA".
 * 
 * <h2>Interactivity</h2>
 * This is an interactive problem. Your solution will be tested against an interactive judge which reads the output of your solution
 * and prints the input your solution receives. The interaction follows these rules:
 * 
 * <ol>
 *   <li>The judge starts by printing an integer n (1 <= n <= 10^4), which represents the length of the passcode S. The passcode only contains the letters {"P", "I", "Z", "A"}.</li>
 *   <li>Next, your solution must output a guess string P which must contain at least 1 letter and at most n letters.</li>
 *   <li>After this, the judge will respond as follows:</li>
 *   <ul>
 *     <li>If P is not a prefix of S, the judge will print 0.</li>
 *     <li>If P is a prefix of S, the judge will print 1.</li>
 *     <li>If P is equal to the passcode, the judge will print 2 and your program should stop guessing.</li>
 *   </ul>
 * </ol>
/**
 * Today is game day at Tommi's Pizzas, so Georg and his friends are going to order some pizzas.
 * The game starts with the employees at Tommi's Pizzas choosing a secret passcode, consisting only of the letters {"P", "I", "Z", "A"},
 * and telling the customer the length of the passcode. If Georg and his friends manage to guess the passcode in at most m attempts,
 * they get their pizza for free!
 * 
 * Georg and his friends may pick words shorter than the passcode as guesses, in which case they will be told whether the guess is a prefix of the passcode.
 * The goal is to help Georg and his friends in guessing the passcode, so they can get their pizza for free.
 * 
 * A word A is considered a prefix of a word B if B starts with A. For example, "PIZ" is a prefix of "PIZZA", but "ZZA" is not a prefix of "PIZZA".
 * 
 * Interactivity
 * This is an interactive problem. Your solution will be tested against an interactive judge which reads the output of your solution
 * and prints the input your solution receives. The interaction follows these rules:
 * 
 * The judge starts by printing an integer n (1 <= n <= 10^4), which represents the length of the passcode S. The passcode only contains the letters {"P", "I", "Z", "A"}.
 * Next, your solution must output a guess string P which must contain at least 1 letter and at most n letters.
 * After this, the judge will respond as follows:
 * - If P is not a prefix of S, the judge will print 0.
 * - If P is a prefix of S, the judge will print 1.
 * - If P is equal to the passcode, the judge will print 2 and your program should stop guessing.
 * 
 * Make sure to flush after each guess. For example:
 * - In Python: print(..., flush=True)
 * - In C++: cout << ... << endl;
 * - In Java: System.out.flush();
 * 
 * The problem comes with a testing tool to help test your solution.
 * 
 * Scoring
 * Group  | Points | Constraints
 * -------|--------|------------
 * 1      | 10     | 1 <= n <= 10^3, m = 4 * n
 * 2      | 25     | 1 <= n <= 10^3, m = 3 * n + 1
 * 3      | 25     | n = 10^3, m = 2.75 * n
 * 4      | 40     | n = 10^3, m = 2.45 * n
 */

 // Version 3
// import java.util.Scanner;

// public class pizzastrengur {
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         StringBuilder sb = new StringBuilder();

//         int n = in.nextInt();
//         while (sb.length() < n) {
//             System.out.println(sb.toString() + 'P');
//             System.out.flush();
//             int res = in.nextInt();
//             if (res == 0) {
//                 System.out.println(sb.toString() + 'I');
//                 System.out.flush();
//                 res = in.nextInt();
//                 if (res == 0) {
//                     System.out.println(sb.toString() + 'Z');
//                     System.out.flush();
//                     res = in.nextInt();
//                     if (res == 0) {
//                         sb.append('A');
//                         if (sb.length() == n) {
//                             System.out.println(sb.toString());
//                             System.out.flush();
//                             break;
//                         } else {
//                             continue;
//                         }

//                     } else if (res == 1) {
//                         sb.append('Z');
//                         continue;
//                     } else if (res == 2) {
//                         break;
//                     }

//                 } else if (res == 1) {
//                     sb.append('I');
//                     continue;
//                 } else if (res == 2) {
//                     break;
//                 }

//             } else if (res == 1) {
//                 sb.append('P');
//                 continue;
//             } else if (res == 2) {
//                 break;
//             }
//         }
//     }
// }

 // Version 2

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

 public class pizzastrengur {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] choices = {'P', 'I', 'Z', 'A'};
        Random rand = new Random();

        int length = in.nextInt(); // Length of the passcode

        while (sb.length() < length) {
            // Shuffle choices for randomness
            List<Character> shuffledChoices = new ArrayList<>();
            for (char c : choices) shuffledChoices.add(c);
            Collections.shuffle(shuffledChoices, rand);

            for (char choice : shuffledChoices) {
                sb.append(choice);

                // Make guess
                System.out.println(sb.toString());
                System.out.flush();

                // Judges response
                int res = in.nextInt();

                if (res == 2) {
                    // We did it!
                    return;
                } else if (res == 1) {
                    // This is a valid prefix, moving on
                    break;
                } else {
                    // This ain't right, remove the last character and try again
                    sb.deleteCharAt(sb.length() - 1);
                }
            }

        }

    }
 }


// Version 1

// import java.util.Scanner;

// public class pizzastrengur {
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         StringBuilder sb = new StringBuilder();
//         char[] choices = {'P', 'I', 'Z', 'A'};

//         int length = in.nextInt();
//         for (int i = 0; i < length; i++) {
//             int guessCount = 0; // only allow 3 guess per character, don't need to guess the last
//                                 // because obvisouly that is correct, just move on to next prefix
//             for (char choice : choices) {
//                 if (guessCount == 3) {
//                     sb.append(choice);
//                     if (sb.length() == length) {
//                         System.out.println(sb.toString());
//                         return;
//                     }
//                     break;
//                 }

//                 sb.append(choice);
//                 System.out.println(sb.toString());
//                 int res = in.nextInt(); 
//                 guessCount++;

//                 if (res == 2) {
//                     // stop guessing
//                     return;
//                 } else if (res == 1) {
//                     // valid prefix
//                     break;
//                 } else {
//                     // invalid prefix
//                     sb.deleteCharAt(sb.length() - 1);
//                 }
//             }
//         }
//     }
// }

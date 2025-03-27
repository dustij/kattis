import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;

public class twentyfour {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("inputs/twentyfour/1.in"))) {
            // Scanner sc = new Scanner(System.in);
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            int D = sc.nextInt();
            int[] operands = {A, B, C, D};

            // Fast look up operators
            HashMap<Character, BiFunction<Integer, Integer, Integer>> opmap = new HashMap<>();
            opmap.put('0', (a, b) -> a + b);
            opmap.put('1', (a, b) -> a - b);
            opmap.put('2', (a, b) -> a * b);
            opmap.put('3', (a, b) -> a / b);

            // Given four operators (+, -, *, /) and three slots
            // there is a total of 4^3 = 64 combos
            for (int base4 = 0; base4 < 64; base4++) {

                // Base 4 represented operator combo, (ex. '001' -> '++-')
                String opcombo = Integer.toString(base4, 4);
                opcombo = String.format("%3s", opcombo).replace(' ', '0');
                System.out.println(opcombo);

                // Permute operands (4! = 24 different ways) using Heap’s Algorithm
                int[] c = new int[4];

                System.out.println(Arrays.toString(operands)); // Print initial permutation

                int i = 0;
                while (i < 4) {
                    if (c[i] < i) {
                        if (i % 2 == 0) {
                            swap(operands, 0, i);
                        } else {
                            swap(operands, c[i], i);
                        }

                        System.out.println(Arrays.toString(operands)); // Print new permutation

                        // Do math
                        int acc = 0;
                        for (int j = 0; j < 3; j++) {
                            // Division can only be used if the result is an integer
                            if (opcombo.charAt(j) == '3' && acc % operands[j + 1] != 0)
                                break;

                            acc = opmap
                                    .get(opcombo.charAt(j))
                                    .apply(acc, operands[j + 1]);
                        }

                        System.out.println("    = " + acc); // Print math outcome

                        c[i]++;
                        i = 0;
                    } else {
                        c[i] = 0;
                        i++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file found");
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

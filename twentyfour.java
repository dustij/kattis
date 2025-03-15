import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;

public class twentyfour {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();
        int D = sc.nextInt();

        // Fast look up operators
        HashMap<Character, BiFunction<Integer, Integer, Integer>> opmap = new HashMap<>();
        opmap.put('0', (a, b) -> a + b);
        opmap.put('1', (a, b) -> a - b);
        opmap.put('2', (a, b) -> a * b);
        opmap.put('3', (a, b) -> a / b);

        // Given three slots for each operator
        // (A+B+C+D has three addition operators)
        // and given four operators (+, -, *, /)
        /// there are a total of 4^3=64 arrangements
        for (int i = 0; i < 64; i++) {

            //// TODO: permute operand positions (4! = 24 different ways)
            for (int j = 0; j < 24; j++) {
            }
            //// end TODO

            // Base 4 representation of operator combo
            // example: 001 -> +, +, -
            String opcombo = Integer.toString(i, 4);
            opcombo = String.format("%3s", opcombo).replace(' ', '0');

            //// TODO: REMOVE LATER (testing)
            A = 1;
            B = 1;
            C = 15;
            D = 5;
            int[] operands = {A, B, C, D};

            // A + B * C / D
            opcombo = "023";

            // Should equal 6
            int acc = operands[0];
            for (int k = 0; k < 3; k++) {
                acc = opmap.get(opcombo.charAt(k)).apply(acc, operands[k + 1]);
            }
            System.out.println("Test answer = " + acc);
            //// end TODO
        }
    }
}

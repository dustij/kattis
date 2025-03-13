import java.util.Scanner;

public class oddities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of tests
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        // outputs
        for (int num : numbers) {
            if (num % 2 == 0)
                System.out.printf("%d is even%n", num);
            else {
                System.out.printf("%d is odd%n", num);
            }
        }
    }
}

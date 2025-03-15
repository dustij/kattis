import java.util.PriorityQueue;
import java.util.Scanner;

public class bikesandbarricades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] barricades = new int[n][4];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < 4; col++) {
                barricades[row][col] = sc.nextInt();
            }
        }

        PriorityQueue<Double> queue = new PriorityQueue<>();

        for (int row = 0; row < n; row++) {
            double x1 = (double) barricades[row][0];
            double y1 = (double) barricades[row][1];
            double x2 = (double) barricades[row][2];
            double y2 = (double) barricades[row][3];

            double m = (y2 - y1) / (x2 - x1); // slope
            double b = y1 - m * x1; // y-intercept

            // Does this line cross the positive y-axis?
            if (b > 0)
                if ((x1 >= 0 && x2 <= 0) || (x1 <= 0 && x2 >= 0))
                    queue.offer(b);
        }

        if (queue.isEmpty()) {
            System.out.println(-1.0);
            return;
        }

        System.out.println(queue.poll());
    }
}

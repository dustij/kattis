import java.util.ArrayList;
import java.util.Arrays;
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

        // Is there a barricade on possitve y-axis?
        boolean isBarricade = false;
        ArrayList<Double> listOfYs = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            double x1 = (double) barricades[row][0];
            double y1 = (double) barricades[row][1];
            double x2 = (double) barricades[row][2];
            double y2 = (double) barricades[row][3];

            // double Y = y1 + ((x1 * x1) - (x1 * x2)) / (y2 - y1);
            double Y = (x1 * x2 - x1 * x1 - y1 * y2 + y1 * y1) / (x2 - x1);

            if (Y > 0) {
                isBarricade = true;
                listOfYs.add(Y);
            }
        }

        // Quit if there isn't
        if (!isBarricade) {
            System.out.println("-1.0");
            return;
        }

        double min = Double.MAX_VALUE;
        for (int i = 0; i < listOfYs.size(); i++) {
            if (listOfYs.get(i) < min) {
                min = listOfYs.get(i);
            }
        }

        System.out.println(min);
    }
}

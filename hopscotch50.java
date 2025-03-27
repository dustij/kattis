import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class hopscotch50 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();



        ArrayList<ArrayList<Integer[]>> coordGrid = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            coordGrid.add(new ArrayList<>());

        }

        boolean[] exists = new boolean[k];

        int[][] arr = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int num = sc.nextInt();
                arr[row][col] = num;
                exists[num - 1] = true;
                coordGrid.get(num - 1).add(new Integer[] {row, col});
            }
        }


        for (boolean b : exists) {
            if (!b) {
                System.out.println(-1);
                return;
            }
        }

        int acc = 0;
        int min = Integer.MAX_VALUE;
        Integer[] bestP = null;

        // init bestP from k-2 -> k-1
        for (var p1 : coordGrid.get(k - 2)) {
            for (var p2 : coordGrid.get(k - 1)) {
                var d = getDistance(p1, p2);
                if (d < min) {
                    min = d;
                    bestP = p1;
                }
            }
        }
        acc += min;
        //
        for (int i = k - 3; i >= 0; i--) {
            min = Integer.MAX_VALUE;
            Integer[] nextP = null;
            for (var p1 : coordGrid.get(i)) {
                var d = getDistance(p1, bestP);
                if (d < min) {
                    nextP = p1;
                    min = d;
                }
            }
            bestP = nextP;
            acc += min;
        }

        // find best last move
        // for (int i = k; i >= 2; i--) {
        // Integer[] nextP = null;

        // for (var p1 : coordGrid.get(i - 2)) {
        // if (bestP == null) {
        // for (var p2 : coordGrid.get(i - 1)) {
        // var d = getDistance(p1, p2);
        // if (d < min) {
        // min = d;
        // bestP = p1;
        // }
        // }
        // } else {
        // var d = getDistance(p1, bestP);
        // if (d < min) {
        // min = d;
        // nextP = p1;
        // }
        // }
        // }

        // bestP = nextP;
        // acc += min;
        // min = Integer.MAX_VALUE;
        // }

        System.out.println(acc);

    }

    public static int getDistance(Integer[] p1, Integer[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}

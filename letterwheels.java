import java.util.Scanner;

public class letterwheels {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] top = sc.nextLine().toCharArray();
        char[] mid = sc.nextLine().toCharArray();
        char[] btm = sc.nextLine().toCharArray();

        int k = top.length;

        int totalShifts = 0;
        int shift = 0;

        // for each char does top and mid match
        for (int i = 0; i < k; i++) {
            if (top[(i + shift) % k] == mid[i]) {
                i = 0;
                if ((shift + 1) % k == 0) {
                    System.out.println(-1);
                    System.exit(-1);
                }
                shift++;
            }
        }

        // solved the first two layers
        totalShifts = shift;
        shift = k;

        int count = 0;

        // now solve bottom two layers
        for (int i = 0; i < k; i++) {
            if (btm[(i + shift) % k] == mid[i]) {
                i = 0;
                if ((shift - 1) % k == 0) {
                    System.out.println(-1);
                    System.exit(-1);
                }
                shift--;
                count++;
            }
        }

        System.out.println(totalShifts + count);

    }


}

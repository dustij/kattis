import java.util.Scanner;

public class lightup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        char[][] tiles = new char[n][n];
        for (int i = 0; i < n; i++) {
            var line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                tiles[i][j] = line.charAt(j);
            }
        }

        // check her out
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                switch (tiles[row][col]) {
                    case '?':
                        for (int k = col + 1; k < n; k++) {
                            if (tiles[row][k] == '?')
                                outputInvalid();
                            if (tiles[row][k] != '.')
                                break;
                        }
                        break;

                    case 'X':
                        break;

                    case '0', '1', '2', '3', '4':
                        int count = 0;

                        if (col - 1 >= 0 && tiles[row][col - 1] == '?')
                            count++;
                        if (col + 1 < n && tiles[row][col + 1] == '?')
                            count++;
                        if (row - 1 >= 0 && tiles[row - 1][col] == '?')
                            count++;
                        if (row + 1 < n && tiles[row + 1][col] == '?')
                            count++;
                        if (count != (int) (tiles[row][col] - '0'))
                            outputInvalid();
                        break;

                    case '.':
                        boolean isIlluminated = false;
                        // check the row forward
                        for (int c = col + 1; c < n && !isIlluminated; c++) {
                            isIlluminated = tiles[row][c] == '?';
                            if (tiles[row][c] != '.')
                                break;
                        }

                        // check the row backward
                        for (int c = col - 1; c >= 0 && !isIlluminated; c--) {
                            isIlluminated = tiles[row][c] == '?';
                            if (tiles[row][c] != '.')
                                break;
                        }

                        // check col forward
                        for (int r = row + 1; r < n && !isIlluminated; r++) {
                            isIlluminated = tiles[r][col] == '?';
                            if (tiles[r][col] != '.')
                                break;
                        }

                        // check col backward
                        for (int r = row - 1; r >= 0 && !isIlluminated; r--) {
                            isIlluminated = tiles[r][col] == '?';
                            if (tiles[r][col] != '.')
                                break;
                        }

                        if (!isIlluminated)
                            outputInvalid();
                        break;

                    default:
                        break;
                }
            }
        }


        // valid
        System.out.println("1");
    }

    public static void outputInvalid() {
        System.out.println("0");
        System.exit(0);
    }
}

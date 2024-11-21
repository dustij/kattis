import java.util.Scanner;

public class pizzastrengur
{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] choices = {'P', 'I', 'Z', 'A'};
        int length = input.nextInt();
        for (int i = 0; i < length; i++) {
            for (char choice : choices) {
                String guess = sb.toString() + choice;
                System.out.println(guess);
                int res = input.nextInt();
                // stop guessing
                if (res == 2) {
                    return;
                }
                // is prefix
                if (res == 1) {
                    sb.append(choice);
                    break;
                }
            }
        }
        input.close();
    }
}
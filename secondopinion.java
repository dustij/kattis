import java.util.Scanner;

public class secondopinion {
  public static void main(String[] args) {
    var input = new Scanner(System.in);
    var s = input.nextInt();
    var h = s / (60 * 60);
    s %= 60 * 60;
    var m = s / 60;
    s %= 60;
    System.out.println(h + " : " + m + " : " + s);
  }
}
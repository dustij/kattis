import java.util.Scanner;

public class tri
{
  public static void main(String[] args)
  {
    Scanner in=new Scanner(System.in);
    String[] s=in.nextLine().split(" ");

    int[] n=new int[3];
    for (int i=0; i<3; i++)
    {
      n[i]=Integer.parseInt(s[i]);
    }

    // Try adding
    if (n[0]+n[1]==n[2]) System.out.printf("%d+%d=%d%n", n[0], n[1], n[2]);
    else if (n[0]==n[1]+n[2]) System.out.printf("%d=%d+%d%n", n[0], n[1], n[2]);
    // Try subtracting
    else if (n[0]-n[1]==n[2]) System.out.printf("%d-%d=%d%n", n[0], n[1], n[2]);
    else if (n[0]==n[1]-n[2]) System.out.printf("%d=%d-%d%n", n[0], n[1], n[2]);
    // Try multiplying
    else if (n[0]*n[1]==n[2]) System.out.printf("%d*%d=%d%n", n[0], n[1], n[2]);
    else if (n[0]==n[1]*n[2]) System.out.printf("%d=%d*%d%n", n[0], n[1], n[2]);
    // Try dividing
    else if (n[0]/n[1]==n[2]) System.out.printf("%d/%d=%d%n", n[0], n[1], n[2]);
    else if (n[0]==n[1]/n[2]) System.out.printf("%d=%d/%d%n", n[0], n[1], n[2]);
  }
}

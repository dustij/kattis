import java.util.Scanner;

public class timeloop
{
  public static void main(String[] args)
  {
    Scanner in=new Scanner(System.in);
    final int n=in.nextInt();
    for (int i=1; i<=n; i++)
    {
      System.out.println(i+" Abracadabra");
    }
  }
}

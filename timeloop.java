import java.util.Scanner;

public class timeloop
{
  public static void main(String[] args)
  {
    Scanner input=new Scanner(System.in);

    final int n=input.nextInt();
    for (int i=1; i<=n; i++)
    {
      System.out.println(i+" Abracadabra");
    }
  }
}

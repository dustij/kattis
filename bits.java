import java.util.Scanner;

public class bits
{
  public static void main(String[] args)
  {
    Scanner in=new Scanner(System.in);
    int T=in.nextInt();

    for (int i=0; i<T; i++)
    {
      int X=in.nextInt();
      int max=countOnes(X);
      while (X>0)
      {
        X/=10;
        int next=countOnes(X);
        if (next>max) max=next;
      }
      System.out.println(max);
    }
  }

  private static int countOnes(int x)
  {
    int count=0;
    while (x>0)
    {
      if ((x&1)==1) count++;
      x>>=1;
    }
    return count;
  }
}

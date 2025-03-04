package templates;

public class BitOperations
{
  public static void main(String[] args)
  {
    int x=123;
    System.out.println(x+" in binary is:");
    System.out.println(Integer.toBinaryString(x));
    System.out.println("Count 1s:");
    System.out.println(Integer.bitCount(x));

    System.out.println("Iterating from radix point");
    int y=x;
    for (int i=0; i<3; i++)
    {
      System.out.println(y%10);
      y/=10;
    }
  }
}

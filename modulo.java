import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class modulo
{
  public static void main(String[] args)
  {
    Scanner in=new Scanner(System.in);
    Set<Integer> set=new HashSet<>();

    for (int i=0; i<10; i++)
    {
      set.add(in.nextInt()%42);
    }

    System.out.println(set.size());
  }
}

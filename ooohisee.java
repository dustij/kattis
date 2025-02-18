import java.util.ArrayList;
import java.util.Scanner;

public class ooohisee
{
  public static void main(String[] args)
  {
    Scanner in=new Scanner(System.in);
    String[][] grid=new String[in.nextInt()][in.nextInt()];
    in.nextLine(); // eat nl

    // Make grid
    for (int r=0; r<grid.length; r++)
    {
      grid[r]=in.nextLine().split("");
    }

    // Search
    ArrayList<int[]> locs=new ArrayList<>();
    for (int r=1; r<grid.length-1; r++) // treasure can't be on edge
    {
      for (int c=1; c<grid[r].length-1; c++)
      {
        if (grid[r][c].equals("0"))
        {
          // Check top
          if (!grid[r-1][c-1].equals("O")) continue;
          if (!grid[r-1][c].equals("O")) continue;
          if (!grid[r-1][c+1].equals("O")) continue;
          // Check left
          if (!grid[r-1][c-1].equals("O")) continue;
          if (!grid[r][c-1].equals("O")) continue;
          if (!grid[r+1][c-1].equals("O")) continue;
          // Check right
          if (!grid[r-1][c+1].equals("O")) continue;
          if (!grid[r][c+1].equals("O")) continue;
          if (!grid[r+1][c+1].equals("O")) continue;
          // Check bottom
          if (!grid[r+1][c-1].equals("O")) continue;
          if (!grid[r+1][c].equals("O")) continue;
          if (!grid[r+1][c+1].equals("O")) continue;

          locs.add(new int[] {r, c});
        }
      }
    }

    if (locs.size()<1) System.out.println("Oh no!");
    else if (locs.size()>1) System.out.printf("Oh no! %d locations\n", locs.size());
    else System.out.printf("%d %d\n", locs.get(0)[0]+1, locs.get(0)[1]+1);
  }
}

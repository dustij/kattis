import java.util.Scanner;

public class amoebas
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

    int count=0;
    for (int r=0; r<grid.length; r++)
    {
      for (int c=0; c<grid[r].length; c++)
      {
        if ("#".equals(grid[r][c]))
        {
          count++;
          consume(new int[] {r, c}, grid);
        }
      }
    }
    System.out.println(count);
  }

  private static void consume(int[] curr, String[][] grid)
  {
    // Find next #
    int[] next=findNext(curr, grid);
    // If no adjacent # then return
    if (next==null) return;
    // Consume for the next #
    consume(next, grid);

  }

  private static int[] findNext(int[] curr, String[][] grid)
  {
    int r=curr[0];
    int c=curr[1];

    // Check top
    if (r-1>=0) // Prevent out of bounds
    {
      if (c-1>=0&&"#".equals(grid[r-1][c-1])) // top left
      {
        grid[r-1][c-1]=".";
        return new int[] {r-1, c-1};
      }
      if ("#".equals(grid[r-1][c])) // top center
      {
        grid[r-1][c]=".";
        return new int[] {r-1, c};
      }
      if (c+1<grid[r].length&&"#".equals(grid[r-1][c+1])) // top right
      {
        grid[r-1][c+1]=".";
        return new int[] {r-1, c+1};
      }
    }
    // Check right
    if (c-1>=0&&"#".equals(grid[r][c-1]))
    {
      grid[r][c-1]=".";
      return new int[] {r, c-1};
    }
    // Check bottom
    if (r+1<grid.length)
    {
      if (c-1>=0&&"#".equals(grid[r+1][c-1]))
      {
        grid[r+1][c-1]=".";
        return new int[] {r+1, c-1};
      }
      if ("#".equals(grid[r+1][c]))
      {
        grid[r+1][c]=".";
        return new int[] {r+1, c};
      }
      if (c+1<grid[r].length&&"#".equals(grid[r+1][c+1]))
      {
        grid[r+1][c+1]=".";
        return new int[] {r+1, c+1};
      }
    }
    // Check left
    if (c+1<grid[r].length&&"#".equals(grid[r][c+1]))
    {
      grid[r][c+1]=".";
      return new int[] {r, c+1};
    }
    return null;
  }
}

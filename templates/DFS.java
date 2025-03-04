package templates;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Point;

public class DFS
{
  public static void main(String[] args)
  {
    Scanner in = new Scanner(System.in);
    int rows = in.nextInt();
    int cols = in.nextInt();
    Object[][] G = new Object[rows][cols]; // Change Object to type

    // Read input into graph G

    dfs(G, new Point(0, 0));
  }

  public static void dfs(Object[][] G, Point p)
  {
    HashSet<Point> visited = new HashSet<>();
    Stack<Point> stack = new Stack<>();
    stack.push(p);

    while (!stack.isEmpty())
    {
      Point p0 = stack.pop();
      if (!visited.contains(p0))
      {
        visit(p0, G);
        visited.add(p0);
        for (Point p1 : neighborsOf(p0, G))
        {
          if (p1 != null && !visited.contains(p1)) stack.add(p1);
        }
      }
    }

  }

  public static void visit(Point p, Object[][] G)
  {
    // Visit logic here
  }

  public static Point[] neighborsOf(Point p, Object[][] G)
  {
    int rows = G.length;
    int cols = G[0].length;
    int[][] deltas = {
        {1, 0}, {-1, 0}, // Right, Left
        {0, 1}, {0, -1}, // Down, Up
        {1, 1}, {1, -1}, // Diagonals
        {-1, 1}, {-1, -1} // Diagonals
    };

    Point[] n = new Point[deltas.length];
    for (int i = 0; i < deltas.length; i++)
    {
      int newX = p.x + deltas[i][0];
      int newY = p.y + deltas[i][1];

      // Check boundaries
      if (newX < 0 || newY < 0 || newX >= cols || newY >= rows) n[i] = null;
      else n[i] = new Point(newX, newY);
    }

    return n;
  }
}

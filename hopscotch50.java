import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;



public class hopscotch50 {

    static class Node {
        int x;
        int y;
        boolean visited;
        int distance;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            visited = false;
            distance = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        // Keep track of shortest distance - this is the output for the problem
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Manage all nodes for dijkstras algorithm
        LinkedList<Stack<Node>> stacksList = new LinkedList<>();

        // Check path is possible
        boolean[] exists = new boolean[k];

        // Poulate
        for (int i = 0; i < k; i++) {
            stacksList.add(new Stack<Node>());
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int num = sc.nextInt();
                exists[num - 1] = true;
                stacksList.get(num - 1).add(new Node(row, col));
            }
        }

        // Early out
        for (boolean b : exists) {
            if (!b) {
                System.out.println(-1);
                return;
            }
        }

        // printQueue(stacksList);

        // Get shortest distance for each starting point to each ending point
        var startStack = stacksList.poll();
        var endStack = stacksList.getLast();

        // printQueue(stacksList);

        while (!startStack.empty()) {
            // Temporary list to preserve base nodes
            LinkedList<Stack<Node>> tempList = new LinkedList<>();
            for (var stack : stacksList) {
                var newStack = new Stack<Node>();
                for (var node : stack) {
                    newStack.add(new Node(node.x, node.y));
                }
                tempList.add(stack);
            }

            Node startNode = startStack.pop();

            while (!endStack.empty()) {
                Node endNode = endStack.pop();

                // Solve and save min distance
                minHeap.offer(solve(startNode, stacksList.subList(0, stacksList.size() - 1), endNode));

            }

            // Restore list back to base
            for (int i = 0; i < tempList.size(); i++) {
                stacksList.set(i, tempList.get(i));
            }


        }

        // printQueue(stacksList);

    }

    public static Integer solve(Node startNode, List<Stack<Node>> nodesBetween, Node endNode) {
        // Dijkstras Algorithm
        return 1;
    }

    public static void printQueue(LinkedList<Stack<Node>> queue) {
        // ChatGPT generated - shhh...

        System.out.println("--- Queue Contents ---");
        for (int i = 0; i < queue.size(); i++) {
            Stack<Node> stack = queue.get(i);
            System.out.printf("Stack %d (size: %d):\n", i + 1, stack.size());

            // Temporary stack to preserve original stack order
            Stack<Node> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                Node node = stack.pop();
                System.out.printf("  Node(x=%d, y=%d, visited=%b, distance=%d)\n",
                        node.x, node.y, node.visited, node.distance);
                tempStack.push(node);
            }

            // Restore the original stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }
        System.out.println("----------------------");
    }

    public static int getDistance(Integer[] p1, Integer[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public static int getDistance(Node p1, Node p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}

import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.PriorityQueue;



public class hopscotch50 {

    static class Node implements Comparable<Node> {
        int id;
        int x;
        int y;
        int distance;

        Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            distance = 0;
        }

        @Override
        public int compareTo(hopscotch50.Node o) {
            return Integer.compare(distance, o.distance);
        }

        @Override
        protected Node clone() throws CloneNotSupportedException {
            return new Node(id, x, y);
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        // Keep track of shortest distance - this is the output for the problem
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Manage all nodes for dijkstras algorithm
        LinkedList<Stack<Node>> stacks = new LinkedList<>();

        // Check path is possible
        boolean[] exists = new boolean[k];

        // Poulate
        for (int i = 0; i < k; i++) {
            stacks.add(new Stack<Node>());
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int num = sc.nextInt();
                exists[num - 1] = true;
                stacks.get(num - 1).add(new Node(num, row, col));
            }
        }

        // Early out
        for (boolean b : exists) {
            if (!b) {
                System.out.println(-1);
                return;
            }
        }

        // For each node in the first stack, use dijkstras algorithm
        // to find min distance to each node in the last stack, store
        // distance in minHeap to output at the end

        var firstStack = stacks.pollFirst();
        var lastStack = stacks.pollLast();

        while (!firstStack.empty()) {
            var startNode = firstStack.pop();

            for (int i = 0; i < lastStack.size(); i++) {
                // Must preserve orignal end node
                Node endNode = lastStack.get(i).clone();
                int minDistance = solve(startNode, stacks, endNode);
                minHeap.offer(minDistance);
            }

            // Before moving on to the next start node, reset all node distances
        }

        // Output the answer
        System.out.println(minHeap.peek());

    }

    public static Integer solve(Node startNode, LinkedList<Stack<Node>> stacks, Node endNode) {
        // Dijkstras Algorithm

        System.out.println("Starting at " + startNode.id);
        System.out.println("Ending at " + endNode.id);
        System.out.println("Nodes to traverse:");
        printQueue(stacks);

        // Note: part of dijsktras algorithm is to start the next iteration
        // at the node with the smallest distance, we can store nodes in
        // a priority queue after we update each nodes distance, the Node
        // is a Comparable, and compares distances, that ways the head of the
        // queue will always be the node with the smallest distance

        return 0;
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
                System.out.printf("  Node(id=%d, x=%d, y=%d, distance=%d)\n",
                        node.id, node.x, node.y, node.distance);
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

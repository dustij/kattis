import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;



public class hopscotch50 {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int distance;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            distance = 0;
        }

        @Override
        public int compareTo(hopscotch50.Node o) {
            return Integer.compare(distance, o.distance);
        }
    }

    public static void main(String[] args) {
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
                stacks.get(num - 1).add(new Node(row, col));
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
        // this turns stackList from, ex. [1, 2, 3, 4] -> [2, 3] i.e. the middle stacks
        var startStack = stacks.pollFirst();
        var endStack = stacks.pollLast();

        // System.out.println("1. stacks between : " + stacks.size());

        // printQueue(stacksList);

        while (!startStack.empty()) {
            // Temporary stack to preserve endStack
            Stack<Node> tempStack = new Stack<>();

            // Temporary llist to preserve base nodes
            LinkedList<Stack<Node>> tempStacks = new LinkedList<>();
            for (var stack : stacks) {
                var newStack = new Stack<Node>();
                for (var node : stack) {
                    newStack.add(new Node(node.x, node.y));
                }
                tempStacks.add(stack);
            }

            Node startNode = startStack.pop();

            // System.out.println("2. stacks between : " + stacks.size());

            while (!endStack.empty()) {
                System.out.println("________ choosing end node __________");
                // Get a node and make copy to restore it later
                Node endNode = endStack.pop();
                tempStack.add(new Node(endNode.x, endNode.y));

                // System.out.println("3. stacks between : " + stacks.size());

                // System.out.println(startNode);
                // Solve and save min distance (dijkstras algorithm)
                minHeap.offer(solve(startNode, stacks, endNode));
                // System.out.println(startNode);

                // Restore llist
                for (int i = 0; i < tempStacks.size(); i++) {
                    stacks.add(tempStacks.get(i));
                }
            }

            // Restore end stack
            while (!tempStack.isEmpty()) {
                endStack.push(tempStack.pop());
            }



            // // Restore llist
            // for (int i = 0; i < tempStacks.size(); i++) {
            // stacks.add(tempStacks.get(i));
            // }


        }

        System.out.println(minHeap.peek());

        // printQueue(stacksList);

    }

    public static Integer solve(Node startNode, LinkedList<Stack<Node>> nodesBetween, Node endNode) {
        // Dijkstras Algorithm

        // Preserve start node
        Node localStartNode = new Node(startNode.x, startNode.y);

        // Preserve nodes between
        LinkedList<Stack<Node>> nodesBetweenLocal = new LinkedList<>();
        for (var stack : nodesBetween) {
            Stack<Node> newStack = new Stack<>();
            for (var node : stack) {
                newStack.push(new Node(node.x, node.y));
            }
            nodesBetweenLocal.add(newStack);
        }

        // Ordered by distance
        PriorityQueue<Node> nextNodes = new PriorityQueue<>();


        while (!nodesBetweenLocal.isEmpty()) {
            // System.out.println("4. nodes between : " + nodesBetweenLocal.size());

            Stack<Node> currStack = nodesBetweenLocal.pollFirst();

            // System.out.println("1 - next Nodes size : " + nextNodes.size());

            // System.out.println("Curr Stack size = " + currStack.size());

            while (!currStack.empty()) {
                Node currNode = currStack.pop();
                currNode.distance += getDistance(localStartNode, currNode) + localStartNode.distance;
                nextNodes.offer(currNode);
            }

            // System.out.println("2 - next Nodes size : " + nextNodes.size());

            // Set start node to the one with min distance
            localStartNode = nextNodes.poll();

            // Empty queue for next run
            nextNodes = new PriorityQueue<>();
            // System.out.println("nodes between left " + nodesBetweenLocal.size());
        }

        // System.out.println("calc distance");

        // System.out.println("local start node: " + localStartNode);

        int d = getDistance(localStartNode, endNode) + localStartNode.distance;

        return d;
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
                System.out.printf("  Node(x=%d, y=%d, distance=%d)\n",
                        node.x, node.y, node.distance);
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

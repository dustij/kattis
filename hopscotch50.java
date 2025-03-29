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
        boolean visited;

        Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            distance = Integer.MAX_VALUE;
            visited = false;
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
                // Preserve orignal stacks
                LinkedList<Stack<Node>> stacksCopy = new LinkedList<>();
                for (int j = 0; j < stacks.size(); j++) {
                    Stack<Node> stackCopy = new Stack<>();
                    for (var node : stacks.get(j)) {
                        stackCopy.push(node.clone());
                    }
                    stacksCopy.add(stackCopy);
                }

                // Dijkstras algorithm solve
                Node endNode = lastStack.get(i);
                int minDistance = solve(startNode, stacksCopy, endNode);
                minHeap.offer(minDistance);
            }

            // debug
            // for (var i : minHeap) {
            // System.out.println(i);
            // }
            // System.exit(0);
        }

        // Output the answer
        System.out.println(minHeap.peek());

    }

    public static Integer solve(Node currNode, LinkedList<Stack<Node>> stacks, Node endNode) {
        // Dijkstras Algorithm

        // System.out.println("Starting at " + currNode.id);
        // System.out.println("Ending at " + endNode.id);
        // System.out.println("Nodes to traverse:");
        // printQueue(stacks);

        // Note: part of dijsktras algorithm is to start the next iteration
        // at the node with the smallest distance, we can store nodes in
        // a priority queue after we update each nodes distance, the Node
        // is a Comparable, and compares distances, that ways the head of the
        // queue will always be the node with the smallest distance

        // Starting node has 0 distance
        currNode.distance = 0;

        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        minHeap.offer(currNode);

        // Starting stack
        Stack<Node> currStack = new Stack<>();
        currStack.push(currNode);

        while (!stacks.isEmpty()) {
            // Get best node
            currNode = minHeap.poll();
            currNode.visited = true;

            // Reset minHeap
            minHeap = new PriorityQueue<>();

            // 1. Update estimates
            // 1.1 Neighbors in current stack
            for (var node : currStack) {
                if (!node.visited) {
                    node.distance = Math.min(node.distance, currNode.distance + getDistance(node, currNode));
                    minHeap.offer(node);
                }
            }

            // 1.2 Neighbors in next
            var nextStack = stacks.peekFirst();

            for (var node : nextStack) {
                if (!node.visited) {
                    node.distance = Math.min(node.distance, currNode.distance + getDistance(node, currNode));
                    minHeap.offer(node);
                }
            }

            // Need to track which two stacks we are looking a
            int nextId = nextStack.peek().id;

            // printMinHeap(minHeap);
            // printQueue(stacks);

            // 2. Choose next node
            currNode = minHeap.peek();

            // Determine which stack we are in, and set up next loop accordingly
            // If the best node is in the next stack, set it to current stack, otherwise do nothing
            if (currNode.id == nextId) {
                // Empty contents
                currStack = new Stack<>();
                while (!nextStack.empty()) {
                    currStack.push(nextStack.pop());
                }

                // Move next stack over
                var otherStack = stacks.pollFirst();
                while (!otherStack.empty()) {
                    nextStack.add(otherStack.pop());
                }
            }

            // System.out.printf(" CurrNode(id=%d, x=%d, y=%d, distance=%d)\n",
            // currNode.id, currNode.x, currNode.y, currNode.distance);


        }

        // System.out.printf(" EndNode(id=%d, x=%d, y=%d, distance=%d)\n",
        // endNode.id, endNode.x, endNode.y, endNode.distance);
        // System.out.println("Current nodes distance = " + currNode.distance);
        // System.out.println("Distance to last node from current node = " + getDistance(currNode,
        // endNode));
        // System.out.printf("Total distance = %d%n", getDistance(currNode, endNode) + currNode.distance);
        return getDistance(currNode, endNode) + currNode.distance;
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

    public static void printStack(Stack<Node> stack) {
        System.out.printf("Stack (size: %d):\n", stack.size());
        for (var node : stack) {
            System.out.printf("  Node(id=%d, x=%d, y=%d, distance=%d, visited=%s)\n",
                    node.id, node.x, node.y, node.distance, String.valueOf(node.visited));
        }
    }

    public static void printMinHeap(PriorityQueue<Node> stack) {
        System.out.printf("MinHeap (size: %d):\n", stack.size());
        for (var node : stack) {
            System.out.printf("  Node(id=%d, x=%d, y=%d, distance=%d, visited=%s)\n",
                    node.id, node.x, node.y, node.distance, String.valueOf(node.visited));
        }
    }

    public static int getDistance(Integer[] p1, Integer[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    public static int getDistance(Node p1, Node p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class hopscotch50 {

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int distance;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
            distance = Integer.MAX_VALUE;
        }

        @Override
        public int compareTo(hopscotch50.Node o) {
            return Integer.compare(distance, o.distance);
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        ArrayList<Node>[] nodes = (ArrayList<Node>[]) new ArrayList[k];

        // Check path is possible
        boolean[] exists = new boolean[k];

        // Populate
        for (int i = 0; i < k; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int i = sc.nextInt();
                exists[i - 1] = true;
                Node node = new Node(row, col);
                if (i == 1)
                    node.distance = 0;
                nodes[i - 1].add(node);
            }
        }

        // Early out
        for (boolean b : exists) {
            if (!b) {
                System.out.println(-1);
                return;
            }
        }

        // DP
        for (int i = 1; i < k; i++) {
            for (var currNode : nodes[i]) {
                for (var prevNode : nodes[i - 1]) {
                    currNode.distance = Math.min(currNode.distance, prevNode.distance + getDistance(prevNode, currNode));
                }
            }
        }

        // Retrieve min distance (from last layer)
        PriorityQueue<Node> minHeap = new PriorityQueue<>();
        for (var node : nodes[nodes.length - 1]) {
            minHeap.offer(node);
        }

        System.out.println(minHeap.poll().distance);
    }

    public static int getDistance(Node p1, Node p2) {
        return Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col);
    }
}

import java.util.*;
class Solution {
    static class Node {
        int x;
        int y;
        int num;
        Node left;
        Node right;

        Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static List<Integer> pre = new ArrayList<>();
    static List<Integer> post = new ArrayList<>();
    
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = {};
        int n = nodeinfo.length;

        Node[] nodes = new Node[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
        }

        Arrays.sort(nodes, (a, b) -> {
            if (a.y != b.y) {
                return Integer.compare(b.y, a.y);
            }
            return Integer.compare(a.x, b.x);
        });

        Node root = nodes[0];

        for (int i = 1; i < n; i++) {
            insert(root, nodes[i]);
        }

        preorder(root);
        postorder(root);

        answer = new int[2][n];

        for (int i = 0; i < n; i++) {
            answer[0][i] = pre.get(i);
            answer[1][i] = post.get(i);
        }

        return answer;
    }

    static void insert(Node parent, Node node) {
        if (node.x < parent.x) {
            if (parent.left == null) {
                parent.left = node;
            } else {
                insert(parent.left, node);
            }
        } else {
            if (parent.right == null) {
                parent.right = node;
            } else {
                insert(parent.right, node);
            }
        }
    }

    static void preorder(Node node) {
        if (node == null) {
            return;
        }

        pre.add(node.num);
        preorder(node.left);
        preorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        post.add(node.num);
    }
}
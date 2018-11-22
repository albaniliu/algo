import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    class Node {
        Node left;
        Node right;
        int val;
        public Node(int v) {
            this.val = v;
        }
    }

    int[] arrive;
    Map<Integer, Long> dp = new HashMap<>();
    int n, m;
    public void run(Scanner scanner) throws IOException {

        long ans = Long.MAX_VALUE;
        n = scanner.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            int v = scanner.nextInt();
            nodes[i] = new Node(v);
        }
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            int l = scanner.nextInt() - 1;
            int r = scanner.nextInt() - 1;
            if (l != -1) {
                nodes[i].left = nodes[l];
            }
            if (r != -1) nodes[i].right = nodes[l];
        }
        List<Integer> pre = new ArrayList<>();

    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner scanner = new Scanner(System.in);
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Main jam = new Main();
        jam.run(scanner);
    }
}

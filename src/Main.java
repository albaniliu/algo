import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    class Node {
        char var;
        int complex;
        public Node(char ch, int com) {
            this.var = ch;
            this.complex = com;
        }
    }

    private boolean contain(int set, int i) {
        return (set & (1<<i)) > 0;
    }

    int n, m;
    int ans = 0;
    int[] low;
    int[] mark;
    int cnt;
    List<List<Integer>> graph = new ArrayList<>();
    boolean[] visited;
    boolean canReturn;
    BigInteger N;
    BigInteger mod;
    BigInteger one = new BigInteger("1");
    BigInteger two = new BigInteger("2");

    public void run(Scanner scanner) throws IOException {
        String line = scanner.nextLine();
        String[] sp = line.split(" ");
        int L = Integer.parseInt(sp[0]);
        String cc = sp[1];
        Stack<Node> stack = new Stack<>();
        Set<Character> vars = new HashSet<>();
        for (int l = 0; l < L; l++) {
            line = scanner.nextLine();
            if (line.equals("E")) {
                if (stack.isEmpty()) {
                    System.out.println("ERR");
                    for (l++;l < L; l++) line = scanner.nextLine();
                    return;
                }
                Node node = stack.pop();
                vars.remove(node.var);
            } else {
                sp = line.split(" ");
                char var = sp[1].charAt(0);
                if (vars.contains(var)) {
                    System.out.println("ERR");
                    for (l++;l < L; l++) line = scanner.nextLine();
                    return;
                }
                vars.add(var);
                int com = 0;
                if (sp[2].equals(sp[3])) {
                    com = 0;
                } else if (sp[2].equals("n")) {
                    com = -1;
                } else if (sp[3].equals("n")) {
                    com = 1;
                } else {
                    int start = Integer.parseInt(sp[2]);
                    int end = Integer.parseInt(sp[3]);
                    if (start > end) {
                        com = -1;
                    } else {
                        com = 0;
                    }
                }

                if (stack.isEmpty()) {
                    stack.push(new Node(var, com));
                } else {
                    Node  node = stack.peek();
                    if (node.complex == -1) {
                        stack.push(new Node(var, -1));
                    } else {
                        if (com == -1)
                            stack.push(new Node(var, -1));
                        else stack.push(new Node(var, node.complex+com));
                    }
                }
                ans = Math.max(ans, stack.peek().complex);
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("ERR");
            return;
        }
        String res = "O(1)";
        if (ans > 0) {
            res = "O(n^" + ans + ")";
        }
        if (res.equals(cc)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner scanner = new Scanner(System.in);
        int T = Integer.parseInt(scanner.nextLine());
        for (int t = 0; t < T; t++) {
            Main jam = new Main();
            jam.run(scanner);
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CodeForces {
    class Pair<K, V> {
        K first;
        V second;
        public Pair(K k, V v) {
            first = k;
            second = v;
        }
    }
    private boolean contain(int set, int i) {
        return (set & (1<<i)) > 0;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a%b);
    }
    
    private long pow(long a, long p) {
        if (p == 0) return 1;
        long b = pow(a, p/2);
        b = b * b;
        if (p % 2 == 1) b *= a;
        return b % mod;
    }
    
    private static boolean isSame(double a, double b) {
        return Math.abs(a - b) < 1e-10;
    }

    private static void swapBoolean(boolean[] p, int i, int j) {
        boolean tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }

    private static void swap(int[] p, int i, int j) {
        int tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }
    private static int countOne(int a) {
        if (a == 0) return 0;
        return countOne(a & (a-1)) + 1;
    }

    private static int sdiv(int a, int b) {
        return (a +b -1) / b;
    }

    private int[] retran(int index) {
        int[] res = new int[2];
        res[0] = index / M;
        res[1] = index % M;
        return res;
    }

    private int tran(int x, int y) {
        return x * M + y;
    }

    private boolean inTable(int x, int y) {
        return x>=0 && x< N && y >= 0 && y < M;
    }

    int N;
    int R;
    int[][] C = new int[10][10];
    int M;
    int mod = 1_000_000_007;
    long IMPO;
    int ans;

    int[] dx = new int[]{1,0, -1, 0};
    int[] dy = new int[]{0, -1, 0, 1};
    Map<String, Boolean> dp = new HashMap<>();

    class Edge {
        int u;
        int v;
        int start;
        int duration;
        public Edge(int u, int v, int l, int d) {
            this.u = u;
            this.v = v;
            this.start = l;
            this.duration = d;
        }
    }
    List<List<Integer>> graph = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
    int[] parent;
    boolean[] visited;
    public void run(Scanner scanner) throws IOException {

        long a = scanner.nextInt();
        long b = scanner.nextInt();
        long k = scanner.nextInt();
        long ans = (a-b) * (k / 2);
        if (k % 2 == 1) ans += a;
//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans);
//        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "C://Users/user/eclipse-workspace/algo/example.txt";
//      String outFile = "C://Users/user/eclipse-workspace/algo/example-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/A-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/A-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/A-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/A-large-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/B-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/B-small-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/B-large-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/B-large-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/C-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/C-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/D-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/D-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/D-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/D-large-out.txt";

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();
        for (int i = 1; i <= T; i++) {
            CodeForces jam = new CodeForces();
            jam.run(scanner);
        }
    }
}

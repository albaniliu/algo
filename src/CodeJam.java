import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CodeJam {
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

    private static void swap(long[] p, int i, int j) {
        long tmp = p[i];
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
        int value;
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
            this.value = Integer.MAX_VALUE;
        }
    }
    List<List<Integer>> graph = new ArrayList<>();
    List<Edge> edges = new ArrayList<>();
    boolean[] visited;
    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        ans = 0;
        for (int i = 0; i < N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            int u = Integer.parseInt(sp[0]) - 1;
            int v = Integer.parseInt(sp[1]) - 1;
            Edge e = new Edge(u, v);
            graph.get(u).add(edges.size());
            graph.get(v).add(edges.size());
            edges.add(e);
        }
        visited = new boolean[N];
        for (int i = 0; i < N; i++) if (!visited[i]) {
            if (!dfs(i, -1)) {
                System.out.println("IMPOSSIBLE");
                out.println("IMPOSSIBLE");
                return;
            }
        }


//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans);
            out.println(ans);
//        }
    }

    private boolean dfs(int cur, int inEdge) {
        visited[cur] = true;
        for (int nextEdge: graph.get(cur)) if (nextEdge != inEdge) {
            Edge e = edges.get(nextEdge);
            int other = e.u == cur? e.v: e.u;
            if (visited[other]) {
                e.value = 1;
            } else {
                if (!dfs(other, nextEdge)) return false;
            }
        }

        int sum = 0;
        for (int nextEdge: graph.get(cur)) if (nextEdge != inEdge) {
            Edge e = edges.get(nextEdge);
            sum += e.value;
        }
        if (inEdge != -1) {
            if (sum == 0) return false;
            edges.get(inEdge).value = -sum;
        }
        return true;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        
      String fileName = "C://Users/user/eclipse-workspace/algo/example.txt";
      String outFile = "C://Users/user/eclipse-workspace/algo/example-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/A-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/A-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/A-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/A-large-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/B-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/B-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/B-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/B-large-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/C-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/C-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/C-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/C-large-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/D-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/D-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/D-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/D-large-out.txt";
      
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      PrintWriter out = new PrintWriter(outFile);

        System.out.println(new Date());
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
        	out.print("Case #" + i + ": ");
            System.out.print("Case #" + i + ": ");
            CodeJam jam = new CodeJam();
            jam.run(br, out);
            out.flush();
        }
        out.close();
        br.close();
        System.out.println("Finished");
        System.out.println(new Date());
    }
}

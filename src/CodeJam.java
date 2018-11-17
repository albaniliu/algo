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
    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        ans = Integer.MAX_VALUE;
        int[] arrives  = new int[2*N];
        for (int i = 0; i < 2*N; i++) arrives[i] = -1;
        for (int i = 0; i < 4*N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < 2 * N; i++) {
            sp = br.readLine().split(" ");
            Edge e = new Edge(i / 2, Integer.parseInt(sp[0]) - 1, Integer.parseInt(sp[1]), Integer.parseInt(sp[2]));
            edges.add(e);
            int v = e.v;
            if (arrives[v*2] == -1) {
                arrives[v*2] = (e.start + e.duration) % 24;
                graph.get( i / 2 * 4 + i % 2).add(v * 4 + 2);
            } else {
                arrives[v*2 + 1] = (e.start + e.duration) % 24;
                graph.get( i / 2 * 4 + i % 2).add(v * 4 + 3);
            }
        }
        parent = new int[2*N];
        boolean[] freeNode = new boolean[N];
        for (int i = 0; i < 2*N; i++) parent[i] = i;
        for (int i = 0; i < N; i++) {
            boolean b1 = between(arrives[i*2], edges.get(i*2).start, edges.get(i*2+1).start);
            boolean b2 = between(arrives[i*2 + 1], edges.get(i*2).start, edges.get(i*2+1).start);
            if (b1 ^ b2) {

            } else {
                int parent1 = find(i*2);
                int parent2 = find(i*2+1);
                parent[parent2] = parent1;
                freeNode[i] = true;
            }
        }
        visited = new boolean[2 * N];
        for (int i = 0; i < 2*N; i++) if (!visited[i]) {
            dfs(i);
        }
        ans = 0;
        for (int i = 1; i < N; i++) {
            int arrive = arrives[i*2];
            int nextEdge = i * 2;
            if ((edges.get(i * 2 + 1).start + 24 - arrive) % 24 < (edges.get(i*2).start + 24 - arrive) % 24) {
                nextEdge = i * 2 + 1;
            }
            ans += (edges.get(nextEdge).start + 24 - arrive) % 24;
            arrive = arrives[i*2 + 1];
            ans += (edges.get(nextEdge ^ 1).start + 24 - arrive) % 24;
            ans += edges.get(i*2).duration;
            ans += edges.get(i*2+1).duration;
        }

        for (int i = 1; i < N; i++) {
            if (freeNode[i]) {
                for (int j = 0; j < 2; j++) {
                    graph.get(i * 4 + 2).add(i * 4 + j);
                    graph.get(i * 4 + 3).add(i * 4 + j);
                }
            } else {
                int arrive = arrives[i*2];
                if ((edges.get(i * 2 + 1).start + 24 - arrive) % 24 < (edges.get(i*2).start + 24 - arrive) % 24) {
                    graph.get(i * 4 + 2).add(i * 4 + 1);
                    graph.get(i * 4 + 3).add(i * 4);
                } else {
                    graph.get(i * 4 + 2).add(i * 4);
                    graph.get(i * 4 + 3).add(i * 4 + 1);
                }
            }
        }

        int tmp = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            int s = k % 2;
            int e = 2 + k / 2;
            graph.get(e^1).add(s^1);
            boolean[] visited = new boolean[4 * N];
            dfs(s, visited);
            if (visited[e]) {
                tmp = Math.min(tmp, edges.get(s).start + (edges.get(s^1).start - arrives[(e - 2)^1] + 24) % 24);
            }
            graph.get(e^1).clear();
        }

        ans += tmp + edges.get(0).duration + edges.get(1).duration;
        int cnt = 0;
        for (int i = 0; i < 2*N; i++) if (parent[i] == i) cnt++;
        ans += (cnt - 1) * 24;


//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans);
            out.println(ans);
//        }
    }

    private void dfs(int s, boolean[] visited) {
        visited[s] = true;
        for (int next: graph.get(s)) {
            if (!visited[next]) dfs(next, visited);
        }
    }

    private void dfs(int cur) {
        visited[cur] = true;
        Edge e = edges.get(cur);
        int next = e.v;
        int arrive = (e.start + e.duration) % 24;
        int nextEdge = next * 2;
        if ((edges.get(next * 2 + 1).start + 24 - arrive) % 24 < (edges.get(next*2).start + 24 - arrive) % 24) {
            nextEdge = next * 2 + 1;
        }
        int parent1 = find(cur);
        int parent2 = find(nextEdge);
        parent[parent2] = parent1;
        if (!visited[nextEdge]) dfs(nextEdge);
    }

    private int find(int cur) {
        if (parent[cur] == cur) return cur;
        else return parent[cur] = find(parent[cur]);
    }

    private boolean between(int i, int time1, int time2) {
        int start = Math.min(time1, time2);
        int end = Math.max(time1, time2);
        return i >= start && i <= end;
    }

    private void solve(int cur, int set, boolean[] visited, int now) {
        if (cur == 0 && visited[0] && visited[1]) {
            for (int i = 0; i < N * 2; i++) if (!visited[i]) return;
            ans = Math.min(ans, now);
            return;
        }
        int dayTime = now % 24;
        int path = 0;
        if (contain(set, cur)) {
            if (visited[cur * 2 + 1]) {
                path = 0;
            } else {
                path = 1;
            }
        } else {
            if (visited[cur * 2]) {
                path = 1;
            } else {
                path = 0;
            }
        }
        visited[cur * 2 + path] = true;
        Edge e = edges.get(cur * 2 + path);
        int wait = 0;
        if (e.start >= dayTime) {
            wait = e.start - dayTime;
        } else {
            wait = e.start + 24 - dayTime;
        }
        solve(e.v, set, visited, now + wait + e.duration);
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "/Users/mobike/IdeaProjects/algo/example.txt";
//      String outFile = "/Users/mobike/IdeaProjects/algo/example-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/A-small-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/A-small-out.txt";
//      String fileName = "C://Users/user/eclipse-workspace/algo/A-large-practice.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/A-large-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/B-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/B-small-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/B-large-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/B-large-out.txt";
      String fileName = "/Users/mobike/IdeaProjects/algo/C-small-practice.in";
      String outFile = "/Users/mobike/IdeaProjects/algo/C-small-out.txt";
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

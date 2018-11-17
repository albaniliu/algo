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
    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        ans = Integer.MAX_VALUE;
        int[] arrives  = new int[2*N];

        for (int i = 0; i < 4*N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < 2 * N; i++) {
            sp = br.readLine().split(" ");
            Edge e = new Edge(i / 2, Integer.parseInt(sp[0]) - 1, Integer.parseInt(sp[1]), Integer.parseInt(sp[2]));
            edges.add(e);
        }

        for (int rot = 0; rot < 2; rot ++) {
            Collections.swap(edges, 0, 1);
            for (int i = 0; i < 2*N; i++) arrives[i] = -1;
            for (int i = 0; i < 2*N; i++) {
                Edge e = edges.get(i);
                if (arrives[e.v * 2] == -1) {
                    arrives[e.v * 2] = i;
                } else {
                    arrives[e.v * 2 + 1] = i;
                }
            }
            int ans = edges.get(0).start;
            for (int i = 0; i < 2*N; i++) ans += edges.get(i).duration;

            int[] cost = new int[N];
            parent = new int[2 * N];
            for (int i = 0; i < 2 * N; i++) parent[i] = i;
            for (int i = 0; i < N; i++) {
                if (i != 0) {
                    int x = match(arrives[i*2], i*2) + match(arrives[i*2+1], i*2+1);
                    int y = match(arrives[i*2+1], i*2) + match(arrives[i*2], i*2+1);
                    if (x > y) {
                        int tmp = x;
                        x = y;
                        y = tmp;
                        swap(arrives, i*2, i*2+1);
                    }
                    ans += x;
                    cost[i] = y - x;
                } else {
                    int x = match(arrives[i*2 + 1], i*2+1);
                    int y = match(arrives[i*2], i*2+1);
                    if (x > y) {
                        int tmp = x;
                        x = y;
                        y = tmp;
                        swap(arrives, i*2, i*2+1);
                    }
                    ans += x;
                    cost[i] = y - x;
                }
            }

            int[] comp = new int[2*N];
            for (int i = 0; i < 2*N; i++) comp[i] = -1;
            int cnt = 0;
            for (int i = 0; i < 2*N; i++) {
                if (comp[i] != -1) continue;
                int curE = i;
                while (comp[curE] == -1) {
                    comp[curE] = cnt;
                    int v = edges.get(curE).v;
                    curE = v * 2 + (arrives[v*2] == curE?0:1);
                }
                cnt++;
            }
            List<int[]> needSwap = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                int x = comp[i*2];
                int y = comp[i*2+1];
                if (x != y) {
                    needSwap.add(new int[] {cost[i], x, y});
                }
            }
            Collections.sort(needSwap, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });
            for (int i = 0; i < needSwap.size(); i++) {
                int x = find(needSwap.get(i)[1]);
                int y = find(needSwap.get(i)[2]);
                if (x != y) {
                    ans += needSwap.get(i)[0];
                    parent[y] = x;
                }
            }
            this.ans = Math.min(this.ans, ans);
        }

//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans);
            out.println(ans);
//        }
    }

    private int match(int j, int i) {
        int wait = (edges.get(i).start - (edges.get(j).start + edges.get(j).duration) % 24 + 24)% 24;
        return  wait;
    }

    private int find(int cur) {
        if (parent[cur] == cur) return cur;
        else return parent[cur] = find(parent[cur]);
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
      String fileName = "C://Users/user/eclipse-workspace/algo/C-large-practice.in";
      String outFile = "C://Users/user/eclipse-workspace/algo/C-large-out.txt";
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

package codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Round_3_2017_B {

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
        boolean di;
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
            this.value = Integer.MAX_VALUE;
            this.di = true;
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
            if (dfs(i, -1) == -1) {
                System.out.println("IMPOSSIBLE");
                out.println("IMPOSSIBLE");
                return;
            }
        }
        for (Edge e: edges) {
            if (e.di) {
                System.out.print(e.value);
                out.print(e.value);
            } else {
                System.out.print(-e.value);
                out.print(-e.value);
            }
            System.out.print(" ");
            out.print(" ");
        }
        System.out.println();
        out.println();

    }

    private int dfs(int cur, int inEdge) {
        visited[cur] = true;
        int sum = 0;
        int inSum = 0;
        for (int nextEdge: graph.get(cur)) if (nextEdge != inEdge) {
            Edge e = edges.get(nextEdge);
            int other = e.u == cur? e.v: e.u;
            if (visited[other]) {
                if (e.value == Integer.MAX_VALUE) {
                    e.value = 1;
                    sum++;
                    if (e.u == cur) e.di = false;
                } else {
                    inSum++;
                }
            } else {
                int tmp = dfs(other, nextEdge);
                if (tmp == -1) return tmp;
                sum+=tmp;
            }
        }

        sum -= inSum;
        if (inEdge != -1) {
            if (sum == 0) return -1;
            edges.get(inEdge).value = -sum;
            if (edges.get(inEdge).u == cur) edges.get(inEdge).di = false;
        }
        return sum;
    }


    public static void main(String[] args) throws NumberFormatException, IOException {

      String fileName = "/B-large-practice.in";
      String outFile = "/B-large-out.txt";

      
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      PrintWriter out = new PrintWriter(outFile);

        System.out.println(new Date());
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
        	out.print("Case #" + i + ": ");
            System.out.print("Case #" + i + ": ");
            Round_3_2017_B jam = new Round_3_2017_B();
            jam.run(br, out);
            out.flush();
        }
        out.close();
        br.close();
        System.out.println("Finished");
        System.out.println(new Date());
    }
}

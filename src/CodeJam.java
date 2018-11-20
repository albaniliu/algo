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
        b = (b * b) % mod;
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
    int[][] c = new int[101][101];
    int C;
    int M;
    int D;
    int mod = 1_000_000_007;
    long IMPO;
    long ans;

    int[] dx = new int[]{1,0, -1, 0};
    int[] dy = new int[]{0, -1, 0, 1};
    Map<String, Boolean> dp = new HashMap<>();

    List<List<Integer>> graph = new ArrayList<>();
    int[] parent;
    boolean[][] visited;
    long[][] table;
    class Node {
        long value;
        int x;
        int y;
        public Node(int x, int y, long v) {
            this.x = x;
            this.y = y;
            this.value = v;
        }
    }
    int[] mapX = new int[210];
    int[] mapY = new int[210];
    Map<Integer, Integer> rmapX = new HashMap<>();
    Map<Integer, Integer> rmapY = new HashMap<>();

    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        R = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        int n = Integer.parseInt(sp[2]);
        D = Integer.parseInt(sp[3]);
//        System.out.println(R + " " + C + " " + n + " " + D);
        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]) - 1;
            int y = Integer.parseInt(sp[1]) - 1;
            int v = Integer.parseInt(sp[2]);
            points.add(new int[]{x, y, v});
        }
        Collections.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int index = 0;
        mapX[index] = 0;
        rmapX.put(0, 0);
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i)[0] > mapX[index]) {
                mapX[++index] = points.get(i)[0];
                rmapX.put(points.get(i)[0], index);
            }
        }
        if (mapX[index] != R-1) {
            mapX[++index] = R-1;
            rmapX.put(R-1, index);
        }
        N = index + 1;

        Collections.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        index = 0;
        mapY[index] = 0;
        rmapY.put(0, 0);
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i)[1] > mapY[index]) {
                mapY[++index] = points.get(i)[1];
                rmapY.put(points.get(i)[1], index);
            }
        }
        if (mapY[index] != C-1) {
            mapY[++index] = C-1;
            rmapY.put(C-1, index);
        }
        M = index + 1;
        table = new long[N][M];
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                long d = Math.abs(points.get(i)[0] - points.get(j)[0]) +
                        Math.abs(points.get(i)[1] - points.get(j)[1]);
                long diff = Math.abs(points.get(i)[2] - points.get(j)[2]);
                if (diff > d * D) {
                    System.out.println("IMPOSSIBLE");
                    out.println("IMPOSSIBLE");
                    return;
                }
            }
        }
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) table[i][j] = -1;
        for (int i = 0; i < points.size(); i++) {
            table[rmapX.get(points.get(i)[0])][rmapY.get(points.get(i)[1])] = points.get(i)[2];
//            table[points.get(i)[0]][points.get(i)[1]] = points.get(i)[2];
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.value == o2.value) return 0;
                return o1.value - o2.value < 0? -1: 1;
            }
        });

        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
            if (table[i][j] != -1) {
                pq.add(new Node(i, j, table[i][j]));
            }
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            for (int k = 0; k < 4; k++) {
                int x = cur.x + dx[k];
                int y = cur.y + dy[k];
                if (inTable(x, y)) {
                    long tmp = table[cur.x][cur.y] + diff(x, y, cur.x, cur.y) * D;
                    if (table[x][y] == -1 || tmp < table[x][y]) {
                        table[x][y] = tmp;
                        pq.add(new Node(x, y, table[x][y]));
                    }
                }
            }
        }

        ans = 0;
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
            ans += table[i][j];
            ans %= mod;
            if (i > 0 && j > 0) {
                long minX = Math.min(table[i-1][j], table[i][j]);
                long maxX = Math.max(table[i-1][j], table[i][j]);
            } else if (i > 0) {
                long min = Math.min(table[i-1][j], table[i][j]);
                long max = Math.max(table[i-1][j], table[i][j]);
                long diff = mapX[i] - mapX[i-1] - 1;
                long inc = (max - min) / D;
                inc = Math.min(diff, inc);
                long tmp = ((min+D) + (min + D *inc)) % mod;
                tmp = (tmp * inc / 2) % mod;
                ans = (ans + tmp) % mod;
                long left = diff - inc;
                long minI = (left + 1) / 2;
                long maxI = left / 2;
                tmp = ((min + D *inc) + ((min + D *inc + minI * D))) % mod;
                tmp = (tmp * minI / 2) % mod;
                ans = (ans + tmp) % mod;

                tmp = ((max + D) + ((max + maxI * D))) % mod;
                tmp = (tmp * maxI / 2) % mod;
                ans = (ans + tmp) % mod;
            } else if (j > 0) {
                long min = Math.min(table[i][j - 1], table[i][j]);
                long max = Math.max(table[i][j - 1], table[i][j]);
                long diff = mapY[j] - mapY[j-1] - 1;
                long inc = (max - min) / D;
                inc = Math.min(diff, inc);
                long tmp = ((min+D) + (min + D *inc)) % mod;
                tmp = (tmp * inc / 2) % mod;
                ans = (ans + tmp) % mod;
                long left = diff - inc;
                long minI = (left + 1) / 2;
                long maxI = left / 2;
                tmp = ((min + D *inc) + ((min + D *inc + minI * D))) % mod;
                tmp = (tmp * minI / 2) % mod;
                ans = (ans + tmp) % mod;

                tmp = ((max + D) + ((max + maxI * D))) % mod;
                tmp = (tmp * maxI / 2) % mod;
                ans = (ans + tmp) % mod;
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

    private long diff(int x, int y, int r, int c) {
        long x1 = mapX[x];
        long y1 = mapY[y];
        long r1 = mapX[r];
        long c1 = mapY[c];
        return Math.abs(x1 - r1)  + Math.abs(y1 - c1);
    }

    private long sum(long start, long d, long len) {
        long ans = (start + (start + d * (len - 1))) % mod;
        ans = ans * len / 2 % mod;
        return ans;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "/Users/mobike/IdeaProjects/algo/example.txt";
//      String outFile = "/Users/mobike/IdeaProjects/algo/example-out.txt";
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
      String fileName = "/Users/mobike/IdeaProjects/algo/D-small-practice.in";
      String outFile = "/Users/mobike/IdeaProjects/algo/D-small-out.txt";
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

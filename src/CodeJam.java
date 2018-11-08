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

    int N;
    int R;
    int C;
    int M;
    int mod = 1_000_000_007;
    long IMPO;

    char[][] table;

    int[] skt;
    boolean[] select;
    int cntS;
    int cntT;
    Map<Integer, Integer> mapT = new HashMap<>();
    List<Integer> listT = new ArrayList<>();
    Map<Integer, Integer> mapS = new HashMap<>();
    List<Integer> listS = new ArrayList<>();
    Map<Integer, Set<Integer>> seen = new HashMap<>();
    List<int[]> ans = new ArrayList<>();

    int[] dx = new int[]{1,0, -1, 0};
    int[] dy = new int[]{0, -1, 0, 1};
    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        C = Integer.parseInt(sp[0]);
        R = Integer.parseInt(sp[1]);
        M = Integer.parseInt(sp[2]);
        table = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                table[i][j] = line.charAt(j);
                if (table[i][j] == 'T') {
                    mapT.put(tran(i, j), cntT);
                    listT.add(tran(i, j));
                    cntT++;
                } else if (table[i][j] == 'S') {
                    mapS.put(tran(i, j), cntS);
                    listS.add(tran(i, j));
                    cntS++;
                }
            }
        }
        for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) if (table[i][j] != '#') {
            Set<Integer> set = new HashSet<>();
            for (int d = 0; d < 4; d++) {
                int x = i;
                int y = j;
                while (inTable(x, y) && table[x][y] != '#') {
                    if (table[x][y] == 'T')
                        set.add(tran(x, y));
                    x += dx[d];
                    y += dy[d];
                }
            }
            seen.put(tran(i, j), set);
        }

        int[][] dp = new int[1<<cntS][1<<cntT];
        List<int[]> list = new ArrayList<>();
        solve(dp, (1<<cntS) - 1, (1<<cntT) -1, list);

        System.out.println(ans.size());
        out.println("" + ans.size());
        for (int i = 0; i < ans.size(); i++) {
            System.out.println((ans.get(i)[0] + 1) + " " + (ans.get(i)[1] + 1));
            out.println((ans.get(i)[0] + 1) + " " + (ans.get(i)[1] + 1));
        }

//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
//            System.out.println(ans + " " + promote);
//            out.println(ans + " " + promote);
//        }
    }

    private void solve(int[][] dp, int setS, int setT, List<int[]> list) {
        if (list.size() > ans.size()) {
            ans.clear();
            ans.addAll(list);
        }
        if (setS == 0) return;
        if (setT == 0) return;
        if (dp[setS][setT] != 0) return;
        dp[setS][setT] = 1;

        for (int i = 0; i < cntS; i++) if (contain(setS, i)) {
            int index = listS.get(i);
            boolean[][] visited = new boolean[R][C];
            int[] point = retran(index);
            int x = point[0];
            int y = point[1];
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(point);
            visited[x][y] = true;
            int move = 0;
            while (!queue.isEmpty()) {
                if (move <= M) {
                    int size = queue.size();
                    for (int s = 0; s< size; s++) {
                        point = queue.poll();
                        Set<Integer> see = seen.get(tran(point[0], point[1]));
                        boolean stop = false;
                        if (see != null) {
                            for (int t : see) {
                                int ti = mapT.get(t);
                                if (contain(setT, ti)) {
                                    stop = true;
                                    list.add(new int[]{i, ti});
                                    solve(dp, setS - (1 << i), setT - (1 << ti), list);
                                    list.remove(list.size() - 1);
                                }
                            }
                        }
                        if (stop) {
                            continue;
                        }
                        for (int k = 0; k < 4; k++) {
                            int nx = point[0] + dx[k];
                            int ny = point[1] + dy[k];
                            if (!inTable(nx, ny) || table[nx][ny] == '#') continue;
                            if (visited[nx][ny]) continue;
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                } else {
                    break;
                }
                move ++;
            }
        }
    }

    private int[] retran(int index) {
        int[] res = new int[2];
        res[0] = index / C;
        res[1] = index % C;
        return res;
    }

    private int tran(int x, int y) {
        return x * C + y;
    }

    private boolean inTable(int x, int y) {
        return x>=0 && x< R && y >= 0 && y < C;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "C://Users/user/eclipse-workspace/algo/example.txt";
//      String outFile = "C://Users/user/eclipse-workspace/algo/example-out.txt";
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
      String fileName = "C://Users/user/eclipse-workspace/algo/D-small-practice.in";
      String outFile = "C://Users/user/eclipse-workspace/algo/D-small-out.txt";
//      String fileName = "d://codejam/D-large-practice.in";
//      String outFile = "d://codejam/D-large-out.txt";
      
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

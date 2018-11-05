import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class CodeJam {
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
    Map<Integer, Set<Integer>> pathMap = new HashMap<>();

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0,0, 1, -1};
    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        R = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        for (int i = 0; i < R; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                table[i][j] = sp[0].charAt(j);
            }
        }

        for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) {
            if (table[i][j] == '#') continue;
            if (table[i][j] == '\\' || table[i][j] == '/' ) continue;
            int index = (i * C + j) * 2;
            if (!pathMap.containsKey(index)) {
                Set<Integer> path = new HashSet<>();
                int res = dfs(index, path, 0);
                if (res == 1) {
                    // circle
                } else {
                    Set<Integer> path2 = new HashSet<>();
                    dfs(index, path2, 1);
                    path.addAll(path2);
                }
                for (int cur: path) pathMap.put(cur, path);
            }
            Set<Integer> path = pathMap.get(index);
        }

//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans + " " + promote);
            out.println(ans + " " + promote);
//        }
    }

    private int dfs(int index, Set<Integer> path, int d) {
        if (path.contains(index)) return 1;
        int x = index / 2 / C;
        int y = index / 2 % C;
        if (x < 0 || x >= R || y < 0 || y >= C) return 0;
        if (table[x][y] == '#') return 0;

        if (table[x][y] == '\\' || table[x][y] == '/') {
            d = change(d, table[x][y]);
        } else {
            path.add(index);
        }

        int nx = x + dx[d];
        int ny = y + dy[d];
        int nindex = (nx * C + ny) * 2 + (d > 1?1:0);
        return dfs(nindex, path, d);
    }

    private int change(int d, char c) {
        if (c == '\\') {
            if (d == 0) return 2;
            if (d == 1) return 3;
            if (d == 2) return 0;
            if (d == 3) return 1;
        } else if (c == '/') {
            if (d == 0) return 3;
            if (d == 1) return 2;
            if (d == 2) return 1;
            if (d == 3) return 0;
        }
        throw new RuntimeException("change error");
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
//      String fileName = "C://Users/user/eclipse-workspace/algo/C-small-practice-2.in";
//      String outFile = "C://Users/user/eclipse-workspace/algo/C-small-out.txt";
//      String fileName = "d://codejam/C-large-practice.in";
//      String outFile = "d://codejam/C-large-out.txt";
//      String fileName = "d://codejam/D-small-practice.in";
//      String outFile = "d://codejam/D-small-out.txt";
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

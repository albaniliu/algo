package codejam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Round_3_2017_D {
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

    private long mul(long a, long b) {
        a %= mod;
        b %= mod;
        return (a * b) % mod;
    }

    private void add(long a) {
        ans += a;
        ans %= mod;
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

    long a,b,c,d;
    int row1, row2, col1, col2;

    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        R = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        int n = Integer.parseInt(sp[2]);
        D = Integer.parseInt(sp[3]);
        List<int[]> points = new ArrayList<>();
        TreeSet<Integer> setX = new TreeSet<>();
        setX.add(0);
        setX.add(R);
        TreeSet<Integer> setY = new TreeSet<>();
        setY.add(0);
        setY.add(C);
        for (int i = 0; i < n; i++) {
            sp = br.readLine().split(" ");
            int x = Integer.parseInt(sp[0]) - 1;
            int y = Integer.parseInt(sp[1]) - 1;
            int v = Integer.parseInt(sp[2]);
            points.add(new int[]{x, y, v});
            setX.add(x);
            setY.add(y);
        }
        for (int i = 0; i < points.size(); i++) for (int j = i +1; j < points.size(); j++) {
            long d = diff(points.get(i)[0], points.get(i)[1], points.get(j)[0], points.get(j)[1]);
            if (Math.abs(points.get(i)[2] - points.get(j)[2]) > d * D) {
                System.out.println("IMPOSSIBLE");
                out.println("IMPOSSIBLE");
                return;
            }
        }
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int x: setX) rows.add(x);
        for (int y: setY) cols.add(y);
        ans = 0;
        for (int i = 0; i < rows.size() - 1; i++) for (int j = 0; j < cols.size() - 1; j++) {
            row1 = rows.get(i); row2 = rows.get(i+1) - 1;
            col1 = cols.get(j); col2 = cols.get(j+1) - 1;
            a = countValue(row1, col1, points);
            b = countValue(row1, col2, points);
            c = countValue(row2, col1, points);
            d = countValue(row2, col2, points);

            for (int rot = 0; rot < 4; rot++) {
                if (aBest(row1, col1)) {
                    int low = 0;
                    int hi = Math.max(col2 - col1, row2 - row1) + 1;
                    while (low + 1 < hi) {
                        int mid = low + (hi - low) / 2;
                        if (aBest(row1 + mid, col1) && aBest(row1, col1 + mid)) {
                            low = mid;
                        } else {
                            hi = mid;
                        }
                    }
                    add(mul(a, sum(1, low+1)));
                    add(mul(D, sum(0, low)));
                    add(mul(D, squereSum(low)));
                    int memo = low;

                    low = 0;
                    hi = Math.max(col2 - col1, row2 - row1) + 1;
                    while (low + 1 < hi) {
                        int mid = low + (hi - low) / 2;
                        if (aBest(row1 + mid, col1) || aBest(row1, col1 + mid)) {
                            low = mid;
                        } else {
                            hi = mid;
                        }
                    }
                    add(mul(memo + 1, mul(a, low - memo)));
                    add(mul(memo + 1, mul(D, sum(memo+1, low))));

                    int memo2 = low;
                    low = memo2;
                    hi = row2 - row1 + col2 - col1 + 1;
                    while (low + 1 < hi) {
                        int mid = low + (hi - low) / 2;
                        if (aBest(row1 + memo2, col1 + mid - memo2) || aBest(row1 + mid - memo2, col1 + memo2)) {
                            low = mid;
                        } else {
                            hi = mid;
                        }
                    }
//                    for (int k = memo2 + 1; k <= low; k++) {
//                        long tmp = (a + k * D) * (memo - k + memo2 + 1);
//                    }
                    add(mul(mul(a, memo+1+memo2), low-memo2));
                    add(mul(-a, sum(memo2+1, low)));
                    add(mul(mul(D, memo+1+memo2), sum(memo2+1, low)));
                    add(mul(-D, squareSum(memo2 + 1, low)));
                }
                a--;
                if (rot % 2 == 0) {
                    long tmp = a;
                    a = b;
                    b = tmp;
                    tmp = c;
                    c = d;
                    d = tmp;
                }
                if (rot == 1) {
                    long tmp = a;
                    a = c;
                    c = tmp;
                    tmp = b;
                    b = d;
                    d = tmp;
                }
            }
        }

        ans = (ans % mod) + mod;
        ans %= mod;

//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans);
            out.println(ans);
//        }
    }

    private boolean aBest(int x, int y) {
        if (x < row1 || x > row2 || y < col1 || y > col2) return false;
        long da = diff(x, y, row1, col1) * D + a;
        long db = diff(x, y, row1, col2) * D + b;
        long dc = diff(x, y, row2, col1) * D + c;
        long dd = diff(x, y, row2, col2) * D + d;
        return da <= db && da <= dc && da <= dd;
    }

    private long countValue(int x, int y, List<int[]> points) {
        long ans = Long.MAX_VALUE;
        for (int[] p: points) {
            ans = Math.min(ans, p[2] + diff(x, y, p[0], p[1]) * D);
        }
        return ans;
    }

    private long diff(int x1, int y1, int r1, int c1) {
        return Math.abs(x1 - r1)  + Math.abs(y1 - c1);
    }

    private long sum(long start, long d, long len) {
        long ans = (start + (start + d * (len - 1))) % mod;
        ans = ans * len / 2 % mod;
        return ans;
    }

    private long sum(long start, long end) {
        long a = start + end;
        long b = end - start + 1;
        if (a % 2 == 0) a/=2;
        else b /= 2;
        return (a * b) % mod;
    }

    private long squereSum(long n) {
        long a = n;
        long b = n+1;
        long c = 2*n+1;
        if (a % 2 == 0) a /= 2;
        else if (b % 2 == 0) b /= 2;
        else c /= 2;

        if (a % 3 == 0) a /= 3;
        else if (b % 3 == 0) b /= 3;
        else c /= 3;
        a = (a * b) % mod;
        a = (a * c) % mod;
        return a;
    }

    private  long squareSum(long a, long b) {
        long B = squereSum(b);
        long A = squereSum(a - 1);
        return (B - A + mod) % mod;
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
//      String fileName = "/Users/mobike/IdeaProjects/algo/D-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/D-small-out.txt";
      String fileName = "C://Users/user/eclipse-workspace/algo/D-large-practice.in";
      String outFile = "C://Users/user/eclipse-workspace/algo/D-large-out.txt";
      
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      PrintWriter out = new PrintWriter(outFile);

        System.out.println(new Date());
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
        	out.print("Case #" + i + ": ");
            System.out.print("Case #" + i + ": ");
            Round_3_2017_D jam = new Round_3_2017_D();
            jam.run(br, out);
            out.flush();
        }
        out.close();
        br.close();
        System.out.println("Finished");
        System.out.println(new Date());
    }
}

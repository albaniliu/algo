import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TrieNode {
    List<String> words = new ArrayList<>();
    TrieNode[] children = new TrieNode[2];
    int cost = Integer.MAX_VALUE / 2;
    public TrieNode() {}
}

public class CodeJam {

    private boolean contain(int set, int i) {
        return (set & (1<<i)) > 0;
    }

    private static void addNode(TrieNode root, String w) {
        TrieNode cur = root;
        for (int i = 0; i < w.length(); i++ ) {
            int index = w.charAt(i) - '0';
            if (cur.children[index] == null) cur.children[index] = new TrieNode();
            cur = cur.children[index];
        }
        cur.words.add(w);
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
    
    int N;
    int M;
    int P;
    int B;
    int D;
    int mod = 1_000_000_007;
    int res = 0;
    int[] h;
    int[] a;
    class Node {
        long cnt;
        long start;
        public Node(long c, long s) {
            this.cnt = c;
            this.start = s;
        }
    }
    
    public void run(BufferedReader br, BufferedWriter wr) throws IOException {

        String[] sp = br.readLine().split(" ");
        h = new int[2];
        a = new int[2];
        h[0] = Integer.parseInt(sp[0]);
        a[0] = Integer.parseInt(sp[1]);
        h[1] = Integer.parseInt(sp[2]);
        a[1] = Integer.parseInt(sp[3]);
        B = Integer.parseInt(sp[4]);
        D = Integer.parseInt(sp[5]);
        int n = 101;
        int ans = Integer.MAX_VALUE;
        int heal = h[0];
        int cue = 0;
        for (int d = 0; d <= n; d++) {
//            if (a[1] == 0) break;
            if (d > 0) {
                if (heal <= a[1] - D) {
                    heal = h[0] - a[1];
                    cue++;
                }
                a[1] -= D;
                a[1] = Math.max(a[1], 0);
                heal -= a[1];
                if (heal <= 0) break;
            }
            int tmp = d + cue;
            int[][][] dp = new int[n][n][n];
            for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) for (int k = 0; k < n; k++) dp[i][j][k] = -1;
            
            boolean[][][] visited = new boolean[n][n][n];
            int res = solve(dp, visited, heal, a[0], h[1]);
            if (res == -1) continue;
            tmp += res;
            ans = Math.min(ans, tmp);
        }
        
        if (ans == Integer.MAX_VALUE) {
            System.out.println("IMPOSSIBLE");
            wr.write("" + "IMPOSSIBLE");
        } else {
            System.out.println(ans);
            wr.write("" + ans);
        }
    }


    private int solve(int[][][] dp, boolean[][][] visited, int hd, int ad, int hk) {
        if (hd <= 0) return -1;
        if (visited[hd][ad][hk]) {
            return dp[hd][ad][hk];
        }
        visited[hd][ad][hk] = true;
        if (hk <= ad) return 1;
        int ans = Integer.MAX_VALUE;
        int tmp = solve(dp, visited, hd - a[1], ad, hk - ad);
        if (tmp != -1) {
            ans = Math.min(ans, tmp+1);
        }
        tmp = solve(dp, visited, h[0] - a[1], ad, hk);
        if (tmp != -1) {
            ans = Math.min(ans, tmp+1);
        }
        tmp = solve(dp, visited, hd - a[1], Math.min(100, ad + B), hk);
        if (tmp != -1) {
            ans = Math.min(ans, tmp+1);
        }
        if (ans == Integer.MAX_VALUE) return -1;
        return dp[hd][ad][hk] = ans;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "d://codejam/example.txt";
//      String outFile = "d://codejam/example-out.txt";
//      String fileName = "d://codejam/A-small-practice.in";
//      String outFile = "d://codejam/A-small-out.txt";
//      String fileName = "d://codejam/A-large-practice.in";
//      String outFile = "d://codejam/A-large-out.txt";
//      String fileName = "d://codejam/B-small-practice.in";
//      String outFile = "d://codejam/B-small-out.txt";
//      String fileName = "d://codejam/B-large-practice.in";
//      String outFile = "d://codejam/B-large-out.txt";
      String fileName = "d://codejam/C-small-practice.in";
      String outFile = "d://codejam/C-small-out.txt";
//      String fileName = "d://codejam/C-large-practice.in";
//      String outFile = "d://codejam/C-large-out.txt";
//      String fileName = "d://codejam/D-small-practice.in";
//      String outFile = "d://codejam/D-small-out.txt";
//      String fileName = "d://codejam/D-large-practice.in";
//      String outFile = "d://codejam/D-large-out.txt";
      
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      BufferedWriter wr = new BufferedWriter(new FileWriter(outFile));
//      try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//          lines = stream.collect(Collectors.toList());
//      } catch (IOException e) {
//          e.printStackTrace();
//      }

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            wr.write("Case #" + i + ": ");
            System.out.print("Case #" + i + ": ");
            CodeJam jam = new CodeJam();
            jam.run(br, wr);
            wr.newLine();
            wr.flush();
        }
        wr.close();
        br.close();
        System.out.println("Finished");
    }
}

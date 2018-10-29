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
    long IMPO;
    public void run(BufferedReader br, BufferedWriter wr) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;


        String[] sp = br.readLine().split(" ");
        h = new int[2];
        a = new int[2];
        h[0] = Integer.parseInt(sp[0]);
        a[0] = Integer.parseInt(sp[1]);
        h[1] = Integer.parseInt(sp[2]);
        a[1] = Integer.parseInt(sp[3]);
        B = Integer.parseInt(sp[4]);
        D = Integer.parseInt(sp[5]);
        long lHd = h[0];
        long Ak = a[1];
        long Ad = a[0];
        long Hd = h[0];
        long Hk = h[1];
        long dbturns = 0;
        long ans = IMPO;
        long aturns = sdiv(Hk, Ad);
        long inc = 0;
        while (sdiv(Hk, Ad + B) + 1 < sdiv(Hk, Ad)) {
            Ad += B;
            inc++;
            aturns = sdiv(Hk, Ad) + inc;
        }
        ans = nodebuff(aturns, Hd, Hd, Ak);

        while (D > 0 && Ak > 0) {
            if(lHd <= Ak-D && Hd-Ak <= Ak-D) {
                // impossible
                break;
            }
            else if (lHd <= Ak-D) {
                // heal
                lHd = Hd - Ak;
                dbturns++;
                continue;
            }
            else {
                long oAk = Ak;
                Ak = Ak - D;
                if(Ak < 0) Ak = 0;
                lHd = lHd - Ak;
                dbturns++;
                if (Ak > 0 && (Hd + Ak - 1) / Ak == (Hd + oAk - 1) / oAk) continue;
            }
            long nans = dbturns + nodebuff(aturns, Hd, lHd, Ak);
            if (nans < ans) ans = nans;
        }

        
        if (ans == IMPO) {
            System.out.println("IMPOSSIBLE");
            wr.write("" + "IMPOSSIBLE");
        } else {
            System.out.println(ans);
            wr.write("" + ans);
        }
    }

    private long sdiv(long a, long b) {
        return (a + b - 1) / b;
    }

    private long nodebuff(long aturns, long hd, long lHd, long ak) {
        if (lHd > ak * (aturns - 1)) return aturns;
        if (aturns == 1) return aturns;
        long maxHit = sdiv(lHd, ak) - 1;
        long oturns = aturns;
        aturns -= maxHit;
        hd -= ak;
        if (hd <= 0) return IMPO;
        long de = sdiv(hd, ak);
        if (de == 1) return IMPO;
        return oturns + 1 + (aturns - 2) / (de - 1);
    }


    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "/Users/mobike/IdeaProjects/algo/example.txt";
//      String outFile = "/Users/mobike/IdeaProjects/algo/example-out.txt";
//      String fileName = "d://codejam/A-small-practice.in";
//      String outFile = "d://codejam/A-small-out.txt";
//      String fileName = "d://codejam/A-large-practice.in";
//      String outFile = "d://codejam/A-large-out.txt";
//      String fileName = "d://codejam/B-small-practice.in";
//      String outFile = "d://codejam/B-small-out.txt";
//      String fileName = "d://codejam/B-large-practice.in";
//      String outFile = "d://codejam/B-large-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/C-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/C-small-out.txt";
      String fileName = "/Users/mobike/IdeaProjects/algo/C-large-practice.in";
      String outFile = "/Users/mobike/IdeaProjects/algo/C-large-out.txt";
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

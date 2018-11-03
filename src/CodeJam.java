import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TrieNode {
    List<String> words = new ArrayList<>();
    TrieNode[] children = new TrieNode[2];
    int cost = Integer.MAX_VALUE / 2;
    public TrieNode() {}
}

public class CodeJam {
    public class Node {
        double arrive;
        int index;
        public Node(double a, int i) {
            this.arrive = a ;
            this.index = i;
        }
    }

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
    int K;
    int mod = 1_000_000_007;
    long IMPO;

    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        K = Integer.parseInt(sp[1]);
        sp = br.readLine().split(" ");
        double U = Double.parseDouble(sp[0]);
        double[] p = new double[N];
        sp = br.readLine().split(" ");
        PriorityQueue<Double> pq = new PriorityQueue<>(new Comparator<Double>() {

			@Override
			public int compare(Double a, Double b) {
				return b < a?-1:1;
			}
        	
        });
        double sum = U;
        for (int i = 0; i < N; i++) {
        	p[i] = Double.parseDouble(sp[i]);
        	sum += p[i];
        	pq.add(p[i]);
        }
        double ans = 1.0;
        while (!pq.isEmpty()) {
        	double tmp = pq.poll();
        	double avg = sum / (pq.size() + 1);
        	if (tmp > avg) {
        		ans *= tmp;
        		sum -= tmp;
        	} else {
        		ans *= Math.pow(avg, pq.size() + 1);
        		break;
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

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "d://codejam/example.txt";
//      String outFile = "d://codejam/example-out.txt";
//      String fileName = "d://codejam/A-small-practice.in";
//      String outFile = "d://codejam/A-small-out.txt";
//      String fileName = "d://codejam/A-large-practice.in";
//      String outFile = "d://codejam/A-large-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/B-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/B-small-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/B-large-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/B-large-out.txt";
      String fileName = "d://codejam/C-small-practice-1.in";
      String outFile = "d://codejam/C-small-out.txt";
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    int mod = 1_000_000_007;
    int res = 0;
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
        N = Integer.parseInt(sp[0]);
        int R = Integer.parseInt(sp[1]);
        int O = Integer.parseInt(sp[2]);
        int Y = Integer.parseInt(sp[3]);
        int G = Integer.parseInt(sp[4]);
        int B = Integer.parseInt(sp[5]);
        int V = Integer.parseInt(sp[6]);
        
        int[] unicorns = new int[3];

 
        unicorns[0] = R - G;
        unicorns[1] = Y - V;
        unicorns[2] = B - O;
        if ( (G > 0 && R <= G) || (V > 0 && Y <= V) || (O > 0 && B <= O)) {
        	System.out.println("IMPOSSIBLE");
            wr.write("" + "IMPOSSIBLE");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int index = -1;
        if (unicorns[0] > unicorns[1]) {
        	index = 0;
        } else {
        	index = 1;
        }
        if (unicorns[2] > unicorns[index]) {
        	index = 2;
        }
        sb.append(index);
        unicorns[index]--;
        int firstIndex = index;
        while ( unicorns[0] > 0 || unicorns[1] > 0 || unicorns[2] > 0) {
        	if (index == 0) {
        		if (unicorns[1] > unicorns[2]) {
        			index = 1;
        		} else if (unicorns[1] == unicorns[2]) {
        			index = 2;
        		}
        	} else if (index == 1) {
        		if (unicorns[0] > unicorns[2]) {
        			index = 0;
        		} else {
        			index = 2;
        		}
        	} else {
        		if (unicorns[0] > unicorns[1]) {
        			index = 0;
        		} else {
        			index = 1;
        		}
        	}
        	sb.append(index);
        	unicorns[index]--;
        	if (unicorns[index] < 0) {
        		System.out.println("IMPOSSIBLE");
                wr.write("" + "IMPOSSIBLE");
                return;
        	}
        }
        
        if (sb.charAt(0) == sb.charAt(sb.length() - 1)) {
        	System.out.println("IMPOSSIBLE");
            wr.write("" + "IMPOSSIBLE");
            return;
        }
        for (int i = 0; i < sb.length(); i++) {
        	if (sb.charAt(i) == '0') {
        		sb.setCharAt(i, 'R');
        	} else if (sb.charAt(i) == '1') {
        		sb.setCharAt(i, 'Y');
        	} else {
        		sb.setCharAt(i, 'B');
        	}
        }
        System.out.println(sb.toString());
        wr.write("" + sb.toString());
        return;
        
//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            wr.write("" + "IMPOSSIBLE");
//        } else {
//            System.out.println(ans);
//            wr.write("" + ans);
//        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "d://codejam/example.txt";
//      String outFile = "d://codejam/example-out.txt";
//      String fileName = "d://codejam/A-small-practice.in";
//      String outFile = "d://codejam/A-small-out.txt";
//      String fileName = "d://codejam/A-large-practice.in";
//      String outFile = "d://codejam/A-large-out.txt";
      String fileName = "d://codejam/B-small-practice.in";
      String outFile = "d://codejam/B-small-out.txt";
//      String fileName = "d://codejam/B-large-practice.in";
//      String outFile = "d://codejam/B-large-out.txt";
//      String fileName = "/Users/mobike/IdeaProjects/algo/C-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/C-small-out.txt";
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

        System.out.println(new Date());
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
        System.out.println(new Date());
    }
}

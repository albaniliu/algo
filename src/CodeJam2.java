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

//class TrieNode {
//    List<String> words = new ArrayList<>();
//    TrieNode[] children = new TrieNode[2];
//    int cost = Integer.MAX_VALUE / 2;
//    public TrieNode() {}
//}

public class CodeJam2 {

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
    int E;
    long Ts;
    long Tf;
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
    
    public void run(BufferedReader br, BufferedWriter wr) throws IOException {

        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        M = Integer.parseInt(sp[1]);
        E = Integer.parseInt(sp[2]);
        int[] start = new int[2];
        start[0] = Integer.parseInt(sp[3]) - 1;
        start[1] = Integer.parseInt(sp[4]) - 1;
        int[] end = new int[2];
        end[0] = Integer.parseInt(sp[5]) - 1;
        end[1] = Integer.parseInt(sp[6]) - 1;
        int[][] board = new int[N][M];
        Map<Integer, Integer> trapMap = new HashMap<>();
        int[] traps = new int[20];
        int index = 0;
        for (int i = 0; i < N; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0 ;j < M; j++) {
                board[i][j] = Integer.parseInt(sp[j]);
                if (board[i][j] < 0 && board[i][j] > -100000) {
                    traps[index] = i * M + j;
                    trapMap.put(i * M + j, index++);
                }
            }
        }
        int ob = -100000;
        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {1, 0, -1, 0};
        long ans = -1;
        Map<Integer, Integer> dp = new HashMap<>();
        for (int set = 0; set < (1 << index); set++) {

            boolean ok = false;
            if (set == 0) ok = true;
            for (int j = 0; j < index; j++) if (contain(set, j)) {
                int nset = set - (1<<j);
                if (dp.containsKey(nset) && dp.get(nset) >= -board[traps[j] / M][traps[j] % M]) {
                    ok = true;
                    break;
                }
            }
            if (!ok) continue;
            
            int eng = E;
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> queue = new LinkedList<>();
            queue.add(start);
            visited[start[0]][start[1]] = true;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nx = cur[0] + dx[k];
                    int ny = cur[1] + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx][ny] == ob) continue;
                    if (visited[nx][ny]) continue;
                    if (board[nx][ny] < 0 && !contain(set, trapMap.get(nx * M + ny))) continue;

                    eng += board[nx][ny];
                    
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
            if (visited[end[0]][end[1]]) {
                ans = Math.max(ans, eng);
            }
            boolean add = true;
            for (int j = 0; j < index; j++) if (contain(set, j)) {
                if (!visited[traps[j] / M][traps[j] % M]) {
                    add = false;
                    break;
                }
            }
            if (add) dp.put(set, eng);
        }
        
//        if (ans == -1) {
//            System.out.println("IMPOSSIBLE");
//            wr.write("" + "IMPOSSIBLE");
//        } else {
            System.out.println(ans);
            wr.write("" + ans);
//        }
    }


    private long getBus(long arrived, int i, long[][] buses) {
        long start = buses[i][0];
        if (arrived <= start) return start + buses[i][2];
        long diff = arrived - start;
        long f = buses[i][1];
        long next = (diff + f-1) / f;
        start += next * f;
        return start + buses[i][2];
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        
//      String fileName = "d://codejam/example.txt";
//      String outFile = "d://codejam/example-out.txt";
//      String fileName = "d://codejam/A-small-attempt0.in";
//      String outFile = "d://codejam/A-small-out.txt";
//      String fileName = "d://codejam/A-large.in";
//      String outFile = "d://codejam/A-large-out.txt";
//      String fileName = "d://codejam/B-small-attempt0.in";
//      String outFile = "d://codejam/B-small-out.txt";
//      String fileName = "d://codejam/B-large.in";
//      String outFile = "d://codejam/B-large-out.txt";
//      String fileName = "d://codejam/C-small-practice.in";
//      String outFile = "d://codejam/C-small-out.txt";
      String fileName = "d://codejam/C-large-practice.in";
      String outFile = "d://codejam/C-large-out.txt";
      
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
            CodeJam2 jam = new CodeJam2();
            jam.run(br, wr);
            wr.newLine();
            wr.flush();
        }
        wr.close();
        br.close();
        System.out.println("Finished");
    }
}

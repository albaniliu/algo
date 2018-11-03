import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

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
    int C;
    int M;
    int mod = 1_000_000_007;
    long IMPO;


    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        N = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        M = Integer.parseInt(sp[2]);
        int[][] ticket = new int[M][2];
        for (int i = 0; i < M; i++) {
            sp = br.readLine().split(" ");
            ticket[i][0] = Integer.parseInt(sp[0]) - 1;
            ticket[i][1] = Integer.parseInt(sp[1]) - 1;
        }
        Arrays.sort(ticket, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return  a[0] - b[0];
            }
        });

        int[] person = new int[C];
        int[] position = new int[N];
        int ans = 0;
        for (int i = 0; i < M; i++) {
            person[ticket[i][1]]++;
            ans = Math.max(ans, person[ticket[i][1]]);
            position[ticket[i][0]]++;
        }
        int sum = 0;
        for (int i= 0; i < N; i++) {
            sum += position[i];
            ans = Math.max(ans, sdiv(sum, i+1));
        }
        int promote = 0;
        int[] count = new int[N];
        for (int i = 0; i < M; i++) {
            count[ticket[i][0]]++;
        }
        for (int i = 0; i < N; i++) {
            if (count[i] > ans) promote += count[i] - ans;
        }
//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
            System.out.println(ans + " " + promote);
            out.println(ans + " " + promote);
//        }
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
      String fileName = "C://Users/user/eclipse-workspace/algo/B-large-practice.in";
      String outFile = "C://Users/user/eclipse-workspace/algo/B-large-out.txt";
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

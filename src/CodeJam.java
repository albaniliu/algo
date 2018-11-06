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
    boolean[][] cover;
    Map<Integer, Set<Integer>> pathMap = new HashMap<>();
    Map<Integer, Integer> fixed = new HashMap<>();
    List<List<Integer>> graph = new ArrayList<>();
    int[] skt;
    boolean[] select;
    int ptr;
    int cnt;

    int[] dx = new int[]{0,0, 1, -1};
    int[] dy = new int[]{-1, 1, 0, 0};
    public void run(BufferedReader br, PrintWriter out) throws IOException {

        IMPO = 1000000000;
        IMPO = IMPO * 1000000000l;

        String[] sp = br.readLine().split(" ");
        R = Integer.parseInt(sp[0]);
        C = Integer.parseInt(sp[1]);
        table = new char[R][C];
        cover = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            sp = br.readLine().split(" ");
            for (int j = 0; j < C; j++) {
                table[i][j] = sp[0].charAt(j);
            }
        }

        for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) {
            if (table[i][j] == '#') continue;
            if (table[i][j] == '\\' || table[i][j] == '/' ) continue;
            if (i == 1 && j == 16) {
                int a = 0;
            }
            int index = (i * C + j) * 2;
            if (!pathMap.containsKey(index)) {
                Set<Integer> path = new HashSet<>();
                int res = dfs(index, path, 0);
                if (res == 1) {
                    // circle
                    for (int cur: path) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (isShooter(x, y)) {
                            int d = cur & 1;
                            int number = x * C + y;
                            if (fixed.containsKey(number) && fixed.get(number) != (d^1)) {
                                System.out.println("IMPOSSIBLE");
                                out.println("IMPOSSIBLE");
                                return;
                            }
                            fixed.put(number, d^1);
                            if ((d^1) == 0) {
                                table[x][y] = '-';
                            } else {
                                table[x][y] = '|';
                            }
                        }
                    }
                } else {
                    Set<Integer> path2 = new HashSet<>();
                    dfs(index, path2, 1);
                    path.addAll(path2);
                }

                int cntShooter = 0;
                for (int cur: path) {
                    pathMap.put(cur, path);
                    int x = cur / 2 / C;
                    int y = cur / 2 % C;
                    if (isShooter(x, y)) cntShooter ++;
                }
                if (cntShooter >= 2) {
                    for (int cur: path) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (isShooter(x, y)) {
                            int d = cur & 1;
                            int number = x * C + y;
                            if (fixed.containsKey(number) && fixed.get(number) != (d^1)) {
                                System.out.println("IMPOSSIBLE");
                                out.println("IMPOSSIBLE");
                                return;
                            }
                            fixed.put(number, d^1);
                            if ((d^1) == 0) {
                                table[x][y] = '-';
                            } else {
                                table[x][y] = '|';
                            }
                        }
                    }
                }
            }

            if (!pathMap.containsKey(index^1)) {
                Set<Integer> path = new HashSet<>();
                int res = dfs(index^1, path, 2);
                if (res == 1) {
                    // circle
                    for (int cur: path) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (isShooter(x, y)) {
                            int d = cur & 1;
                            int number = x * C + y;
                            if (fixed.containsKey(number) && fixed.get(number) != (d^1)) {
                                System.out.println("IMPOSSIBLE");
                                out.println("IMPOSSIBLE");
                                return;
                            }
                            fixed.put(number, d^1);
                            if ((d^1) == 0) {
                                table[x][y] = '-';
                            } else {
                                table[x][y] = '|';
                            }
                        }
                    }
                } else {
                    Set<Integer> path2 = new HashSet<>();
                    dfs(index^1, path2, 3);
                    path.addAll(path2);
                }

                int cntShooter = 0;
                for (int cur: path) {
                    pathMap.put(cur, path);
                    int x = cur / 2 / C;
                    int y = cur / 2 % C;
                    if (isShooter(x, y)) cntShooter ++;
                }
                if (cntShooter >= 2) {
                    for (int cur: path) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (isShooter(x, y)) {
                            int d = cur & 1;
                            int number = x * C + y;
                            if (fixed.containsKey(number) && fixed.get(number) != (d^1)) {
                                System.out.println("IMPOSSIBLE");
                                out.println("IMPOSSIBLE");
                                return;
                            }
                            fixed.put(number, d^1);
                            if ((d^1) == 0) {
                                table[x][y] = '-';
                            } else {
                                table[x][y] = '|';
                            }
                        }
                    }
                }
            }
        }

        boolean updated = true;
        while (updated) {
            updated = false;
            for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) if (table[i][j] == '.' && !cover[i][j]) {
                int index = (i * C + j) * 2;
                if (i == 0 && j == 0) {
                    int a = 0;
                }
                Set<Integer> path1 = pathMap.get(index);
                Set<Integer> path2 = pathMap.get(index ^ 1);
                int cnt1 = countShoot(path1);
                int cnt2 = countShoot(path2);
                if (cnt1 == 0 && cnt2 == 0) {
                    System.out.println("IMPOSSIBLE");
                    out.println("IMPOSSIBLE");
                    return;
                } else if (cnt1 == 1 && cnt2 == 1) {
                    continue;
                } else if (cnt1 == 1 && !cover[i][j]) {
                    for (int cur: path1) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (isShooter(x, y)) {
                            int d = cur & 1;
                            int number = x * C + y;
                            fixed.put(number, d);
                            if (d == 0) {
                                table[x][y] = '-';
                            } else {
                                table[x][y] = '|';
                            }
                        }
                    }
                    for (int cur : path1) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (table[x][y] == '.') cover[x][y] = true;
                    }
                    cover[i][j] = true;
                    updated = true;
                } else if (cnt2 == 1 && !cover[i][j]) {
                    for (int cur: path2) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (isShooter(x, y)) {
                            int d = cur & 1;
                            int number = x * C + y;
                            fixed.put(number, d);
                            if (d == 0) {
                                table[x][y] = '-';
                            } else {
                                table[x][y] = '|';
                            }
                        }
                    }
                    for (int cur : path2) {
                        int x = cur / 2 / C;
                        int y = cur / 2 % C;
                        if (table[x][y] == '.') cover[x][y] = true;
                    }
                    cover[i][j] = true;
                    updated = true;
                }
            }
        }

        for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) if (isShooter(i, j) && fixed.containsKey(i * C + j)) {
            int index = (i * C + j) * 2;
            if (table[i][j] == '|') index++;
            Set<Integer> path = pathMap.get(index);
            for (int cur : path) {
                int x = cur / 2 / C;
                int y = cur / 2 % C;
                if (table[x][y] == '.') cover[x][y] = true;
            }
        }

        Map<Integer, Integer> indexMap = new HashMap<>();
        cnt = 0;
        for (int i = 0; i < R; i++) for (int j = 0; j < C; j++) if (isShooter(i, j) && !fixed.containsKey(i * C + j)) {
            int index =  (i * C + j) * 2;
            indexMap.put(index, cnt++);
            indexMap.put(index^1, cnt++);
        }

        for (int i = 0; i < cnt; i++) graph.add(new ArrayList<>());
        skt = new int[cnt];
        select = new boolean[cnt];
        ptr = 0;
        for (int i= 0; i < R; i++) for (int j = 0; j < C; j++) if (table[i][j] == '.' && !cover[i][j]) {
            int index = (i * C + j) * 2;
            Set<Integer> path1 = pathMap.get(index);
            Set<Integer> path2 = pathMap.get(index ^ 1);
            int s1 = 0;
            int s2 = 0;
            for (int cur:path1) {
                int x = cur / 2 / C;
                int y = cur / 2 % C;
                if (isShooter(x, y)) {
                    try {
                        s1 = indexMap.get(cur);
                        break;
                    } catch (Exception e) {
                       System.out.println(x + " " + y + " " + cur + " " + indexMap);
                       throw e;
                    }
                }
            }
            for (int cur: path2) {
                int x = cur / 2 / C;
                int y = cur / 2 % C;
                if (isShooter(x, y)) {
                    s2 = indexMap.get(cur);
                    break;
                }
            }
            addVerse(s1^1, s2^1);
        }
        if (!solve()) {
            System.out.println("IMPOSSIBLE");
            out.println("IMPOSSIBLE");
        } else {
            System.out.println("POSSIBLE");
            out.println("POSSIBLE");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (isShooter(i, j) && !fixed.containsKey(i * C + j)) {
                        int index = (i * C + j) * 2;
                        int nindex = indexMap.get(index);
                        if (select[nindex]) table[i][j] = '-';
                        else table[i][j] = '|';
                    }
                    System.out.print(table[i][j]);
                    out.print(table[i][j]);
                }
                System.out.println();
                out.println();
            }

        }

//        if (ans == IMPO) {
//            System.out.println("IMPOSSIBLE");
//            out.println("IMPOSSIBLE");
//        } else {
//            System.out.println(ans + " " + promote);
//            out.println(ans + " " + promote);
//        }
    }

    private boolean solve() {
        for (int i = 0; i < cnt; i+=2) {
            if (!select[i] && !select[i^1]) {
                if (!dfs(i)) {
                    while (ptr > 0) select[skt[--ptr]] = false;
                    if (!dfs(i ^ 1)) return false;
                }
            }
        }
        return true;
    }

    boolean dfs(int cur) {
        if (select[cur ^ 1]) return false;
        if (select[cur]) return true;
        select[cur] = true;
        skt[ptr++] = cur;
        for (int next: graph.get(cur)) {
            if (!dfs(next)) return false;
        }
        return true;
    }

    private void addVerse(int x, int y) {
        graph.get(x ^ 1).add(y);
        graph.get(y ^ 1).add(x);
    }

    private int countShoot(Set<Integer> path) {
        int cnt = 0;
        for (int cur: path) {
            int x = cur / 2 / C;
            int y = cur / 2 % C;
            if (isShooter(x, y)) {
                int d = cur & 1;
                int number = x * C + y;
                if (fixed.containsKey(number)) {
                    if (fixed.get(number) == d) cnt++;
                } else {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean isShooter(int x, int y) {
        return table[x][y] == '|' || table[x][y] == '-';
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
        if (nx < 0 || nx >= R || ny < 0 || ny >= C) return 0;
        int nindex = (nx * C + ny) * 2 + (d > 1?1:0);
        return dfs(nindex, path, d);
    }

    private int change(int d, char c) {
        if (c == '\\') {
            if (d == 0) return 3;
            if (d == 1) return 2;
            if (d == 2) return 1;
            if (d == 3) return 0;
        } else if (c == '/') {
            if (d == 0) return 2;
            if (d == 1) return 3;
            if (d == 2) return 0;
            if (d == 3) return 1;
        }
        throw new RuntimeException("change error");
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
//      String fileName = "/Users/mobike/IdeaProjects/algo/C-small-practice.in";
//      String outFile = "/Users/mobike/IdeaProjects/algo/C-small-out.txt";
      String fileName = "/Users/mobike/IdeaProjects/algo/C-large-practice.in";
      String outFile = "/Users/mobike/IdeaProjects/algo/C-large-out.txt";
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

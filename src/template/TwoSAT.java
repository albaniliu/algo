package template;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhao@mobike.com on 2018/11/5.
 */
public class TwoSAT {
    List<List<Integer>> graph = new ArrayList<>();
    int[] skt;
    boolean[] selected;
    int ptr;
    int n;

    public TwoSAT(int n) {
        this.n = n;
        for (int i = 0; i < n * 2; i++) graph.add(new ArrayList<>());
        skt = new int[n];
        ptr = 0;
        selected = new boolean[n*2];
    }

    public void addReverse(int x, int y) {
        x = x * 2;
        y = y * 2;
        graph.get(x ^ 1).add(y);
        graph.get(y ^ 1).add(x);
    }

    public boolean solve() {
        for (int i = 0; i < n * 2; i += 2) if (!selected[i] && !selected[i ^ 1]) {
            ptr = 0;
            if (!dfs(i)) {
                while (ptr > 0) selected[skt[--ptr]] = false;
                if (!dfs(i ^ 1)) return false;
            }
        }
        return true;
    }

    private boolean dfs(int cur) {
        if (selected[cur ^ 1]) return false;
        if (selected[cur]) return true;
        selected[cur] = true;
        skt[ptr++] = cur;
        for (int next: graph.get(cur)) {
            if (!dfs(next)) return false;
        }
        return true;
    }
}

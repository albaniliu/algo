import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) { val = x; }
}
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; }
}
class GraphNode {
	List<GraphNode> adj;
	int name;
	GraphNode(int name) {
		this.name = name;
		adj = new ArrayList<>();
	}
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

class Node {
	int val;
	public Node(int v) {
		val = v ;
	}
}

public class Solution {

    int mod = 1_000_000_007;
	List<Integer> order = new ArrayList<>();
	Map<Integer, List<Integer>> orderMap = new HashMap<>();
	int[][] table;
	int M;
	public String shortestSuperstring(String[] A) {
		int n = A.length;
		M = 1<<n;
		int[][] dp = new int[n][1<<n];
		table = new int[n][n];
		for (int i = 0; i < n; i++) for (int j = 0; j<n; j++) if (i != j) {
			for (int k = 0; k < A[i].length(); k++) {
				String subString = A[i].substring(k);
				if (A[j].startsWith(subString)) {
					table[i][j] = subString.length();
					break;
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			List<Integer> order = new ArrayList<>();
			int tmp = solve(i, (1<<n) - 1 - (1<<i), dp, A, order) + A[i].length();
			order.add(i);
			if (tmp < ans) {
				ans = tmp;
				this.order = order;
			}
		}
		Collections.reverse(order);
		StringBuilder sb = new StringBuilder();
		sb.append(A[order.get(0)]);
		for (int i = 1; i < order.size(); i++) {
			sb.append(A[order.get(i)].substring(table[order.get(i-1)][order.get(i)]));
		}
		return sb.toString();
	}

	private int solve(int i, int set, int[][] dp, String[] A, List<Integer> order) {
		if (set == 0) return 0;
		if (dp[i][set] != 0) {
			order.addAll(orderMap.get(i*M + set));
			return dp[i][set];
		}
		int ans = Integer.MAX_VALUE;
		List<Integer> order2 = new ArrayList<>();
		for (int j = 0; j < A.length; j++) if ((set & (1<<j)) > 0) {
			List<Integer> torder =new ArrayList<>();
			if (j == 3 && set == 24) {
				int a = 0;
			}
			int tmp = solve(j, set - (1<<j), dp, A, torder) + (A[j].length() - table[i][j]);
			torder.add(j);
			if (tmp < ans) {
				order2 = torder;
				ans = tmp;
			}
		}
		order.addAll(order2);
		orderMap.put(i * M + set, order2);
		return dp[i][set] = ans;
	}


	public static void main(String[] args) throws IOException, InterruptedException {
//		System.out.println("[[1,1],[1,3],[3,1],[3,3],[2,2]]".replace("[", "{").replace("]", "}"));
		Solution s = new Solution();
		int[] A = new int[] {0,0,0,0,0,0};
		int[] B = new int[] {327716,69772,667805,856849,78755,606982,696937,207697,275337,290550};
		int[][] hits = new int[][] {{5,1},{1,3}};
		int[][] grid = new int[][] {{1,1},{1,3},{3,1},{3,3},{2,2}};
		String[] emails = new String[] {"catg","ctaagt","gcta","ttca","atgcatc"};
		
		TreeSet<long[]> sumSet = new TreeSet<>(new Comparator<long[]>() {

            @Override
            public int compare(long[] arg0, long[] arg1) {
                // TODO Auto-generated method stub
                return 0;
            }
            
        });
		sumSet.descendingIterator();
		ExecutorService exe = Executors.newFixedThreadPool(10);
		CountDownLatch cd = new CountDownLatch(10);
		cd.await(10, TimeUnit.MILLISECONDS);
		cd.countDown();
		Semaphore semaphore = new Semaphore(5);
		semaphore.tryAcquire(10, TimeUnit.MILLISECONDS);
		semaphore.release();
		System.out.println(s.shortestSuperstring(emails));
	}

}

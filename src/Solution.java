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

	public int tallestBillboard(int[] rods) {
		boolean[] can = new boolean[10000];
		can[0] = true;
		int n = rods.length;
		int[] dp = new int[1 << n];
		dp[0] = 0;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < rods.length; i++) {
			if (!map.containsKey(rods[i])) {
				map.put(rods[i], new ArrayList<>());
			}
			map.get(rods[i]).add(1<<i);
			dp[1<<i] = rods[i];
		}
		for (int set = 1; set < 1<< n; set++) {
			if ((set & (set - 1)) != 0) {
				int tmp = set & (set - 1);
				int other = set ^ tmp;
				int sum = dp[tmp] + dp[other];
				dp[set] = sum;
				if (!map.containsKey(sum)) {
					map.put(sum, new ArrayList<>());
				}
				map.get(sum).add(set);
			}
		}
		int ans = 0;
		for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
			if (entry.getValue().size() > 1) {
				List<Integer> list = entry.getValue();
				boolean needs = true;
				for (int i = 0; i < list.size(); i++) {
					if (!needs) break;
					for (int j = i + 1; j < list.size(); j++) {
						if ((list.get(i) & list.get(j)) == 0) {
							needs = false;
							ans = Math.max(ans, entry.getKey());
						}
					}
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
//		System.out.println("[[1,1],[1,3],[3,1],[3,3],[2,2]]".replace("[", "{").replace("]", "}"));
		Solution s = new Solution();
		int[] A = new int[] {34,28,39,23,32,26,23,24,26,24,26,29,27,34,30,38,34,37,36};
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
		System.out.println(s.largestComponentSize(A));
	}

}

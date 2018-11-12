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

    public int distinctSubseqII(String S) {
        int[] last = new int[26];
        for (int i = 0; i < last.length; i++) last[i] = -1;
        int[] dp = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            int res = 0;
            if (last[S.charAt(i) - 'a'] == -1) res++;
            for (int j = Math.max(0, last[S.charAt(i) - 'a']); j < i; j++) {
                res += dp[j];
                res %= mod;
            }
            dp[i] = res;
            last[S.charAt(i) - 'a'] = i;
        }
        int res = 0;
        for (int i = 0; i < S.length(); i++) {
            res += dp[i];
            res %= mod;
        }
        return res;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("[[1,1],[1,3],[3,1],[3,3],[2,2]]".replace("[", "{").replace("]", "}"));
		Solution s = new Solution();
		int[] A = new int[] {0,0,0,0,0,0};
		int[] B = new int[] {327716,69772,667805,856849,78755,606982,696937,207697,275337,290550};
		int[][] hits = new int[][] {{5,1},{1,3}};
		int[][] grid = new int[][] {{1,1},{1,3},{3,1},{3,3},{2,2}};
		String[] emails = new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
		
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
		System.out.println(s.distinctSubseqII("aba"));
	}

}

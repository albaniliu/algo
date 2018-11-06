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

interface Master {
     public static  int guess(String word) {
		return 0;
	}
}

class Node {
	int val;
	public Node(int v) {
		val = v ;
	}
}

public class Solution {

    List<List<Integer>> pre = new ArrayList<>();
    List<List<Integer>> after = new ArrayList<>();
    int[][] dp;
    int n;

    public int[] movesToStamp(String stamp, String target) {
        StringBuilder sb = new StringBuilder();
        sb.append(target);
        n = target.length();
        StringBuilder t = new StringBuilder();
        for (int i = 0; i < n; i++) {
            t.append('*');
        }
        List<Integer> ans = new ArrayList<>();
        while (!sb.toString().equals(t.toString())) {
            int index = replace(sb, stamp);
            if (index == -1) return new int[0];
            ans.add(index);
        }
        int[] res = new int[ans.size()];
        Collections.reverse(ans);
        for (int j = 0; j < ans.size(); j++) res[j] = ans.get(j);
        System.out.println(ans);
        return res;
    }

    private int replace(StringBuilder sb, String stamp) {
        for (int i = 0; i <= sb.length() - stamp.length(); i++) {
            boolean ok = true;
            int cntStart = 0;
            for (int j = 0; j < stamp.length(); j++) {
                if (sb.charAt(i+j) == '*') cntStart ++;
                if (sb.charAt(i+j) != stamp.charAt(j) && sb.charAt(i+j) != '*') {
                    ok = false;
                    break;
                }
            }
            if (ok && cntStart < stamp.length()) {
                for (int j = 0; j < stamp.length(); j++) sb.setCharAt(i+j, '*');
                return i;
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("[[1,1,0,0],[1,1,0,1],[0,0,1,0],[0,1,0,1]]".replace("[", "{").replace("]", "}"));
		Solution s = new Solution();
		int[] A = new int[] {0,0,0,0,0,0};
		int[] B = new int[] {327716,69772,667805,856849,78755,606982,696937,207697,275337,290550};
		int[][] hits = new int[][] {{5,1},{1,3}};
		int[][] grid = new int[][] {{1,1,0,0},{1,1,0,1},{0,0,1,0},{0,1,0,1}};
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
		System.out.println(s.movesToStamp("abc", "ababc"));
	}

}

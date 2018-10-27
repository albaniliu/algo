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
    
    public int[] threeEqualParts(int[] A) {
        int n = A.length;
        int[] next = new int[n];
        for (int i = 0; i < n; i++) next[i] = -1;
        next[n-1] = n;
        for (int i = n-2; i >= 0; i--) {
            if (A[i] != A[i+1]) {
                next[n-2] = n-2 - (i);
                break;
            }
        }
        if (next[n-2] == -1) next[n-2] = n - 1;
        
        for (int i = n - 3; i >= 0; i--) {
            int tmp = n - 1;
            int len = 0;
            if (next[i+1] > 1) {
                len = Math.min(next[i+1] - 1, next[n-2]);
            }
            tmp = n - len - 1;
            for (int j = i-len; j>=0; j--,tmp--) {
                if (A[j] != A[tmp]) {
                    next[i] = n-1 - tmp;
                    break;
                }
            }
            if (next[i] == -1) next[i] = i+1;
        }
        
        int[] leftZero = new int[n];
        if (A[0] == 0) leftZero[0] = 1;
        for (int i = 1; i < n; i++) {
            if (A[i] == 0) {
                leftZero[i] = leftZero[i-1] + 1;
            }
        }
        
        int first = 0;
        for (int i = 0; i< n; i++  ) {
            if (A[i] == 1) {
                first = i;
                break;
            }
        }
        int l = first;
        int r = n - 2;
        while (l < r) {
            int len = l - first + 1;
            if (next[l] >= len) {
                if (r >= n - len) {
                    r = n - len - 1;
                }
                while (r > l && next[r] < len) r --;
                if (r - l >= len && leftZero[r - len] >= r - len - l && leftZero[n-len-1] >= n - len - 1 -r) {
                    return new int[] {l ,  r+1};
                } else {
                    l++;
                }
            } else {
                l ++;
            }
        }
        return new int[] {-1,-1};
    }

    
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("[[1,1,0,0],[1,1,0,1],[0,0,1,0],[0,1,0,1]]".replace("[", "{").replace("]", "}"));
		Solution s = new Solution();
		int[] A = new int [] {1,0,1,1,1,1,0,1,1,1,1,0,1,1,1};
		int[] B = new int[] {327716,69772,667805,856849,78755,606982,696937,207697,275337,290550};
		int[][] hits = new int[][] {{5,1},{1,3}};
		int[][] grid = new int[][] {{1,1,0,0},{1,1,0,1},{0,0,1,0},{0,1,0,1}};
		
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
		System.out.println(s.threeEqualParts(A)[1]);
	}

}

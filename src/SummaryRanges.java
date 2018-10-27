import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SummaryRanges {

	public int splitArray(int[] nums, int m) {
        long l = 0;
        long r = Integer.MAX_VALUE;
        
        long res = r;
        while (l < r) {
        	long mid = (l+r) / 2;
        	if (ok(nums, m, mid)) {
        		res = mid;
        		r = mid;
        	} else {
        		l = mid + 1;
        	}
        }
        
        return (int) res;
    }
    
	private boolean ok(int[] nums, int m, long mid) {
		int i = 0;
		while (m > 0 && i < nums.length) {
			long sum = 0;
			for (; i < nums.length; i++) {
				if (sum + nums[i] > mid) break;
				sum += nums[i];
			}
			m--;
		}
		return i == nums.length;
	}

	public static void main(String[] args) {
		SummaryRanges s = new SummaryRanges();
	}

}

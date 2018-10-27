public class WordDictionary {
	public int findKthLargest(int[] nums, int k) {
		k = nums.length - k + 1;
        return solve(nums, k, 0, nums.length);
    }

	private int solve(int[] nums, int k, int l, int r) {
		int m = l + (r - l) / 2;
		int i = l;
		int j = r - 1;
		int value = nums[m];
		nums[m] = nums[r-1];
		nums[r-1] = value;
		j = r - 2;
		
		while (i <= j) {
			while (i < r-1 && nums[i] <= value)
				i++;
			while (j >= l && nums[j] > value)
				j--;
			if (i < j) {
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j] = tmp;
			}
		}
		nums[r-1] = nums[i];
		nums[i] = value;
		if (i - l + 1 == k) return value;
		else if (i - l + 1 > k) {
			return solve(nums,k,l,i);
		} else {
			return solve(nums,k - (i-l+1), i+1, r);
		}
	}

	public static void main(String[] args) {
		int[] nums = new int[] {3,2,1,5,6,4};
		WordDictionary w = new WordDictionary();
		System.out.println(w.findKthLargest(nums, 1));
	}

}

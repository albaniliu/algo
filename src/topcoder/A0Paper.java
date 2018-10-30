package topcoder;

public class A0Paper {
    public String canBuild(int[] A) {
    	int cur = 1;
        for (int i = 0; i < A.length; i++) {
        	if (A[i] >= cur) return "Possible";
        	if (i == A.length - 1) break;
        	A[i+1] = A[i] * 2;
        	cur *= 2;
        }
        return "Impossible";
    }
}

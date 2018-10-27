
public class NumMatrix {

	int[][] sums;
	int n , m;
    public NumMatrix(int[][] matrix) {
    	if (matrix.length == 0) return;
    	this.n = matrix.length;
    	this.m = matrix[0].length;
    	
    	sums = new int[n+1][m+1];
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) {
        	sums[i+1][j+1] = sums[i][j+1] + sums[i+1][j] - sums[i-1][j-1] + matrix[i][j];
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if (sums == null) return 0;
        return sums[row1][col1] + sums[row2+1][col2+1] - sums[row1][col2+1] - sums[row2+1][col1];
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

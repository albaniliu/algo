
public class Hello {

    public static void main(String[] args) {
        int n = 5;
        int[] L = new int[7];
        int[] R = new int[7];
        int[] a = new int[] {0, 1,4,5,3,2};

        for (int i = 1; i <= n; ++i) {
            R[i]= i + 1 ;
            L[i] = i - 1;
        }

        for (int i = 1; i <= n; ++i) {
            L[R[a[i]]] = L[a[i]];
            R[L[a[i]]] = R[a[i]];
        }

        for (int i = 1; i <= n; ++i) {
            System.out.print(R[i] + " ");
        }
        System.out.println();
    }

}

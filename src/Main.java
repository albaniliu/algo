import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public void run(Scanner scanner) throws IOException {

        int n = scanner.nextInt();
        long d =  scanner.nextInt();
        long k =  scanner.nextInt();
        long[][] points = new long[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0] = scanner.nextInt();
            points[i][1] = scanner.nextInt();
        }
        long low = -1;
        long hi = 1000000000l;
        while (low < hi) {
            long mid = (hi + low + 1) / 2;
            long min = Math.max(1, d - mid);
            long max = d + mid;
            Deque<Integer> queue = new LinkedList<>();
            long[] scores = new long[n];
            int l = n - 1;
            int r = n - 1;
            for (int i = n - 1; i >= 0; i--) {
                long tmp = points[i][1];
                while (l > i) {
                    if (points[l][0] - points[i][0] >= min) {
                        while (!queue.isEmpty() && scores[queue.getLast()] < scores[l]) queue.pollLast();
                        queue.offerLast(l);
                        l --;
                    } else {
                        break;
                    }
                }
                while (r > l) {
                    if (points[r][0] - points[i][0] > max) {
                        while (!queue.isEmpty() && queue.getFirst() >= r) queue.pollFirst();
                        r--;
                    } else {
                        break;
                    }
                }
                if (queue.size() > 0 && scores[queue.getFirst()] > 0) {
                    tmp += scores[queue.getFirst()];
                }
                scores[i] = tmp;
            }

            while (l >= 0) {
                if (points[l][0] >= min) {
                    while (!queue.isEmpty() && scores[queue.getLast()] < scores[l]) queue.pollLast();
                    queue.offerLast(l);
                    l --;
                } else {
                    break;
                }
            }
            while (r > l) {
                if (points[r][0] > max) {
                    while (!queue.isEmpty() && queue.getFirst() >= r) queue.pollFirst();
                    r--;
                } else {
                    break;
                }
            }

            if (queue.size() > 0 && scores[queue.getFirst()] >= k) {
                hi = mid - 1;
            } else {
                low = mid;
            }
        }
        if (hi == 1000000000l) {
            System.out.println(-1);
        } else {
            System.out.println(hi+1);
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        Scanner scanner = new Scanner(System.in);
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Main jam = new Main();
        jam.run(scanner);
    }
}

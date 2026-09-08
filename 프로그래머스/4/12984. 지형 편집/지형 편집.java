import java.util.*;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length;
        int size = n * n;
        int[] arr = new int[size];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[index++] = land[i][j];
            }
        }

        Arrays.sort(arr);

        int left = arr[0];
        int right = arr[size - 1];
        long answer = Long.MAX_VALUE;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            long cost1 = getCost(arr, mid, P, Q);
            long cost2 = getCost(arr, mid + 1, P, Q);

            answer = Math.min(answer, cost1);

            if (cost1 < cost2) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private long getCost(int[] arr, int target, int P, int Q) {
        long cost = 0;

        for (int height : arr) {
            if (height < target) {
                cost += (long) (target - height) * P;
            } else if (height > target) {
                cost += (long) (height - target) * Q;
            }
        }

        return cost;
    }
}
import java.util.*;

class Solution {
    static class Event {
        int x, y1, y2, type;

        Event(int x, int y1, int y2, int type) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.type = type;
        }
    }

    static int[] ys;
    static int[] count;
    static long[] length;

    public long solution(int[][] rectangles) {
        int n = rectangles.length;

        Event[] events = new Event[n * 2];
        ys = new int[n * 2];

        int idx = 0;

        for (int[] r : rectangles) {
            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];

            events[idx] = new Event(x1, y1, y2, 1);
            ys[idx++] = y1;
            events[idx] = new Event(x2, y1, y2, -1);
            ys[idx++] = y2;
        }

        Arrays.sort(ys);

        int m = 0;

        for (int y : ys) {
            if (m == 0 || ys[m - 1] != y) {
                ys[m++] = y;
            }
        }

        final int size = 4 * m + 5;

        count = new int[size];
        length = new long[size];

        Arrays.sort(events, Comparator.comparingInt(e -> e.x));

        long answer = 0;
        long prevLength = 0;
        int prevX = events[0].x;

        int i = 0;

        while (i < events.length) {
            int x = events[i].x;

            answer += (long) (x - prevX) * prevLength;

            while (i < events.length && events[i].x == x) {
                int left = lowerBound(ys, m, events[i].y1);
                int right = lowerBound(ys, m, events[i].y2) - 1;

                update(1, 0, m - 2, left, right, events[i].type);

                i++;
            }

            prevLength = length[1];
            prevX = x;
        }

        return answer;
    }

    static void update(int node, int start, int end,
                       int left, int right, int value) {

        if (right < start || end < left) {
            return;
        }

        if (left <= start && end <= right) {
            count[node] += value;
        } else {
            int mid = (start + end) >>> 1;

            update(node * 2, start, mid, left, right, value);
            update(node * 2 + 1, mid + 1, end, left, right, value);
        }

        if (count[node] > 0) {
            length[node] = (long) ys[end + 1] - ys[start];
        } else if (start == end) {
            length[node] = 0;
        } else {
            length[node] = length[node * 2] + length[node * 2 + 1];
        }
    }

    static int lowerBound(int[] arr, int size, int target) {
        int left = 0;
        int right = size;

        while (left < right) {
            int mid = (left + right) >>> 1;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
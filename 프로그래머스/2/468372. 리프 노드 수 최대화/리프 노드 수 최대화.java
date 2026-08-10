
class Solution {
    public int solution(int dist_limit, int split_limit) {
        long answer = 1;

        for (int a = 0; a <= 30; a++) {
            for (int b = 0; b <= 30; b++) {
                long product = 1;

                for (int i = 0; i < a; i++) {
                    product *= 2;
                    if (product > split_limit) break;
                }

                if (product > split_limit) continue;

                for (int i = 0; i < b; i++) {
                    product *= 3;
                    if (product > split_limit) break;
                }

                if (product > split_limit) continue;

                long nodes = 1;
                long used = 0;
                long leaves = 1;

                for (int i = 0; i < a + b; i++) {
                    if (used == dist_limit) break;

                    int degree = i < a ? 2 : 3;
                    long count = Math.min(nodes, (long) dist_limit - used);

                    leaves += count * (degree - 1);
                    used += count;
                    nodes *= degree;
                }

                answer = Math.max(answer, leaves);
            }
        }

        return (int) answer;
    }
}
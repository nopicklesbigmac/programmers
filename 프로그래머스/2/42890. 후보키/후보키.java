import java.util.*;
class Solution {
    public int solution(String[][] relation) {
        int answer = 0;
        int rowSize = relation.length;
        int colSize = relation[0].length;
        List<Integer> candidateKeys = new ArrayList<>();

        for (int i = 1; i < (1 << colSize); i++) {
            if (!isMinimal(i, candidateKeys)) {
                continue;
            }
            if (isUnique(i, relation, rowSize, colSize)) {
                candidateKeys.add(i);
            }
        }

        answer = candidateKeys.size();
        return answer;
    }
private boolean isMinimal(int key, List<Integer> candidateKeys) {
        for (int ck : candidateKeys) {
            if ((key & ck) == ck) {
                return false;
            }
        }
        return true;
    }

    private boolean isUnique(int key, String[][] relation, int rowSize, int colSize) {
        Set<String> set = new HashSet<>();
        for (int r = 0; r < rowSize; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < colSize; c++) {
                if ((key & (1 << c)) != 0) {
                    sb.append(relation[r][c]).append(",");
                }
            }
            set.add(sb.toString());
        }
        return set.size() == rowSize;
    }
}
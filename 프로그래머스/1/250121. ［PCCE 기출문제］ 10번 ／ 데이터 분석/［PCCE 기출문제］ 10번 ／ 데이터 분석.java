import java.util.*;
class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer = {};
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 0);
        map.put("date", 1);
        map.put("maximum", 2);
        map.put("remain", 3);

        int extIdx = map.get(ext);
        int sortIdx = map.get(sort_by);
        List<int[]> filteredList = new ArrayList<>();
        for (int[] row : data) {
            if (row[extIdx] < val_ext) {
                filteredList.add(row);
            }
        }
        filteredList.sort((o1, o2) -> Integer.compare(o1[sortIdx], o2[sortIdx]));
        answer = new int[filteredList.size()][4];
        for (int i = 0; i < filteredList.size(); i++) {
            answer[i] = filteredList.get(i);
        }
        return answer;
    }
}
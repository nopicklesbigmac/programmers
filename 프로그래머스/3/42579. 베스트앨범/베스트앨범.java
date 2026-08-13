import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, Integer> total = new HashMap<>();
        Map<String, List<int[]>> songs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
            songs.computeIfAbsent(genres[i], k -> new ArrayList<>())
                 .add(new int[]{i, plays[i]});
        }

        List<String> genreList = new ArrayList<>(total.keySet());
        genreList.sort((a, b) -> total.get(b) - total.get(a));

        List<Integer> result = new ArrayList<>();

        for (String genre : genreList) {
            List<int[]> list = songs.get(genre);

            list.sort((a, b) -> {
                if (a[1] != b[1]) return b[1] - a[1];
                return a[0] - b[0];
            });

            result.add(list.get(0)[0]);
            if (list.size() > 1) {
                result.add(list.get(1)[0]);
            }
        }

        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
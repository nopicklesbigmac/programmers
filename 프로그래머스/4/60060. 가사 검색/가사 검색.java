import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        ArrayList<String>[] normal = new ArrayList[10001];
        ArrayList<String>[] reverse = new ArrayList[10001];

        for (String word : words) {
            int len = word.length();

            if (normal[len] == null) {
                normal[len] = new ArrayList<>();
                reverse[len] = new ArrayList<>();
            }

            normal[len].add(word);
            reverse[len].add(new StringBuilder(word).reverse().toString());
        }

        for (int i = 1; i <= 10000; i++) {
            if (normal[i] != null) {
                Collections.sort(normal[i]);
                Collections.sort(reverse[i]);
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int len = query.length();

            if (normal[len] == null) {
                answer[i] = 0;
                continue;
            }

            if (query.charAt(0) == '?') {
                String reversed = new StringBuilder(query).reverse().toString();
                answer[i] = count(reverse[len], reversed);
            } else {
                answer[i] = count(normal[len], query);
            }
        }

        return answer;
    }

    private int count(ArrayList<String> list, String query) {
        char[] low = query.toCharArray();
        char[] high = query.toCharArray();

        for (int i = 0; i < query.length(); i++) {
            if (query.charAt(i) == '?') {
                low[i] = 'a';
                high[i] = 'z';
            }
        }

        String lower = new String(low);
        String upper = new String(high);

        return upperBound(list, upper) - lowerBound(list, lower);
    }

    private int lowerBound(ArrayList<String> list, String target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid).compareTo(target) >= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int upperBound(ArrayList<String> list, String target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid).compareTo(target) > 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
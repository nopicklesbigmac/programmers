import java.util.*; 
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        Map<String, Integer> cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                return size() > cacheSize;
            }
        };

        for (String city : cities) {
            String lowerCity = city.toLowerCase();

            if (cache.containsKey(lowerCity)) {
                answer += 1;
                cache.get(lowerCity);
            } else {
                answer += 5;
                cache.put(lowerCity, 0);
            }
        }

        return answer;
    }
}
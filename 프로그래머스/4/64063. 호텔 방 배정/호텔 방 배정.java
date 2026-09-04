import java.util.*;
class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        answer = new long[room_number.length];
        Map<Long, Long> map = new HashMap<>();

        for (int i = 0; i < room_number.length; i++) {
            long room = room_number[i];

            if (!map.containsKey(room)) {
                answer[i] = room;
                map.put(room, room + 1);
            } else {
                long next = find(room, map);
                answer[i] = next;
                map.put(next, next + 1);
            }
        }

        return answer;
    }

    private long find(long room, Map<Long, Long> map) {
        if (!map.containsKey(room)) {
            return room;
        }

        long next = find(map.get(room), map);
        map.put(room, next);

        return next;
    }
}
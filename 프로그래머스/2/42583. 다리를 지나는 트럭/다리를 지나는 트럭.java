import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> bridge = new LinkedList<>();
        int bridgeWeight = 0;
        int time = 0;
        int index = 0;

        for (int i = 0; i < bridge_length; i++) {
            bridge.add(0);
        }

        while (index < truck_weights.length) {
            bridgeWeight -= bridge.poll();
            int truck = truck_weights[index];

            if (bridgeWeight + truck <= weight) {
                bridge.add(truck);
                bridgeWeight += truck;
                index++;
            } else {
                bridge.add(0);
            }
            time++;
        }

        answer = time + bridge_length;
        return answer;
    }
}
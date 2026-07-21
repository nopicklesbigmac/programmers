import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inTimeMap = new HashMap<>();
        Map<String, Integer> totalTimeMap = new HashMap<>();
        
        for (String record : records) {
            String[] split = record.split(" ");
            int time = toMinutes(split[0]);
            String carNumber = split[1];
            String type = split[2];
            
            if (type.equals("IN")) {
                inTimeMap.put(carNumber, time);
            } else {
                int startTime = inTimeMap.get(carNumber);
                int duration = time - startTime;
                totalTimeMap.put(carNumber, totalTimeMap.getOrDefault(carNumber, 0) + duration);
                inTimeMap.remove(carNumber);
            }
        }
        
        int endTime = toMinutes("23:59");
        for (String carNumber : inTimeMap.keySet()) {
            int startTime = inTimeMap.get(carNumber);
            int duration = endTime - startTime;
            totalTimeMap.put(carNumber, totalTimeMap.getOrDefault(carNumber, 0) + duration);
        }
        
        List<String> sortedCars = new ArrayList<>(totalTimeMap.keySet());
        Collections.sort(sortedCars);
        
        int[] answer = new int[sortedCars.size()];
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        for (int i = 0; i < sortedCars.size(); i++) {
            String carNumber = sortedCars.get(i);
            int totalTime = totalTimeMap.get(carNumber);
            
            int fee = defaultFee;
            if (totalTime > defaultTime) {
                fee += Math.ceil((double)(totalTime - defaultTime) / unitTime) * unitFee;
            }
            answer[i] = fee;
        }
        
        return answer;
    }
    
    private int toMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}
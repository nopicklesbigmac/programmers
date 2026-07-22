import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        HashMap<String, String> map = new HashMap<>();
        
        for (String r : record) {
            String[] split = r.split(" ");
            String action = split[0];
            String uid = split[1];
            
            if (!action.equals("Leave")) {
                String nickname = split[2];
                map.put(uid, nickname);
            }
        }
        
        List<String> list = new ArrayList<>();
        for (String r : record) {
            String[] split = r.split(" ");
            String action = split[0];
            String uid = split[1];
            
            if (action.equals("Enter")) {
                list.add(map.get(uid) + "님이 들어왔습니다.");
            } else if (action.equals("Leave")) {
                list.add(map.get(uid) + "님이 나갔습니다.");
            }
        }
        answer = list.toArray(new String[0]);
        return answer;
    }
}
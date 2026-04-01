import java.util.ArrayList;
import java.util.List;
class Solution {
    public String[] solution(String[] strArr) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        for (String str : strArr) {
            if (!str.contains("ad")) {
                list.add(str);
            }
        }
        answer = list.toArray(new String[0]);
        return answer;
    }
}
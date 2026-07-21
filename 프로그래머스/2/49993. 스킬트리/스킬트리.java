import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (String skillTree : skill_trees) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < skillTree.length(); i++) {
                char c = skillTree.charAt(i);
                if (skill.indexOf(c) != -1) {
                    sb.append(c);
                }
            }
            String filtered = sb.toString();
            if (skill.startsWith(filtered)) {
                answer++;
            }
        }
        
        return answer;
    }
}
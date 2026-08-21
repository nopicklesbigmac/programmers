import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = {};
        answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            StringBuilder stack = new StringBuilder();
            int count = 0;

            for (int j = 0; j < str.length(); j++) {
                stack.append(str.charAt(j));

                int len = stack.length();
                if (len >= 3
                        && stack.charAt(len - 3) == '1'
                        && stack.charAt(len - 2) == '1'
                        && stack.charAt(len - 1) == '0') {
                    stack.delete(len - 3, len);
                    count++;
                }
            }

            int pos = stack.lastIndexOf("0") + 1;

            StringBuilder result = new StringBuilder();
            result.append(stack, 0, pos);

            for (int j = 0; j < count; j++) {
                result.append("110");
            }

            result.append(stack, pos, stack.length());

            answer[i] = result.toString();
        }

        return answer;
    }
}
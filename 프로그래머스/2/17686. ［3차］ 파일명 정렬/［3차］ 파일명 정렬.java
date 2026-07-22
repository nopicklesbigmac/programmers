import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class Solution {
    public String[] solution(String[] files) {
        String[] answer = {};
        answer = files;
        Pattern pattern = Pattern.compile("^([a-zA-Z\\s.\\-]+?)(\\d{1,5})(.*)$");
        Arrays.sort(files, (s1, s2) -> {
            Matcher m1 = pattern.matcher(s1);
            Matcher m2 = pattern.matcher(s2);

            m1.find();
            m2.find();

            String head1 = m1.group(1).toLowerCase();
            String head2 = m2.group(1).toLowerCase();

            int headResult = head1.compareTo(head2);
            if (headResult != 0) {
                return headResult;
            }

            int num1 = Integer.parseInt(m1.group(2));
            int num2 = Integer.parseInt(m2.group(2));

            return Integer.compare(num1, num2);
        });
        
        return answer;
    }
}
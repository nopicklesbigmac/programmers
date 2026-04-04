class Solution {
    public String[] solution(String myStr) {
        String[] answer = {};
        String[] splitArr = myStr.split("[abc]");
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        for (String s : splitArr) {
            if (!s.isEmpty()) {
                list.add(s);
            }
        }
        answer = list.isEmpty() ? new String[]{"EMPTY"} : list.toArray(new String[0]);
        return answer;
    }
}
class Solution {
    public String solution(String my_string, int[] index_list) {
        String answer = "";
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < index_list.length; i++) {
            int index = index_list[i];
            str.append(my_string.charAt(index));
        }
        answer = str.toString();
        return answer;
    }
}
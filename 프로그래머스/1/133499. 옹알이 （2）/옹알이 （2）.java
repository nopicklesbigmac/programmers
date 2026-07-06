class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] sounds = {"aya", "ye", "woo", "ma"};

        for (String s : babbling) {
            String target = s;

            boolean isPossible = true;
            for (String sound : sounds) {
                if (target.contains(sound + sound)) {
                    isPossible = false;
                    break;
                }
            }

            if (isPossible) {
                for (String sound : sounds) {
                    target = target.replace(sound, " ");
                }

                if (target.replace(" ", "").isEmpty()) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
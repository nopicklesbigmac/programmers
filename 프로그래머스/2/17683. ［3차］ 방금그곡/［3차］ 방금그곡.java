import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        String parsedM = parseMelody(m);
        answer = "(None)";
        int maxPlayTime = -1;

        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");
            int start = toMinutes(info[0]);
            int end = toMinutes(info[1]);
            int playTime = end - start;
            String title = info[2];
            String melody = parseMelody(info[3]);

            StringBuilder sb = new StringBuilder();
            int melodyLen = melody.length();
            for (int j = 0; j < playTime; j++) {
                sb.append(melody.charAt(j % melodyLen));
            }
            String playedMelody = sb.toString();

            if (playedMelody.contains(parsedM)) {
                if (playTime > maxPlayTime) {
                    maxPlayTime = playTime;
                    answer = title;
                }
            }
        }

        return answer;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    private String parseMelody(String melody) {
        melody = melody.replace("C#", "c")
                       .replace("D#", "d")
                       .replace("F#", "f")
                       .replace("G#", "g")
                       .replace("A#", "a")
                       .replace("B#", "b");
        return melody;
    }
}
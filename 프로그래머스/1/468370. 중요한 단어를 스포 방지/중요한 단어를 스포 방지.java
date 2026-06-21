import java.util.*;

class Solution {
    static class Word {
        String val;
        int start;
        int end;

        Word(String val, int start, int end) {
            this.val = val;
            this.start = start;
            this.end = end;
        }
    }

    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;
        int len = message.length();
        
        boolean[] isSpoilerPos = new boolean[len];
        for (int[] range : spoiler_ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                isSpoilerPos[i] = true;
            }
        }

        List<Word> wordList = new ArrayList<>();
        int start = -1;
        for (int i = 0; i < len; i++) {
            if (message.charAt(i) != ' ') {
                if (start == -1) {
                    start = i;
                }
            }
            if (message.charAt(i) == ' ' || i == len - 1) {
                if (start != -1) {
                    int end = (message.charAt(i) == ' ') ? i - 1 : i;
                    wordList.add(new Word(message.substring(start, end + 1), start, end));
                    start = -1;
                }
            }
        }

        Set<String> normalWords = new HashSet<>();
        for (Word w : wordList) {
            boolean isFullyNormal = true;
            for (int i = w.start; i <= w.end; i++) {
                if (isSpoilerPos[i]) {
                    isFullyNormal = false;
                    break;
                }
            }
            if (isFullyNormal) {
                normalWords.add(w.val);
            }
        }

        Set<String> visitedSpoilerWords = new HashSet<>();
        
        for (int[] range : spoiler_ranges) {
            int sRange = range[0];
            int eRange = range[1];
            
            for (Word w : wordList) {
                if (w.start <= eRange && w.end >= sRange) {
                    boolean isSpoilerWord = false;
                    for (int i = w.start; i <= w.end; i++) {
                        if (isSpoilerPos[i]) {
                            isSpoilerWord = true;
                            break;
                        }
                    }
                    
                    if (isSpoilerWord) {
                        if (!normalWords.contains(w.val) && !visitedSpoilerWords.contains(w.val)) {
                            answer++;
                        }
                        visitedSpoilerWords.add(w.val);
                    }
                }
            }
        }

        return answer;
    }
}
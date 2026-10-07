import java.util.*;

class Solution {
    String inner(String sentence, int l, int r, int[] cnt) {
        String sub = sentence.substring(l, r);
        int len = sub.length();
        boolean all = true;
        for (int k = 0; k < len; k++) {
            if (!Character.isUpperCase(sub.charAt(k))) {
                all = false;
                break;
            }
        }
        if (all) return sub;
        if (len < 3 || len % 2 == 0) return null;
        char t = sub.charAt(1);
        if (!Character.isLowerCase(t)) return null;
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < len; k++) {
            char c = sub.charAt(k);
            if (k % 2 == 0) {
                if (!Character.isUpperCase(c)) return null;
                sb.append(c);
            } else if (c != t) {
                return null;
            }
        }
        if (cnt[t] != (len - 1) / 2) return null;
        return sb.toString();
    }

    public String solution(String sentence) {
        int n = sentence.length();
        int[] cnt = new int[128];
        for (int i = 0; i < n; i++) cnt[sentence.charAt(i)]++;
        boolean[] ok = new boolean[n + 2];
        int[] nextPos = new int[n + 1];
        String[] wordOf = new String[n + 1];
        ok[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            char ch = sentence.charAt(i);
            if (Character.isLowerCase(ch)) {
                if (cnt[ch] != 2) continue;
                int j = sentence.indexOf(ch, i + 1);
                if (j <= i + 1) continue;
                String w = inner(sentence, i + 1, j, cnt);
                if (w != null && ok[j + 1]) {
                    ok[i] = true;
                    nextPos[i] = j + 1;
                    wordOf[i] = w;
                }
            } else {
                if (i + 1 < n && Character.isLowerCase(sentence.charAt(i + 1))) {
                    char s = sentence.charAt(i + 1);
                    int c = cnt[s];
                    StringBuilder sb = new StringBuilder();
                    sb.append(ch);
                    int p = i;
                    int occ = 0;
                    while (occ < c && p + 2 < n && sentence.charAt(p + 1) == s && Character.isUpperCase(sentence.charAt(p + 2))) {
                        sb.append(sentence.charAt(p + 2));
                        occ++;
                        p += 2;
                    }
                    if (occ == c && ok[p + 1]) {
                        ok[i] = true;
                        nextPos[i] = p + 1;
                        wordOf[i] = sb.toString();
                        continue;
                    }
                }
                if (ok[i + 1]) {
                    ok[i] = true;
                    nextPos[i] = i + 1;
                    wordOf[i] = null;
                }
            }
        }
        if (!ok[0]) return "invalid";
        List<StringBuilder> words = new ArrayList<>();
        boolean prevPlain = false;
        int i = 0;
        while (i < n) {
            String w = wordOf[i];
            if (w == null) {
                if (prevPlain) {
                    words.get(words.size() - 1).append(sentence.charAt(i));
                } else {
                    words.add(new StringBuilder().append(sentence.charAt(i)));
                }
                prevPlain = true;
            } else {
                words.add(new StringBuilder(w));
                prevPlain = false;
            }
            i = nextPos[i];
        }
        StringBuilder result = new StringBuilder();
        for (int k = 0; k < words.size(); k++) {
            if (k > 0) result.append(' ');
            result.append(words.get(k));
        }
        return result.toString();
    }
}
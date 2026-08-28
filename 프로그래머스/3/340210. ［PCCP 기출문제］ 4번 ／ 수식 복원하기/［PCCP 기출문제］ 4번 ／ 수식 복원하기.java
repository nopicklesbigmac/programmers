import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        List<String> answer = new ArrayList<>();
        List<String[]> list = new ArrayList<>();

        boolean[] possible = new boolean[10];
        Arrays.fill(possible, true);

        for (String expression : expressions) {
            String[] p = expression.split(" ");
            list.add(p);

            for (int base = 2; base <= 9; base++) {
                if (!possible[base]) continue;

                if (!valid(p[0], base) || !valid(p[2], base)) {
                    possible[base] = false;
                    continue;
                }

                if (!p[4].equals("X") && !valid(p[4], base)) {
                    possible[base] = false;
                    continue;
                }

                if (!p[4].equals("X")) {
                    int a = Integer.parseInt(p[0]);
                    int b = Integer.parseInt(p[2]);
                    int c = Integer.parseInt(p[4]);

                    int av = convert(a, base);
                    int bv = convert(b, base);
                    int cv = convert(c, base);

                    if (p[1].equals("+")) {
                        if (av + bv != cv) possible[base] = false;
                    } else {
                        if (av - bv != cv) possible[base] = false;
                    }
                }
            }
        }

        for (String[] p : list) {
            if (!p[4].equals("X")) continue;

            String result = null;
            boolean same = true;

            for (int base = 2; base <= 9; base++) {
                if (!possible[base]) continue;

                int a = convert(Integer.parseInt(p[0]), base);
                int b = convert(Integer.parseInt(p[2]), base);

                int value;
                if (p[1].equals("+")) {
                    value = a + b;
                } else {
                    value = a - b;
                }

                String converted = toBase(value, base);

                if (result == null) {
                    result = converted;
                } else if (!result.equals(converted)) {
                    same = false;
                    break;
                }
            }

            answer.add(p[0] + " " + p[1] + " " + p[2] + " = " + (same ? result : "?"));
        }

        return answer.toArray(new String[0]);
    }

    private boolean valid(String s, int base) {
        for (char c : s.toCharArray()) {
            if (c - '0' >= base) return false;
        }
        return true;
    }

    private int convert(int value, int base) {
        int result = 0;
        int digit = 1;

        while (value > 0) {
            result += (value % 10) * digit;
            value /= 10;
            digit *= base;
        }

        return result;
    }

    private String toBase(int value, int base) {
        if (value == 0) return "0";

        StringBuilder sb = new StringBuilder();

        while (value > 0) {
            sb.append(value % base);
            value /= base;
        }

        return sb.reverse().toString();
    }
}
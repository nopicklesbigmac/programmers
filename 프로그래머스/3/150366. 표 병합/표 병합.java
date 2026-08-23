import java.util.*;
class Solution {
    public String[] solution(String[] commands) {
         int[][] parent = new int[51][51];
        String[][] value = new String[51][51];

        for (int r = 1; r <= 50; r++) {
            for (int c = 1; c <= 50; c++) {
                parent[r][c] = r * 51 + c;
            }
        }

        List<String> result = new ArrayList<>();

        for (String command : commands) {
            String[] p = command.split(" ");

            if (p[0].equals("UPDATE")) {
                if (p.length == 4) {
                    int r = Integer.parseInt(p[1]);
                    int c = Integer.parseInt(p[2]);
                    String v = p[3];

                    int root = find(parent, r * 51 + c);
                    int rr = root / 51;
                    int cc = root % 51;

                    value[rr][cc] = v;
                } else {
                    String oldValue = p[1];
                    String newValue = p[2];

                    for (int r = 1; r <= 50; r++) {
                        for (int c = 1; c <= 50; c++) {
                            int root = find(parent, r * 51 + c);
                            int rr = root / 51;
                            int cc = root % 51;

                            if (oldValue.equals(value[rr][cc])) {
                                value[rr][cc] = newValue;
                            }
                        }
                    }
                }

            } else if (p[0].equals("MERGE")) {
                int r1 = Integer.parseInt(p[1]);
                int c1 = Integer.parseInt(p[2]);
                int r2 = Integer.parseInt(p[3]);
                int c2 = Integer.parseInt(p[4]);

                int a = find(parent, r1 * 51 + c1);
                int b = find(parent, r2 * 51 + c2);

                if (a == b) {
                    continue;
                }

                int ar = a / 51;
                int ac = a % 51;
                int br = b / 51;
                int bc = b % 51;

                String mergedValue;

                if (value[ar][ac] != null) {
                    mergedValue = value[ar][ac];
                } else {
                    mergedValue = value[br][bc];
                }

                parent[br][bc] = a;
                value[ar][ac] = mergedValue;
                value[br][bc] = null;

            } else if (p[0].equals("UNMERGE")) {
                int r = Integer.parseInt(p[1]);
                int c = Integer.parseInt(p[2]);

                int root = find(parent, r * 51 + c);
                int rr = root / 51;
                int cc = root % 51;

                String mergedValue = value[rr][cc];

                List<Integer> members = new ArrayList<>();

                for (int i = 1; i <= 50; i++) {
                    for (int j = 1; j <= 50; j++) {
                        int current = i * 51 + j;

                        if (find(parent, current) == root) {
                            members.add(current);
                        }
                    }
                }

                for (int member : members) {
                    int mr = member / 51;
                    int mc = member % 51;

                    parent[mr][mc] = member;
                    value[mr][mc] = null;
                }

                value[r][c] = mergedValue;

            } else if (p[0].equals("PRINT")) {
                int r = Integer.parseInt(p[1]);
                int c = Integer.parseInt(p[2]);

                int root = find(parent, r * 51 + c);
                int rr = root / 51;
                int cc = root % 51;

                if (value[rr][cc] == null) {
                    result.add("EMPTY");
                } else {
                    result.add(value[rr][cc]);
                }
            }
        }

        return result.toArray(new String[0]);
    }

    private int find(int[][] parent, int x) {
        int r = x / 51;
        int c = x % 51;

        if (parent[r][c] == x) {
            return x;
        }

        int root = find(parent, parent[r][c]);

        parent[r][c] = root;

        return root;
    }
}
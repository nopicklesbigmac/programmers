import java.util.*;

class Solution {
    static class Assignment {
        String name;
        int start, playtime;

        Assignment(String name, String start, int playtime) {
            this.name = name;
            String[] t = start.split(":");
            this.start = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            this.playtime = playtime;
        }
    }

    public String[] solution(String[][] plans) {
        Assignment[] list = new Assignment[plans.length];
        for (int i = 0; i < plans.length; i++) {
            list[i] = new Assignment(plans[i][0], plans[i][1], Integer.parseInt(plans[i][2]));
        }
        Arrays.sort(list, Comparator.comparingInt(a -> a.start));

        List<String> answer = new ArrayList<>();
        Stack<Assignment> stack = new Stack<>();

        for (int i = 0; i < list.length; i++) {
            Assignment cur = list[i];
            int time = cur.start;

            if (i < list.length - 1) {
                int nextTime = list[i + 1].start;
                int diff = nextTime - time;

                if (cur.playtime <= diff) {
                    time += cur.playtime;
                    answer.add(cur.name);

                    while (!stack.isEmpty() && time < nextTime) {
                        Assignment p = stack.peek();
                        int rem = nextTime - time;
                        if (p.playtime <= rem) {
                            time += p.playtime;
                            answer.add(p.name);
                            stack.pop();
                        } else {
                            p.playtime -= rem;
                            time = nextTime;
                        }
                    }
                } else {
                    cur.playtime -= diff;
                    stack.push(cur);
                }
            } else {
                answer.add(cur.name);
            }
        }

        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        return answer.toArray(new String[0]);
    }
}
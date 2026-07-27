import java.util.ArrayList;
import java.util.List;
class Solution {
    public long solution(String expression) {
        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        int num = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                numbers.add((long) num);
                num = 0;
                operators.add(c);
            } else {
                num = num * 10 + (c - '0');
            }
        }
        numbers.add((long) num);
        
        String[][] priorities = {
            {"+", "-", "*"},
            {"+", "*", "-"},
            {"-", "+", "*"},
            {"-", "*", "+"},
            {"*", "+", "-"},
            {"*", "-", "+"}
        };
        
        long maxResult = 0;
        
        for (String[] priority : priorities) {
            List<Long> tempNumbers = new ArrayList<>(numbers);
            List<Character> tempOperators = new ArrayList<>(operators);
            
            for (String opStr : priority) {
                char op = opStr.charAt(0);
                for (int i = 0; i < tempOperators.size(); i++) {
                    if (tempOperators.get(i) == op) {
                        long res = calc(tempNumbers.get(i), tempNumbers.get(i + 1), op);
                        tempNumbers.remove(i + 1);
                        tempNumbers.set(i, res);
                        tempOperators.remove(i);
                        i--;
                    }
                }
            }
            
            long result = Math.abs(tempNumbers.get(0));
            maxResult = Math.max(maxResult, result);
        }
        
        return maxResult;
    }
    
    private long calc(long a, long b, char op) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b;
    }
}
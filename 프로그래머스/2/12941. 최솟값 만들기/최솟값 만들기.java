import java.util.Arrays;
import java.util.Collections;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        System.out.println("Hello Java");

        Arrays.sort(A);
        
        Integer[] bObj = new Integer[B.length];
        for (int i = 0; i < B.length; i++) {
            bObj[i] = B[i];
        }
        Arrays.sort(bObj, Collections.reverseOrder());
        for (int i = 0; i < A.length; i++) {
            answer += A[i] * bObj[i];
        }
        
        
        return answer;
    }
}
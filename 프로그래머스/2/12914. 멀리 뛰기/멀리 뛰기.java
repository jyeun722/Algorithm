class Solution {
    public long solution(int n) {
        long first = 1;
        long second = 2;
        
        if (n == 1) return 1;
        else if (n == 2) return 2;
        else {
            long num = 2, answer = 0;
            while (num++ != n) {
                answer = (first + second) % 1234567;
                first = second % 1234567;
                second = answer % 1234567;
            }
            
            return answer;
        }
    }
}
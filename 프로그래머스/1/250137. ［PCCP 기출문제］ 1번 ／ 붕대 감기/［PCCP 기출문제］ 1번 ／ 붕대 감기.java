class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = 0;
       int maxHealth = health;
        int currHealth = health;
        int currTime = 0;
        int success = 0;

        for (int[] attack : attacks) {
            int attackTime = attack[0];
            int damage = attack[1];

            while (currTime < attackTime) {
                currTime++;
                if (currTime < attackTime) {
                    currHealth += bandage[1];
                    success++;
                    if (success == bandage[0]) {
                        currHealth += bandage[2];
                        success = 0;
                    }
                    if (currHealth > maxHealth) {
                        currHealth = maxHealth;
                    }
                }
            }

            currHealth -= damage;
            success = 0;
            currTime = attackTime;

            if (currHealth <= 0) {
                return -1;
            }
        }

        answer = currHealth;
        return answer;
    }
}
import java.util.Random;

public class BallroomDancing {
    static final int NUM_COUPLES = 10;
    static final int NUM_EVENTS = 3;
    static final int NUM_JUDGES = 8;
    static final int MIN_SCORE = 90;
    static final int MAX_SCORE = 99;

    public static int countOccurrences(int[] scores, int target)
    {
        int numoftimes = 0;
        for(int i : scores){
            if(scores[i] == target){
                numoftimes++;
            }
        }
        return numoftimes;
    }

    public static int[] findMaxAndMin(int[] scores)
    {
        int [] minmax = {};
        int max = 0;
        int min = 0;
        for(int i = 1; i < scores.length; i++){
            if(scores[i]<min){
                min = scores[i];
            }
            if(scores[i]>max){
                max = scores[i];
            }
        }
        minmax[0] = max;
        minmax[1] = min;
        return minmax;
    }  


    public static double averageScore(int[] scores){
        double average = 0;
            int total = 0;
            int [] minmax = findMaxAndMin(scores);
            int min = minmax[1];
            int max = minmax[0];
            int minrecur = countOccurrences(scores, max);
            int maxrecur = countOccurrences(scores, min);
            for(int i : scores){
                total+=i;
            }
            if(scores.length >= 6 && minrecur == 1){
                total-=min;
            }
            if(scores.length >= 6 && maxrecur == 1){
                total-=max;
            }  
            average = (double)((total)/scores.length);
        return average;
    }

    public static void main(String[] args) {
        int[] totalScores = new int[NUM_COUPLES];
        Random random = new Random();

        for (int couple = 0; couple < NUM_COUPLES; couple++) {
            int total = 0;
            System.out.println("Couple #" + (couple + 1) + ":");
            for (int event = 0; event < NUM_EVENTS; event++) {
                int[] scores = new int[NUM_JUDGES];
                for (int judge = 0; judge < NUM_JUDGES; judge++) {
                    scores[judge] = random.nextInt(MAX_SCORE - MIN_SCORE + 1) + MIN_SCORE;
                }

                double avg = averageScore(scores);
                total += Math.round(avg);
                System.out.print("   Event " + (event + 1) + " ");
                System.out.print(java.util.Arrays.toString(scores) + " Avg = " + String.format("%.2f", avg) + "\n");
            }
            totalScores[couple] = total;
            System.out.println("Total score: " + total + "\n");
        }

        int bestScore = 0;
        int countBest = 0;

        for (int score : totalScores) {
            if (score > bestScore) {
                bestScore = score;
                countBest = 1;
            } else if (score == bestScore) {
                countBest++;
            }
        }

        System.out.println("Final scores:");
        for (int score : totalScores) {
            System.out.print(score + " ");
        }
        System.out.println("\nBest score: " + bestScore + " Shared by " + countBest + " winners.");
    }
}

package matrixchain;

public class DynamicPrograming {
    public static int[][] noOfMultiplication; // storage for the number of multiplications
    public static int[][] matrixMultiplicationOrder; // storage for the order
    public static int noOfMatrices; // number of matrices in the product

    // the bottom-top implementation of the dynamic programming method
    public static void dynamicProgramingMethod(int[] p) {
        noOfMatrices = p.length - 1;     // number of matrices in the product
        noOfMultiplication = new int[noOfMatrices + 1][noOfMatrices + 1];  // create and automatically initialize array m
        matrixMultiplicationOrder = new int[noOfMatrices + 1][noOfMatrices + 1];
        for (int i = 1; i <= noOfMatrices; i++)
            noOfMultiplication[i][i] = 0;
        // Solve for chains of increasing length l.
        for (int l = 2; l <= noOfMatrices; l++) {
            for (int i = 1; i <= noOfMatrices - l + 1; i++) {
                int j = i + l - 1;
                noOfMultiplication[i][j] = Integer.MAX_VALUE;
                // Check each possible split to see if it's better
                // than all seen so far.
                for (int k = i; k < j; k++) {
                    int q = noOfMultiplication[i][k] + noOfMultiplication[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < noOfMultiplication[i][j]) {
                        // q is the best split for this subproblem so far.
                        noOfMultiplication[i][j] = q;
                        matrixMultiplicationOrder[i][j] = k;
                    }
                }
            }
        }
    }

    // algorithm for printing out optimal multiplication order
    public static void printOptimalParentheses(int i, int j) {
        if (i == j) {
            System.out.print("A[" + i + "]");
        } else {
            System.out.print("(");
            printOptimalParentheses(i, matrixMultiplicationOrder[i][j]);
            printOptimalParentheses(matrixMultiplicationOrder[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
//        int input[] = {40, 20};
//        int input[] = {40, 20, 30};
//        int input[] = {40, 20, 30, 10};
//        int input[] = {40, 20, 30, 10, 30};
//        int input[] = {40, 20, 30, 10, 30, 50};
//        int input[] = {40, 20, 30, 10, 30, 50, 5};
//        int input[] = {40, 20, 30, 10, 30, 50, 5, 25};
//        int input[] = {40, 20, 30, 10, 30, 50, 5, 25, 15};
//        int input[] = {40, 20, 30, 10, 30, 50, 5, 25, 15, 25};
        int input[] = {40, 20, 30, 10, 30, 50, 5, 25, 15, 25, 35};
        System.out.println("######Using bottom-top Dyn. Prog. method:");
        long startTime = System.nanoTime();
        dynamicProgramingMethod(input);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Elapsed Time for dynamic programming method in nano seconds: " + duration);
        System.out.println("Table of m[i][j]:");
        System.out.print("j\\i|");
        for (int i = 1; i <= noOfMatrices; i++)
            System.out.printf("%5d ", i);
        System.out.print("\n---+");
        for (int i = 1; i <= 6 * noOfMatrices - 1; i++)
            System.out.print("-");
        System.out.println();
        for (int j = noOfMatrices; j >= 1; j--) {
            System.out.print(" " + j + " |");
            for (int i = 1; i <= j; i++)
                System.out.printf("%5d ", noOfMultiplication[i][j]);
            System.out.println();
        }
        System.out.println("Min number of multiplications: " + noOfMultiplication[1][noOfMatrices] + "\n");
        System.out.println("Table of s[i][j]:");
        System.out.print("j\\i|");
        for (int i = 1; i <= noOfMatrices; i++)
            System.out.printf("%2d ", i);
        System.out.print("\n---+");
        for (int i = 1; i <= 3 * noOfMatrices - 1; i++)
            System.out.print("-");
        System.out.println();
        for (int j = noOfMatrices; j >= 2; j--) {
            System.out.print(" " + j + " |");
            for (int i = 1; i <= j - 1; i++)
                System.out.printf("%2d ", matrixMultiplicationOrder[i][j]);
            System.out.println();
        }
        System.out.print("Optimal multiplication order: ");
        printOptimalParentheses(1, noOfMatrices);
        System.out.println("\n");
    }
}

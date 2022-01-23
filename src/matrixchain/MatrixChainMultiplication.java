package matrixchain;

import java.util.Scanner;

public class MatrixChainMultiplication {
    // Matrix Ai has dimension p[i-1] x p[i] for i = 1..n
    public static int MatrixChainOrder(int p[], int i, int j) {
        if (i == j)
            return 0;

        int min = Integer.MAX_VALUE;

        // place parenthesis at different places between first
        // and last matrix, recursively calculate count of
        // multiplications for each parenthesis placement and
        // return the minimum count
        for (int k = i; k < j; k++) {
            int count = MatrixChainOrder(p, i, k) +
                    MatrixChainOrder(p, k + 1, j) +
                    p[i - 1] * p[k] * p[j];

            if (count < min)
                min = count;
        }

        // Return minimum count
        return min;
    }

    // Driver program to test above function
    public static void main(String args[]) {
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
        int n = input.length;
        System.out.println("######Using a recursive non Dyn. Prog. method:");
        long startTime = System.nanoTime();
        int minNoOfMultiplication = MatrixChainOrder(input, 1, n - 1);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Elapsed Time for recursive version in nano seconds: " + duration);
//        System.out.println("Minimum number of multiplications is " + minNoOfMultiplication);
    }
}

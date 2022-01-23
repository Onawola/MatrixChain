package matrixchain;

public class MemoizedVersion {
    public static int[][] noOfMultiplication; // storage for the number of multiplications
    public static int noOfMatrices; // number of matrices in the product

    // memoized version of matrix-chain multiplication
    public static int memoizedVersionMethod(int[] p) {
        noOfMatrices = p.length - 1;
        noOfMultiplication = new int[noOfMatrices + 1][noOfMatrices + 1];
        for (int i = 0; i <= noOfMatrices; i++)
            for (int j = i; j <= noOfMatrices; j++)
                noOfMultiplication[i][j] = Integer.MAX_VALUE;
        return (lookUpChain(p, 1, noOfMatrices));
    }

    // auxilary method involved by matrix-chain multiplication
    public static int lookUpChain(int[] p, int i, int j) {
        if (noOfMultiplication[i][j] < Integer.MAX_VALUE) return (noOfMultiplication[i][j]);

        if (i == j) noOfMultiplication[i][j] = 0;
        else {
            for (int k = i; k < j; k++) {
                int q = lookUpChain(p, i, k) + lookUpChain(p, k + 1, j) + p[i - 1] * p[k] * p[j];
                if (q < noOfMultiplication[i][j])
                    noOfMultiplication[i][j] = q;
            }
        }
        return (noOfMultiplication[i][j]);
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
        System.out.println("######Using top-bottom Dyn. Prog. method:");
        long startTime = System.nanoTime();
        int minNoOfMultiplication = memoizedVersionMethod(input);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("Elapsed Time for memoized version in nano seconds: " + duration);
        System.out.println("Min number of multiplications: " + minNoOfMultiplication);
    }
}

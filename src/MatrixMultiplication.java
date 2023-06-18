public class MatrixMultiplication {

    /**
     *
     * Brute Force algorithm for matrix multiplication
     */
    public static int[][] bruteForce(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < B.length; j++) {
                C[i][j] = 0;
                for (int k = 0; k < A.length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    /**
     *
     *strassen's algorithm that recurses down to 1x1 matrices, no cutoff.
     */
    public static int[][] strassensNoCutoff(int[][]A, int[][]B) {
        int n = A.length;

        int[][] ResultMatrix = new int[n][n];

        if (n==1) { //Recursion down to 1x1 matrices
            ResultMatrix[0][0] = A[0][0] * B[0][0];
        }
        else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            //splitting mattrix A and B into 4 quarters.
            splitMatrix(A, A11, 0, 0);
            splitMatrix(A, A12, 0, n / 2);
            splitMatrix(A, A21, n / 2, 0);
            splitMatrix(A, A22, n / 2, n / 2);

            splitMatrix(B, B11, 0, 0);
            splitMatrix(B, B12, 0, n / 2);
            splitMatrix(B, B21, n / 2, 0);
            splitMatrix(B, B22, n / 2, n / 2);

            int[][] M1 = strassensNoCutoff(addMatrices(A11, A22), addMatrices(B11, B22));
            int[][] M2 = strassensNoCutoff(addMatrices(A21, A22), B11);
            int[][] M3 = strassensNoCutoff(A11, subtractMatrices(B12, B22));
            int[][] M4 = strassensNoCutoff(A22, subtractMatrices(B21, B11));
            int[][] M5 = strassensNoCutoff(addMatrices(A11, A12), B22);
            int[][] M6 = strassensNoCutoff(subtractMatrices(A21, A11), addMatrices(B11, B12));
            int[][] M7 = strassensNoCutoff(subtractMatrices(A12, A22), addMatrices(B21, B22));
            int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
            int[][] C12 = addMatrices(M3, M5);
            int[][] C21 = addMatrices(M2, M4);
            int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

            //joining the 4 quarters back
            joinMatrices(C11, ResultMatrix, 0, 0);
            joinMatrices(C12, ResultMatrix, 0, n / 2);
            joinMatrices(C21, ResultMatrix, n / 2, 0);
            joinMatrices(C22, ResultMatrix, n / 2, n / 2);
        }
        return ResultMatrix;
    }

    /**
     *
     * @param A - matrix A
     * @param B - matrix B
     * @param cutoff - some 2^n number we use to tell the algorithm when to switch to brute force
     * @return - resulting matrix
     *
     * strassen's algorithm that recurses down until you reach a specified cutoff point(some 2^n value), to which you finish
     * the rest of the multiplications through brute force.
     */
    public static int[][] strassensWithCutoff(int[][]A, int[][]B, int cutoff) {
        int n = A.length;

        int[][] ResultMatrix = new int[n][n];

        if (n<=cutoff) {
            return bruteForce(A,B); //This is where the cutoff determines when we switch to strassen's
        }
        else {
            //creating 4 matrices for the A matrix
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];

            //creating 4 matrices for the B matrix
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            //splitting mattrix A and B into 4 quarters.
            splitMatrix(A, A11, 0, 0);
            splitMatrix(A, A12, 0, n / 2);
            splitMatrix(A, A21, n / 2, 0);
            splitMatrix(A, A22, n / 2, n / 2);

            splitMatrix(B, B11, 0, 0);
            splitMatrix(B, B12, 0, n / 2);
            splitMatrix(B, B21, n / 2, 0);
            splitMatrix(B, B22, n / 2, n / 2);


            int[][] M1 = strassensWithCutoff(addMatrices(A11, A22), addMatrices(B11, B22), cutoff);
            int[][] M2 = strassensWithCutoff(addMatrices(A21, A22), B11, cutoff);
            int[][] M3 = strassensWithCutoff(A11, subtractMatrices(B12, B22), cutoff);
            int[][] M4 = strassensWithCutoff(A22, subtractMatrices(B21, B11), cutoff);
            int[][] M5 = strassensWithCutoff(addMatrices(A11, A12), B22, cutoff);
            int[][] M6 = strassensWithCutoff(subtractMatrices(A21, A11), addMatrices(B11, B12), cutoff);
            int[][] M7 = strassensWithCutoff(subtractMatrices(A12, A22), addMatrices(B21, B22), cutoff);

            int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
            int[][] C12 = addMatrices(M3, M5);
            int[][] C21 = addMatrices(M2, M4);
            int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

            //joining the 4 quarters back
            joinMatrices(C11, ResultMatrix, 0, 0);
            joinMatrices(C12, ResultMatrix, 0, n / 2);
            joinMatrices(C21, ResultMatrix, n / 2, 0);
            joinMatrices(C22, ResultMatrix, n / 2, n / 2);
        }
        return ResultMatrix;
    }

    /**
     *Splitting up a matrix and returning a matrix 1/4 of the size that was inputted.
     * iB and jB is telling us which corner of the matrix we are iterating through and returning.
     */
    public static void splitMatrix(int[][] P, int[][] C, int iB, int jB) {

        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) { //iterating over the 2D array, only iterating over only one of the 4 corners that is being split.
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    /**
     *
     * function to subtract two matrices
     */
    public static int[][] subtractMatrices(int[][] A, int[][] B)
    {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }

    /**
     *
     * function to add two matrices
     */
    public static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    /**
     *
     * function to join the current result matrix and another one.
     */
    public static void joinMatrices(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }
}

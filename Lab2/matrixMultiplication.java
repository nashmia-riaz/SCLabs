package com.company;

/**
 * Created by nashm on 22/02/2017.
 */
public class matrixMultiplication {
    public Matrix iterativeMethod(Matrix mat1, Matrix mat2) {
        //isolate matrices into 2D arrays for multiplication
        int A[][] = mat1.mat;
        int B[][] = mat2.mat;

        int Cr = mat1.rows;
        int Cc = mat2.cols;
        int common = mat2.rows;
        int sum = 0;

        Matrix ans = new Matrix(Cr, Cc);
        int C[][] = new int[Cr][Cc];
        for (int i = 0; i < Cr; i++) {
            for (int j = 0; j < Cc; j++) {
                sum = 0;
                for (int k = 0; k < common; k++)
                    sum = sum + A[i][k] * B[k][j];

                C[i][j] = sum;
            }
        }

        //convert C (2D array) back to matrix
        ans.mat = C;
        return ans;
    }

    public void split(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }

    public void join(int[][] C, int[][] P, int lenB, int rowB) {
        for (int i1 = 0, i2 = lenB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = rowB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    //subtract matrices
    public int[][] sub(int[][] A, int[][] B) {
        int length = A.length;
        int[][] C = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    //add matrices
    public int[][] add(int[][] A, int[][] B) {
        int length = A.length;
        int[][] C = new int[length][length];
        for (int i = 0; i < length; i++)
            for (int j = 0; j < length; j++)
                C[i][j] = A[i][j] + B[i][j];
        return C;
    }

    public Matrix strassenMethod(Matrix mat1, Matrix mat2) {
        mat1.addPadding();
        mat2.addPadding();

        int[][] A = mat1.mat;
        int[][] B = mat2.mat;
        int n = mat1.rows;
        int[][] C = new int[n][n];
        Matrix result = new Matrix(n, n);

        if (n == 1)
            C[0][0] = A[0][0] * B[0][0];
        else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            //split matrix A
            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);

            //split matrix B
            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            Matrix x = new Matrix(n / 2, n / 2);
            Matrix y = new Matrix(n / 2, n / 2);

            x.mat = add(A11, A22);
            y.mat = add(B11, B22);
            Matrix M1 = strassenMethod(x, y);

            x.mat = add(A21, A22);
            y.mat = B11;
            Matrix M2 = strassenMethod(x, y);

            x.mat = A11;
            y.mat = sub(B12, B22);
            Matrix M3 = strassenMethod(x, y);

            x.mat = A22;
            y.mat = sub(B21, B11);
            Matrix M4 = strassenMethod(x, y);

            x.mat = add(A11, A12);
            y.mat = B22;
            Matrix M5 = strassenMethod(x, y);

            x.mat = sub(A21, A11);
            y.mat = add(B11, B12);
            Matrix M6 = strassenMethod(x, y);

            x.mat = sub(A12, A22);
            y.mat = add(B21, B22);
            Matrix M7 = strassenMethod(x, y);

            int[][] C11 = add(sub(add(M1.mat, M4.mat), M5.mat), M7.mat);
            int[][] C12 = add(M3.mat, M5.mat);
            int[][] C21 = add(M2.mat, M4.mat);
            int[][] C22 = add(sub(add(M1.mat, M3.mat), M2.mat), M6.mat);

            join(C11, C, 0, 0);
            join(C12, C, 0, n / 2);
            join(C21, C, n / 2, 0);
            join(C22, C, n / 2, n / 2);
        }

        result.mat = C;
        return result;
    }
}


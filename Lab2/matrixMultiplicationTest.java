package com.company;

import static org.junit.Assert.*;

/**
 * Created by nashm on 22/02/2017.
 */
public class matrixMultiplicationTest {
    public Matrix m = new Matrix(2,2);
    public Matrix n = new Matrix(2,2);
    public Matrix o = new Matrix(4,4);
    public matrixMultiplication multiplier = new matrixMultiplication();
    public Matrix result = new Matrix (2,2);

    @org.junit.Test
    public void iterativeMethod() throws Exception {
        int[][] x = new int[2][2];
        for(int i =0; i< 2; i++){
            for(int j=0; j<2; j++){
                x[i][j]=2;
            }
        }
        m.mat = x;
        result.mat = x;

        int [][]y = new int[2][2];
        for(int i =0; i< m.rows; i++){
            for(int j=0; j<n.cols; j++){
                y[i][j]=1;
            }
        }
        n.mat = y;
        m.printMatrix();
        n.printMatrix();
        result.printMatrix();
        assertEquals("Identity matrix X 2 matrix is [ 2 2 ] [ 2 2 ]",result,multiplier.iterativeMethod(m,n));
    }

    @org.junit.Test
    public void strassenMethod() throws Exception {
        int[][] x = new int[2][2];
        for(int i =0; i< 2; i++){
            for(int j=0; j<2; j++){
                x[i][j]=2;
            }
        }
        m.mat = x;
        result.mat = x;

        int [][]y = new int[2][2];
        for(int i =0; i< m.rows; i++){
            for(int j=0; j<n.cols; j++){
                y[i][j]=1;
            }
        }
        n.mat = y;
        m.printMatrix();
        n.printMatrix();
        result.printMatrix();
        Matrix i = multiplier.strassenMethod(m,n);
        assertEquals("Identity matrix X 2 matrix is [ 2 2 ] [ 2 2 ]",m.mat,i.mat);
    }

}
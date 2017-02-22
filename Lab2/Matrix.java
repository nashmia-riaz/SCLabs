package com.company; /**
 * Created by nashm on 22/02/2017.
 */
import java.util.Scanner;

public class Matrix {
    public int[][] mat;
    public int cols;
    public int rows;

    public Matrix(int r, int c){
        rows = r;
        cols = c;

        mat = new int[r][c];
    }

    public void enterMatrix(){
        System.out.println("Enter the matrix row wise");
        for(int i =0; i< rows; i++){
            for(int j=0; j<cols; j++){
                Scanner input = new Scanner(System.in);
                mat[i][j] = input.nextInt();
            }
        }
    }


    public void printMatrix(){
        for(int i =0; i< rows; i++){
            for(int j=0; j<cols; j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public void addPadding(){
        int x;

        if (rows>cols)
            x= rows;
        else
            x = cols;

        int size = 1;
        while (size < x){
            size *=2;
        }

        Matrix newMatrix= new Matrix(size, size);

        for (int i =0; i < size; i++){
            for (int j =0; j< size; j++){
                if(i>=rows)
                    newMatrix.mat[i][j]=0;
                else if (j>=cols)
                    newMatrix.mat[i][j]=0;
                else
                    newMatrix.mat[i][j] = mat[i][j];
            }
        }

        mat = newMatrix.mat;
        rows=size;
        cols=size;

    }
}

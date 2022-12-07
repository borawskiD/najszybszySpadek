package com.company;

public class MatrixOperation {
    public static double findDet(double [][] matrix){
        //main diagonal(s)
        double firstPart = matrix[0][0] * matrix[1][1] * matrix[2][2];
        double secondPart = matrix[1][0] * matrix[2][1] * matrix[0][2];
        double thirdPart = matrix[2][0] * matrix[0][1] * matrix[1][2];
        double leftSide = firstPart + secondPart+thirdPart;
        //opposite diagonal(s)
        firstPart = matrix[0][2] * matrix[1][1] * matrix[2][0];
        secondPart = matrix[1][2] * matrix[2][1] * matrix[0][0];
        thirdPart = matrix[2][2] * matrix[0][1] * matrix[1][0];
        double rightSide = -firstPart - secondPart -thirdPart;
//        System.out.println("Det(A) = " + (leftSide + rightSide));
        return leftSide + rightSide;
    }
    public static double[][] matrixComplement(double [][] matrix){
        double[][] output = new double[3][3];
        output[0][0] = smallDet(matrix,2,2,3,3,3,2,2,3);
        output[0][1] = -smallDet(matrix,2,1,3,3,3,1,2,3);
        output[0][2] = smallDet(matrix,2,1,3,2,3,1,2,2);

        output[1][0] = -smallDet(matrix,1,2,3,3,3,2,1,3);
        output[1][1] = smallDet(matrix,1,1,3,3,3,1,1,3);
        output[1][2] = -smallDet(matrix,1,1,2,3,3,1,1,2);

        output[2][0] = smallDet(matrix,1,2,2,3,2,2,1,3);
        output[2][1] = -smallDet(matrix,1,1,2,3,2,1,1,3);
        output[2][2] = smallDet(matrix,1,1,2,2,2,1,1,2);
        return output;
    }
    private static double smallDet(double[][] matrix, int ... indexes){
        return matrix[indexes[0]-1][indexes[1]-1] * matrix[indexes[2]-1][indexes[3]-1] - matrix[indexes[4]-1][indexes[5]-1] * matrix[indexes[6]-1][indexes[7]-1];
    }
    public static double[][] inversedMatrix(double[][] matrix, double detA){
        double[][] output = new double[3][3];
        for (int c = 0; c < output.length; c++) {
            for (int r = 0; r < output.length; r++) {
                output[c][r] = matrix[c][r] / detA;
            }
        }
//        System.out.println("Macierz przeciwna:");
//        for (double[] doubles : output) {
//            for (double aDouble : doubles) {
//                System.out.print(aDouble + " ");
//            }
//            System.out.println("");
//        }
        return output;
    }
    public static double[] multiplyMatrix(double[][] matrix, double[] gradient){
        double[] output = new double[3];
        for (int c = 0; c < 3; c++){
            for (int a = 0; a < 3; a++){
                output[c] += matrix[a][c] * gradient[a];
            }
        }
        return output;
    }
}

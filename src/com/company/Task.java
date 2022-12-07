package com.company;

public class Task {
    double [] point = new double[]{1,1,1};
    final double EPSILON = 0.000001;
    double[] gradient = new double[3];
    public void run(){
        do{
            point = findNewPoint(point);
            System.out.println("Współrzedne nowego punktu: [" + point[0] + ", " + point[1] + ", " + point[2] + "] f(x) = " + F(point) );
        }while(timeToStop());
    }
    public double[] findNewPoint(double[] point){
        gradient = generateGradient(point);
        double[][] HesseMatrix = generateHesseMatrix(point[0], point[1], point[2]);
        double detA = MatrixOperation.findDet(HesseMatrix);
        double[][] complementMatrix = MatrixOperation.matrixComplement(HesseMatrix);
        double[][] inversedMatrix = MatrixOperation.inversedMatrix(complementMatrix,detA);
        double[] vector = MatrixOperation.multiplyMatrix(inversedMatrix,gradient);
        double newPoint[] = new double[3];
        newPoint[0] = point[0] + vector[0];
        newPoint[1] = point[1] + vector[1];
        newPoint[2] = point[2] + vector[2];
        return newPoint;
    }
    public boolean timeToStop(){
        double value = 0;
        for (double v : gradient) {
            value += Math.pow(v,2);
        }
        return value > EPSILON;
    }
    public double der_x1(double x1, double x2, double x3){
        return 3 * x1 * x1 - 6 * x1 * x2 * x2 - 8 * x1 * x3 * x3 * x3;
    }
    public double der_x2(double x1, double x2, double x3){
        return -6*x1*x1*x2 + 9 * x2 * x2 * x3;
    }
    public double der_x3(double x1, double x2, double x3){
        return 3 * x2 * x2 * x2 - 12 * x1 * x1 * x3 * x3;
    }
    //pochodne drugiego rzedu
    public double der_x1x1(double x1, double x2, double x3){
        return 6*x1 - 6 * x2 * x2 - 8 * x3 * x3 * x3;
    }
    public double der_x1x2(double x1, double x2, double x3){
        return -12 * x1 * x2;
    }
    public double der_x1x3(double x1, double x2, double x3){
        return -24 * x1 * x3 * x3;
    }
    public double der_x2x1(double x1, double x2, double x3){
        return -12 * x1 * x2;
    }
    public double der_x2x2(double x1, double x2, double x3){
        return -6 * x1 * x1 + 18 * x2 * x3;
    }
    public double der_x2x3(double x1, double x2, double x3){
        return 9 * x2 * x2;
    }
    public double der_x3x1(double x1, double x2, double x3){
        return -24 * x1 * x3 * x3;
    }
    public double der_x3x2(double x1, double x2, double x3){
        return 9 * x2 * x2;
    }
    public double der_x3x3(double x1, double x2, double x3){
        return -24 * x1 * x1 * x3;
    }
    public double[][] generateHesseMatrix(double x1, double x2, double x3){
        double [][] matrix = new double[][]{{der_x1x1(x1,x2,x3),der_x1x2(x1,x2,x3),der_x1x3(x1,x2,x3)}, {der_x2x1(x1,x2,x3),der_x2x2(x1,x2,x3),der_x2x3(x1,x2,x3)}, {der_x3x1(x1,x2,x3),der_x3x2(x1,x2,x3),der_x3x3(x1,x2,x3)}};
        return matrix;
    }
    public double[] generateGradient(double[] point){
        double x1 = der_x1(point[0], point[1], point[2]);
        double x2 = der_x2(point[0], point[1], point[2]);
        double x3 = der_x3(point[0], point[1], point[2]);
        return new double[]{-x1, -x2, -x3};
    }
    public double F(double [] point){
        return point[0] * point[0] * point[0] - 3 * point[0] * point[0] * point[1] * point[1] + 3 * point[1] * point[1] * point[1] * point[2] - 4 * point[0] * point[0] * point[2] * point[2] * point[2];
    }
}

package com.company;

public class QuadraticRegression extends DataSet{

    public QuadraticRegression(double X, double X2, double X3, double X4, double Y){
        super(X, X2, X3,X4,Y);
    }

    public static void main(String[] args) {
        DataSet.LeerDatos5();

        quadraticRegression();
    }

    public static void quadraticRegression() {

        int n= Datos.size();
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;
        double sumX3 = 0;
        double sumX4 = 0;
        double sumX2Y = 0;

        for (DataSet p : Datos) {
            double X = p.X;
            double Y = p.Y;

            sumX += X;
            sumY += Y;
            sumXY += X * Y;
            sumX2 += X * X;
            sumX3 += X * X * X;
            sumX4 += X * X * X * X;
            sumX2Y += X*X * Y ;

        }

        double[][] matrizX = new double[3][3];
        double[][] matrizY = new double[3][1];

    // Llena la matriz 'matrizX' con los valores adecuados
        matrizX[0][0] = sumX4;
        matrizX[0][1] = sumX3;
        matrizX[0][2] = sumX2;
        matrizX[1][0] = sumX3;
        matrizX[1][1] = sumX2;
        matrizX[1][2] = sumX;
        matrizX[2][0] = sumX2;
        matrizX[2][1] = sumX;
        matrizX[2][2] = n;

    // Llena la matriz 'matrizY' con los valores adecuados
        matrizY[0][0] = sumX2Y;
        matrizY[1][0] = sumXY;
        matrizY[2][0] = sumY;
        imprimirMatriz(matrizX);

    //  Creamos una sola matriz
        double[][] matrizAumentada = aumentMatrix(matrizX, matrizY);

    //  Resolvemos el sistema de ecuaciones
        gaussJordan(matrizAumentada);


        double a=matrizAumentada[0][3];
        double b=matrizAumentada[1][3];
        double c=matrizAumentada[2][3];

        System.out.println("La ecuación de la regresión cuadrática es: Y = " +a + " * X^2 + "+ b  + " * X + "+ c );
        System.out.println();

        System.out.println("Simulaciones: ");

        System.out.println("\nSimulacion 1: ");
        System.out.println("Cuando X= -4\nResultado: " +(a + -4 *-4 + b  + -4 + c));

        System.out.println("\nSimulacion 2: ");
        System.out.println("Cuando X= 1\nResultado: " +(a + 1 * 1 + b  + 1 + c));

        System.out.println("\nSimulacion 3: ");
        System.out.println("Cuando X= 4\nResultado: " +(a + 4 * 4 + b  + 4 + c));

        System.out.println("\nEl poder predictivo relativo de un modelo cuadrático se denota por: R2=1−SSE/SST\n" +
                "where SSE=∑(yi−axi2−bxi−c)^2\n" +
                "and SST=∑(yi−y¯)^2\n");
        System.out.println("Prediccion del error: = "+ error(a,b,c));
    }

    public static double[][] aumentMatrix(double[][] matrixX, double[][] matrixY) {
        int numRows = matrixX.length;
        int numCols = matrixX[0].length;
        double[][] augmentedMatrix = new double[numRows][numCols + 1];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                augmentedMatrix[i][j] = matrixX[i][j];
            }
            augmentedMatrix[i][numCols] = matrixY[i][0];
        }

        return augmentedMatrix;
    }

    public static void gaussJordan(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int pivot = 0; pivot < numRows; pivot++) {

            // Hacer el elemento diagonal igual a 1
            double pivotValue = matrix[pivot][pivot];
            for (int j = pivot; j < numCols; j++) {
                matrix[pivot][j] /= pivotValue;
            }

            // Hacer 0 los otros elementos en la columna
            for (int i = 0; i < numRows; i++) {
                if (i != pivot) {
                    double factor = matrix[i][pivot];
                    for (int j = pivot; j < numCols; j++) {
                        matrix[i][j] -= factor * matrix[pivot][j];
                    }
                }
            }
        }
    }

    public static void imprimirMatriz(double[][] matriz) {
        for (double[] doubles : matriz) {
            for (int j = 0; j < doubles.length; j++) {
                System.out.printf("%.12f\t", doubles[j]);
            }
            System.out.println();
        }
    }

    public static double error(double a, double b, double c) {
        double error;
        double SSE=0;
        double SST=0;
        double x;
        double y;
        double sumaY=0;
        double promedioY;

        for (DataSet p : Datos) {
            x=p.X;
            y=p.Y;
            SSE += (y - (a*(x * x))- (b*(x)) - (c)) * (y - (a*(x * x))- (b*(x)) - (c));
        }

        for (DataSet p : Datos) {

            sumaY += p.Y;
        }
        promedioY= sumaY/7;

        for (DataSet p : Datos) {
            x=p.X;
            y=p.Y;


            SST += (y-promedioY) * (y-promedioY);
        }
        error= 1.0 - SSE/SST;

        return error;
    }


}

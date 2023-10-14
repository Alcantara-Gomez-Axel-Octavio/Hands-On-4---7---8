package com.company;

import java.util.ArrayList;

public class SimpleLinearRegression {

    public double pendiente;
    public double intercept;


    public static void main(String[] args) {

        double simulacion1, simulacion2, simulacion3 ;
        double prediccion1, prediccion2, prediccion3 ;

        ArrayList<DataSet> data = DataSet.LeerDatos();
        //ArrayList<DataSet> data = DataSet.LeerDatos2();

        SimpleLinearRegression regression = new SimpleLinearRegression(data);


        // Simulacion 1
        simulacion1 = 23;
        prediccion1 = regression.predict(simulacion1);
        System.out.println("Para x = " + simulacion1 + ", y = " + prediccion1);

        //Simulacion 2
        simulacion2 = 30;
        prediccion2 = regression.predict(simulacion2);
        System.out.println("Para x = " + simulacion2 + ", y = " + prediccion2);

        //Simulacion 3
        simulacion3 = 40;
        prediccion3 = regression.predict(simulacion3);
        System.out.println("Para x = " + simulacion3 + ", y = " + prediccion3);
    }


    public SimpleLinearRegression(ArrayList<DataSet> data) {
        // Calcula la pendiente y la intersección de la regresión lineal
        int n = data.size();
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        for (DataSet p : data) {
            sumX += p.X;
            sumY += p.Y;
            sumXY += p.X * p.Y;
            sumX2 += p.X * p.X;
        }

        pendiente = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        intercept = (sumY - pendiente * sumX) / n;

        // Imprime la ecuación de la regresión lineal
        System.out.println("Ecuación de la regresión lineal: y = " + pendiente + "x + " + intercept);
    }


    public double predict(double x) {

        // Realiza una predicción utilizando la ecuación de la regresión lineal
        return pendiente * x + intercept;
    }

}

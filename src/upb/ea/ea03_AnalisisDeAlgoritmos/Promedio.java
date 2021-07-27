package upb.ea.ea03_AnalisisDeAlgoritmos;

class Promedio {

    double promedio(double[] x) { // Tamaño entrada x.length=N

        double sum = 0; // tiempo constante t1, f1=1

        for(int i=0; i<x.length; i++) // inicializacion f2=1, t2, comparacion f3=N+1, t3=cte, incremento f4=N, t4=cte
            sum += x[i]; // f7=N , t7=cte

        sum /= x.length; // f5=1, t5=cte

        return sum; // f6=1, t6=cte
    }



}

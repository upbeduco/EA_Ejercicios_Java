package upb.ea.ea01_ADT;

import edu.princeton.cs.algs4.StdOut;

public class Fecha {

    int año;
    byte mes;
    byte dia;

    // private static final byte[] DIAS_MES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


    public Fecha(int a, byte m, byte d) throws Exception {
        año = a;
        mes = m;
        dia = d;
    }

    public int diaDelAño() {
        // TODO: Dar una implementacion para calcular el dia del año
        return 0;
    }


    // TODO: Hacer una función de biblioteca para determinar si un año es biciesto
    // public static boolean isLeapYear(int año) {}

    // TODO: Hacer una función de biblioteca que lea una fecha por cosola y devuelva una instancia
    //       (Este es un ejemplo del patrón "Factory")
    // public static Fecha readDate() {}


    public boolean equals(Object other) {
        // TODO: Completar el codigo
        return false;
    }

    public String toString() {
        // TODO: Completar el codigo
        return "";
    }


    // TODO: Hacer la sobre-escritura del metodo equals
    // public boolean equals(Object f) {}

    // TODO: Hacer la sobre-escritura del metodo toString
    // public String toString() {}

    public static void main(String[] args) throws Exception {
        StdOut.println("Ejemplo ADT Fecha");
        
        // Pruebas unitarias del método diaDelAño
        Fecha f1 = new Fecha(2025,(byte)1,(byte)31);
        assert f1.diaDelAño()==31 : "El 31 de enero de 2025 es el dia 31 del año";

        Fecha f2 = new Fecha(2025,(byte)12,(byte)31);
        assert f2.diaDelAño()==365 : "El 31 de diciembre de 2025 es el dia 365 del año";

        Fecha f3 = new Fecha(2024,(byte)3,(byte)1);
        assert f3.diaDelAño()==365 : "El 1 de marzo de 2024 es el dia 61 del año";


    }


}

// Ejercicios:
// ==========
// 1. Completar la implementacion del ADT
// 2. Asegurar la encapsulacion del valor del ADT
// 3. Implementar la operación (API) diaDelAño
// 4. Sobre-escribir los métodos toString, equals heredados de Object
// 5. Implementar un main que ejemplifique el uso del ADT
// 6. Hacer una aplicación "cliente" que calcule cuantos dias faltan para el cumpleaños
// 6. Implementar un método estático "Factory" que cree instancias a partir de datos ingresados por consola
// 7. Implementar la interface Comparable y hacerle pruebas unitarias

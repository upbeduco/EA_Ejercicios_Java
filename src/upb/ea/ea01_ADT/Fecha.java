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

        return 0;
    }


    // TODO: Hacer una función de biblioteca para determinar si un año es biciesto
    // public static boolean esBiciesto(int año) {}

    // TODO: Hacer una función de biblioteca que lea una fecha por cosola y devuelva una instancia
    //       (Este es un ejemplo del patrón "Factory")
    // public static Fecha leerFecha() {}

    public static void main(String[] args) {
        StdOut.println("Ejemplo ADT Fecha");
        
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
// 7. Implementar la interface Comparable y hacerle pruebas

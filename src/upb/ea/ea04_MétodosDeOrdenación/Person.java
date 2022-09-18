package upb.ea.ea04_MétodosDeOrdenación;

import java.text.DecimalFormat;

class Person implements Comparable<Person> {

    private String nombres;
    private String apellidos;
    private int edad;
    private float peso;

    public Person(String n, String a, int e, float p) {
        nombres = n;
        apellidos = a;
        edad = e;
        peso = p;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public float getPeso() {
        return peso;
    }

    public String toString() {
        return nombres+" "+apellidos+" : "+edad+", "+df.format(peso);
    }

    @Override
    public int compareTo(Person o) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    private static final DecimalFormat df = new DecimalFormat();
    {
        df.setMaximumFractionDigits(2);
    }

}
package upb.ea.ea03_AnalisisDeAlgoritmos;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class GeneradorNombres {

    private static String[] mujer = { "Isabella", "Olivia", "Alexis", "Sofía", "Victoria", "Amelia", "Alexa", "Julia",
            "Camila", "Alexandra", "Maya", "Andrea", "Ariana", "María", "Eva", "Angelina", "Valeria", "Natalia",
            "Isabel", "Sara", "Liliana", "Adriana", "Juliana", "Gabriela", "Daniela", "Valentina", "Lila", "Vivian",
            "Nora", "Ángela", "Elena", "Clara", "Eliana", "Alana", "Miranda", "Amanda", "Diana", "Ana", "Penélope",
            "Aurora", "Alexandría", "Lola", "Alicia", "Amaya", "Alexia", "Jazmín", "Mariana", "Alina", "Lucía",
            "Fátima", "Ximena", "Laura", "Cecilia", "Alejandra", "Esmeralda", "Verónica", "Daniella", "Miriam",
            "Carmen", "Iris", "Guadalupe", "Selena", "Fernanda", "Angélica", "Emilia", "Lía", "Tatiana", "Mónica",
            "Carolina", "Jimena", "Dulce", "Talía", "Estrella", "Brenda", "Lilian", "Paola", "Serena", "Celeste",
            "Viviana", "Elisa", "Melina", "Gloria", "Claudia", "Sandra", "Marisol", "Asia", "Ada", "Rosa", "Isabela",
            "Regina", "Elsa", "Perla", "Raquel", "Virginia", "Patricia", "Linda", "Marina", "Leila", "América",
            "Mercedes" };

    private static String[] hombre = { "Daniel", "David", "Gabriel", "Benjamín", "Samuel", "Lucas", "Ángel", "José",
            "Adrián", "Sebastián", "Xavier", "Juan", "Luis", "Diego", "Óliver", "Carlos", "Jesús", "Alex", "Max",
            "Alejandro", "Antonio", "Miguel", "Víctor", "Joel", "Santiago", "Elías", "Iván", "Óscar", "Leonardo",
            "Eduardo", "Alan", "Nicolás", "Jorge", "Omar", "Paúl", "Andrés", "Julián", "Josué", "Román", "Fernando",
            "Javier", "Abraham", "Ricardo", "Francisco", "César", "Mario", "Manuel", "Édgar", "Alexis", "Israel",
            "Mateo", "Héctor", "Sergio", "Emiliano", "Simón", "Rafael", "Martín", "Marco", "Roberto", "Pedro",
            "Emanuel", "Abel", "Rubén", "Fabián", "Emilio", "Joaquín", "Marcos", "Lorenzo", "Armando", "Adán", "Raúl",
            "Julio", "Enrique", "Gerardo", "Pablo", "Jaime", "Saúl", "Esteban", "Gustavo", "Rodrigo", "Arturo",
            "Mauricio", "Orlando", "Hugo", "Salvador", "Alfredo", "Maximiliano", "Ramón", "Ernesto", "Tobías", "Abram",
            "Noé", "Guillermo", "Ezequiel", "Lucián", "Alonzo", "Felipe", "Matías", "Tomás", "Jairo" };

    private static String[] apellidos = { "González", "Muñoz", "Rojas", "Díaz", "Pérez", "Soto", "Contreras", "Silva",
            "Martínez", "Sepúlveda", "Morales", "Rodríguez", "López", "Fuentes", "Hernández", "Torres", "Araya",
            "Flores", "Espinoza", "Valenzuela", "Castillo", "Tapia", "Reyes", "Gutiérrez", "Castro", "Pizarro",
            "Álvarez", "Vásquez", "Sánchez", "Fernández", "Ramírez", "Carrasco", "Gómez", "Cortés", "Herrera", "Núñez",
            "Jara", "Vergara", "Rivera", "Figueroa", "Riquelme", "García", "Miranda", "Bravo", "Vera", "Molina", "Vega",
            "Campos", "Sandoval", "Orellana", "Cárdenas", "Olivares", "Alarcón", "Gallardo", "Ortiz", "Garrido",
            "Salazar", "Guzmán", "Henríquez", "Saavedra", "Navarro", "Aguilera", "Parra", "Romero", "Aravena", "Vargas",
            "Vázquez", "Cáceres", "Yáñez", "Leiva", "Escobar", "Ruiz", "Valdés", "Vidal", "Salinas", "Zúñiga", "Peña",
            "Godoy", "Lagos", "Maldonado", "Bustos", "Medina", "Pino", "Palma", "Moreno", "Sanhueza", "Carvajal",
            "Navarrete", "Sáez", "Alvarado", "Donoso", "Poblete", "Bustamante", "Toro", "Ortega", "Venegas", "Guerrero",
            "Mendoza", "Farías", "San", "Martín" };

    public static void arrayFromInput() {
        String[] lines = StdIn.readAllStrings();
        String comma = "";
        StringBuilder sb = new StringBuilder("{");
        for (String s : lines) {
            sb.append(comma + "\"" + s + "\"");
            comma = ",";
        }
        sb.append("};");
        StdOut.println(sb);
    }

    public static String[] generar(int n) {
        String[] nombres = new String[n];
        for (int i = 0; i < n; i++) {
            int genero = StdRandom.uniform(0, 2);
            String nombre1, nombre2;
            if (genero == 0) {
                nombre1 = mujer[StdRandom.uniform(mujer.length)];
                nombre2 = mujer[StdRandom.uniform(mujer.length)];
            } else {
                nombre1 = mujer[StdRandom.uniform(hombre.length)];
                nombre2 = mujer[StdRandom.uniform(hombre.length)];
            }
            String apellido1 = apellidos[StdRandom.uniform(apellidos.length)];
            String apellido2 = apellidos[StdRandom.uniform(apellidos.length)];
            nombres[i] = nombre1 + " " + nombre2 + " " + apellido1 + " " + apellido2;
        }
        return nombres;
    }

    public static void main(String[] args) {
        // arrayFromInput();
        String[] nombres = generar(10);
        for (String n : nombres)
            StdOut.println(n);
    }

}

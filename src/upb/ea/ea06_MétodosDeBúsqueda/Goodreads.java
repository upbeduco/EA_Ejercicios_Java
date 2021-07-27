package upb.ea.ea06_MétodosDeBúsqueda;

/**
 * Ejemplos de uso de la tabla de símbolos ordenada
 * 
 * @author Jorge Londoño
 * Dataset: https://www.kaggle.com/jealousleopard/goodreadsbooks
 * 
 */

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.RedBlackBST;

/**
 * Un ADT para representar los libros en la coleccion
 */
class Book {
    private int bookID;
    private String title;
    private String authors;
    private float average_rating;
    private String isbn;
    private String isbn13;
    private String language_code;
    private int num_pages;
    private int ratings_count;
    private int text_reviews_count;
    private Date publication_date;
    private String publisher;
    
    Book(String line) throws ParseException {
        String[] tmp = line.split(",");
        bookID = Integer.valueOf(tmp[0]);
        title = tmp[1];
        authors = tmp[2];
        average_rating = Float.parseFloat(tmp[3]);
        isbn = tmp[4];
        isbn13 = tmp[5];
        language_code = tmp[6];
        num_pages = Integer.valueOf(tmp[7]);
        ratings_count = Integer.valueOf(tmp[8]);
        text_reviews_count = Integer.valueOf(tmp[9]);
        publication_date = sdf.parse(tmp[10]);
        publisher = tmp[11];
    }

    public int    getBookID()             { return bookID; }
    public String getTitle()              { return title; }
    public String getAuthors()            { return authors; }
    public float  getAverage_rating()     { return average_rating; }
    public String getIsbn()               { return isbn; }
    public String getIsbn13()             { return isbn13; }
    public String getLanguage_code()      { return language_code; }
    public int    getNum_pages()          { return num_pages; }
    public int    getRatings_count()      { return ratings_count; }
    public int    getText_reviews_count() { return text_reviews_count; }
    public Date   getPublication_date()   { return publication_date; }
    public String getPublisher()          { return publisher; }

    static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

}

class BookComparator implements Comparator<Book> {

    public int compare(Book a, Book b) {
        // TODO: Hacer un comparador de libros

        return 0;
    }

}


public class Goodreads {


    public static void readFile(String file) {
        In in = new In(file);
        in.readLine(); // ignorar primera linea
        while(! in.isEmpty()) {
            String l = in.readLine();
            try {
                Book b = new Book(l);
                // TODO: Agregar el libro a las estructuras necesarias para hacer las búsquedas
                
            }
            catch(ParseException e) { 
                StdOut.println("Fecha no valida: "+l);
            }
            catch(NumberFormatException e) {
                StdOut.println("Numero no valido: "+l);
            }
        }
    }


    public static void librosPorAutor(String autor) {
        // TODO: Implementar un metodo para calcular el promedio de calificacion
        // de los libros de un autor
    }

    public static void librosPorAnno() { 
        // TODO: Calcular cuantos libros aparecen registrados por cada año

    }

    public static void topPorAutor() {
        // TODO: Calcular los 5 libros mejor calificados por cada autor

    }

    public static void main(String[] args) {
        String ruta = "D:\\UPB\\Datasets\\books.csv";
        readFile(ruta);

    }



}


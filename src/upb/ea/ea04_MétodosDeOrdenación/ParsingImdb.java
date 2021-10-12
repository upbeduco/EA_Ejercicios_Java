package upb.ea.ea04_MétodosDeOrdenación;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ParsingImdb {

    static class Pelicula {
        private String imdb_title_id;
        private String title;
        private String original_title;
        private int year;
        private Date date_published;
        private String genre;
        private int duration;
        private String country;
        private String language;
        private String director;
        private String writer;
        private String production_company;
        private String actors;
        private String description;
        private float avg_vote;
        private int votes;
        private String budget;
        private String usa_gross_income;
        private String worlwide_gross_income;
        private String metascore;
        private float reviews_from_users;
        private String reviews_from_critics;
    }

    public static void main(String[] args) throws ParseException {
        String url = "IMDb%20movies.csv";
        In in = new In(url);
        in.readLine(); // ignore first line
        int counter = 0;
        while (!in.isEmpty()) {
            counter++;
            String line = in.readLine();
            // StdOut.println(line);

            String[] fields = null;
            try {
                // Solucion para no separar el campo de actores encerrado en comillas:
                // Tomada de: https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes
                fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                // StdOut.println(fields.length);
                Pelicula p = new Pelicula();
                p.imdb_title_id = fields[0];
                p.title = fields[1];
                p.original_title = fields[2];
                p.year = Integer.valueOf(fields[3]);
                p.date_published = parseDate(fields[4]);
                p.genre = fields[5];
                p.duration = Integer.valueOf(fields[6]);
                p.country = fields[7];
                p.language = fields[8];
                p.director = fields[9];
                p.writer = fields[10];
                p.production_company = fields[11];
                p.actors = fields[12];
                p.description = fields[13];
                p.avg_vote = Float.valueOf(fields[14]);
                p.votes = Integer.valueOf(fields[15]);
                p.budget = fields[16];
                p.usa_gross_income = fields[17];
                p.worlwide_gross_income = fields[18];
                p.metascore = fields[19];
                p.reviews_from_users = parseFloat(fields[20]);
                p.reviews_from_critics = (fields.length>21) ? fields[21] : null;
            }
            catch(NumberFormatException e) {
                StdOut.println("ERROR: Linea "+counter);
                StdOut.println(line);
                StdOut.println();
            }
        }
        StdOut.println("Total lines parsed: "+counter);

    }

    private static float parseFloat(String s) {
        try {
            return Float.valueOf(s);
        }
        catch(NumberFormatException e) {
            return 0.0f;
        }
    }

    private static Date parseDate(String s) throws ParseException {
        if (s.indexOf(' ')>=0) return sdfz.parse(s);
        else if (s.indexOf('-')<0) return sdfy.parse(s);
        return sdf.parse(s);
    }

    final static SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    final static SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
    final static SimpleDateFormat sdfz = new SimpleDateFormat("'TV Movie 'yyyy");



}

package upb.ea.ea06_MétodosDeBúsqueda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.princeton.cs.algs4.In;

public class CustomerSupportTweet {

    // The fields of the class come from the following CSV sample file:
    // tweet_id	author_id	inbound	created_at	text	response_tweet_id	in_response_to_tweet_id
    // 119237	105834	True	Wed Oct 11 06:55:44 +0000 2017	@AppleSupport causing the reply to be disregarded and the tapped notification under the keyboard is opened😡😡😡	119236	
    // 119238	ChaseSupport	False	Wed Oct 11 13:25:49 +0000 2017	@105835 Your business means a lot to us. Please DM your name, zip code and additional details about your concern. ^RR https://t.co/znUu1VJn9r		119239

    private int tweetId;
    private String authorId;
    private boolean inbound;
    private Date createdAt;
    private String text;
    private String responseTweetId;
    private String inResponseToTweetId;

    /**
     * String representation of a tweet
     */
    public String toString() {
        return tweetId+" "+authorId+" "+inbound+" "+createdAt+" "+text+" "+responseTweetId+" "+inResponseToTweetId;
    }

    /**
     * Reads a CSV file with the twits and returns an ArrayList of CustomerSupportTwit objects
     * @param filename
     * @return ArrayList of CustomerSupportTwit objects
     */
    public static ArrayList<CustomerSupportTweet> leerTweetsCsv(String filename) {
        int lineNumber = 0;
        ArrayList<CustomerSupportTweet> twits = new ArrayList<CustomerSupportTweet>();
        In in = new In(filename);
        String[] fields = in.readLine().split(",");
        lineNumber++;
        while (!in.isEmpty()) {
            lineNumber++;
            String line = in.readLine();
            // remove line breaks from line
            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            // System.out.println(lineNumber+" : "+line);
            try {
                if (data.length==7) {
                    CustomerSupportTweet twit = new CustomerSupportTweet();
                    twit.tweetId = Integer.parseInt(data[0]);
                    twit.authorId = data[1];
                    twit.inbound = Boolean.parseBoolean(data[2]);
                    twit.createdAt = spf.parse(data[3]);
                    twit.text = data[4];
                    if (data.length>5) twit.responseTweetId = data[5];
                    if (data.length>6) twit.inResponseToTweetId = data[6];
                    twits.add(twit);
                }
                else {
                    // System.err.println("Invalid line: "+lineNumber+" "+line);
                }
    
            }
            catch(NumberFormatException e) {
                System.err.println("Invalid number: "+lineNumber+" "+line);
            }
            catch(ParseException e) {
                System.err.println("Invalid date: "+lineNumber+" "+line);
            }
    
        }
        return twits;
    }


    private static SimpleDateFormat spf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");


    public static void main(String[] args) {
        String filename = "/home/jmlon/tmp/Datasets/twcs.csv";
        ArrayList<CustomerSupportTweet> twits = CustomerSupportTweet.leerTweetsCsv(filename);
        System.out.println("Loaded " + twits.size() + " twits from " + filename);
        // for (CustomerSupportTwit twit : twits) {
        //     System.out.println(twit);
        // }
    }


}

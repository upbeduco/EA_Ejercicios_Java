package upb.ea.ea06_MétodosDeBúsqueda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


// Fields of a DelayRecord
// YEAR	FL_DATE	OP_UNIQUE_CARRIER	OP_CARRIER_AIRLINE_ID	OP_CARRIER_FL_NUM	ORIGIN_AIRPORT_ID	ORIGIN	ORIGIN_CITY_NAME	ORIGIN_STATE_ABR	DEST_AIRPORT_ID	DEST	DEST_CITY_NAME	DEST_STATE_ABR	DEP_DELAY_NEW	ARR_DELAY	ARR_DELAY_NEW	CARRIER_DELAY	WEATHER_DELAY	NAS_DELAY	SECURITY_DELAY	LATE_AIRCRAFT_DELAY
public class DelayRecord {

    private int year;
    private Date date;
    private String carrier;
    private int carrierId;
    private int flightNumber;
    private int originAirportId;
    private String origin;
    private String originCity;
    private String originState;
    private int destAirportId;
    private String dest;
    private String destCity;
    private String destState;
    private Integer depDelay;
    private Integer arrDelay;
    private Integer arrDelayNew;
    private Integer carrierDelay;
    private Integer weatherDelay;
    private Integer nasDelay;
    private Integer securityDelay;
    private Integer lateAircraftDelay;

    // Constructor that initializes all the fields
    public DelayRecord(int year, String date, String carrier, int carrierId, int flightNumber, int originAirportId,
            String origin, String originCity, String originState, int destAirportId, String dest, String destCity,
            String destState, String depDelay, String arrDelay, String arrDelayNew, String carrierDelay,
            String weatherDelay, String nasDelay, String securityDelay, String lateAircraftDelay) throws ParseException {
        this.year = year;
        this.date = df.parse(date);
        this.carrier = carrier;
        this.carrierId = carrierId;
        this.flightNumber = flightNumber;
        this.originAirportId = originAirportId;
        this.origin = origin;
        this.originCity = originCity;
        this.originState = originState;
        this.destAirportId = destAirportId;
        this.dest = dest;
        this.destCity = destCity;
        this.destState = destState;
        this.depDelay = parseInteger(depDelay);
        this.arrDelay = parseInteger(arrDelay);
        this.arrDelayNew = parseInteger(arrDelayNew);
        this.carrierDelay = parseInteger(carrierDelay);
        this.weatherDelay = parseInteger(weatherDelay);
        this.nasDelay = parseInteger(nasDelay);
        this.securityDelay = parseInteger(securityDelay);
        this.lateAircraftDelay = parseInteger(lateAircraftDelay);
    }


    // Parse String to Integer, return null if the string is empty
    private static Integer parseInteger(String s) {
        if (s.isEmpty()) {
            return null;
        } else {
            return Integer.parseInt(s);
        }
    }


    // For parsing dates in the format "yyyy-MM-dd"
    private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    
    public static ArrayList<DelayRecord> readCSVFile(String filename) {
        int lines=0;
        ArrayList<DelayRecord> delays = new ArrayList<>();
        In in = new In(filename);
        in.readLine();
        while (in.hasNextLine()) {
            lines++;
            // read a line an create a DelayRecord instance
            String line = in.readLine();
            // split comma separated fields, except within quotes
            String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            // StdOut.println(lines+" : "+fields[1]+","+fields[3]+","+fields[4]+","+fields[5]+","+fields[9]);
            try {
                DelayRecord delay = new DelayRecord(
                        Integer.parseInt(fields[0]), // year
                        fields[1], // date
                        fields[2], // carrier
                        Integer.parseInt(fields[3]), // carrierId
                        Integer.parseInt(fields[4]), // flightNumber
                        Integer.parseInt(fields[5]), // originAirportId
                        fields[6], // origin
                        fields[7], // originCity
                        fields[8], // originState
                        Integer.parseInt(fields[9]), // destAirportId
                        fields[10], // dest
                        fields[11], // destCity
                        fields[12], // destState
                        fields[13], // depDelay
                        fields[14], // arrDelay
                        fields[15], // arrDelayNew
                        fields[16], // carrierDelay
                        fields[17], // weatherDelay
                        fields[18], // nasDelay
                        fields[19], // securityDelay
                        fields[20] // lateAircraftDelay
                );
                delays.add(delay);
            } catch (ParseException e) {
                System.out.println("Error reading line: " + line);
            }
        }
        in.close();
        return delays;
    }


    public static void main(String[] args) {
        // Leer el archivo CSV y crear una lista de DelayRecord
        String filename = "548634059_T_ONTIME_REPORTING.csv";
        ArrayList<DelayRecord> delays = readCSVFile(filename);
        StdOut.println("Number of records: " + delays.size());
    }



}

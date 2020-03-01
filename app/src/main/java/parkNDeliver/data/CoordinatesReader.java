package parkNDeliver.data;

import java.io.*;

import android.content.res.Resources;
import com.example.parkndeliver.R;

public class CoordinatesReader {
    private static final int NUMBER_OF_LINES_TO_PROCESS = 30;
    private final static String clientsDataPath = "clients_aqua_here.csv";
    private final static String loadUnloadDataPath = null;

    private static Resources appResources;

    private static String line = "";
    private static String cvsSplitBy = ";";


    public static void parseClientsData() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClientDataFile()))) {
            int parsedLines = 0;
            while ((line = br.readLine()) != null && hasNotPrintedEnoughLines(parsedLines)) {

                if(startsWithNumber(line)) {
                    // use comma as separator
                    String[] client = line.split(cvsSplitBy);

                    System.out.println("Client [id= " + client[0] + " , x=" + client[1] + " , y=" + client[2] + "]");

                    parsedLines++;
                }
            }

        } catch (IOException e) {
            System.err.println("Error parsing the file.");
            e.printStackTrace();
        }
    }

    public static void parseLoadUnloadData() {

    }

    public static void setResources(Resources resources) {
        appResources = resources;
    }

    private static InputStream getClientDataFile() {
        return appResources.openRawResource(R.raw.clients_aqua_here);
    }


    private static boolean hasNotPrintedEnoughLines(int parsedLines) {
        return parsedLines < NUMBER_OF_LINES_TO_PROCESS;
    }


    private static boolean startsWithNumber(@org.jetbrains.annotations.NotNull String line) {
        char firstChar = line.charAt(0);
        return Character.isDigit(firstChar);
    }

}

package parkNDeliver.data;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.res.Resources;
import com.example.parkndeliver.R;

public class CoordinatesReader {
    private static int numberOfClientLinesToProcess = Integer.MAX_VALUE;
    private static int numberOfLoadUnloadsLinesToProcess = Integer.MAX_VALUE;

    private static Resources appResources;

    private static String line = "";
    private static String cvsSplitBy = ";";

    private static List<Client> clients = new LinkedList<>();
    private static List<LoadUnload> loadUnloads = new LinkedList<>();

    public static void setResources(Resources resources) {
        appResources = resources;
    }

    public static List<Client> getClients() {
        return parseClientsData();
    }

    /**
     * Retrieves the first number of clients requested.
     * @param numberOfRequestedClients
     * @return
     */
    public static List<Client> getClients(int numberOfRequestedClients) {
        return parseClientsData(numberOfRequestedClients);
    }

    public static List<LoadUnload> getLoadUnloads() {
        return parseLoadUnloadData();
    }

    /**
     * Retrieves the first number of load/unloads requested.
     * @param numberOfRequestedLoadUnloads
     * @return
     */
    public static List<LoadUnload> getLoadUnloads(int numberOfRequestedLoadUnloads) {
        return parseLoadUnloadData(numberOfRequestedLoadUnloads);
    }

    private static List<Client> parseClientsData() {
        initializeClientList();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClientDataFile()))) {
            int parsedLines = 0;
            while ((line = br.readLine()) != null && hasNotProcessedEnoughClients(parsedLines)) {

                if(startsWithNumber(line)) {

                    String[] client = line.split(cvsSplitBy);

                    addClientToList(client);
                    parsedLines++;

                }
            }

            return clients;

        } catch (IOException e) {
            System.err.println("Error parsing the file.");
            e.printStackTrace();
        }

        return null;
    }

    private static List<Client> parseClientsData(int numberOfRequestedClients) {
        numberOfClientLinesToProcess = numberOfRequestedClients;
        return parseClientsData();
    }

    private static void addClientToList(String[] client) {
        int id = Integer.parseInt(client[0]);
        float latitude = Float.parseFloat(client[1]);
        float longitude = Float.parseFloat(client[2]);

        clients.add(new Client(id, latitude, longitude));
    }

    private static void initializeClientList() {
        clients = new LinkedList<>();
    }

    private static List<LoadUnload> parseLoadUnloadData() {
        initializeLoadUnloadsList();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getFakeLoadUnloadDataFile()))) {

            int parsedLines = 0;
            while ((line = br.readLine()) != null && hasNotProcessedEnoughLoadUnloads(parsedLines)) {


                String[] loadUnload = line.split(cvsSplitBy);

                addLoadUnloadToList(loadUnload);
                parsedLines++;

            }

            return loadUnloads;

        } catch (IOException e) {
            System.err.println("Error parsing the file.");
            e.printStackTrace();
        }

        return null;
    }

    private static void initializeLoadUnloadsList() {
        loadUnloads = new LinkedList<>();
    }

    private static List<LoadUnload> parseLoadUnloadData(int numberOfRequestedLoadUnloads) {
        numberOfLoadUnloadsLinesToProcess = numberOfRequestedLoadUnloads;
        return parseLoadUnloadData();
    }

    private static void addLoadUnloadToList(String[] loadUnload) {
        String id = loadUnload[0];
        float latitude = Float.parseFloat(loadUnload[1]);
        float longitude = Float.parseFloat(loadUnload[2]);

        loadUnloads.add(new LoadUnload(id, latitude, longitude));
    }



    private static InputStream getClientDataFile() {
        return appResources.openRawResource(R.raw.clients_aqua_here);
    }

    private static InputStream getFakeLoadUnloadDataFile() {
        return appResources.openRawResource(R.raw.loadunload_real);
    }

    private static InputStream getLoadUnloadDataFile() {
        return appResources.openRawResource(R.raw.loadunload_real);
    }

    private static boolean hasNotProcessedEnoughClients(int parsedLines) {
        return parsedLines < numberOfClientLinesToProcess;
    }

    private static boolean hasNotProcessedEnoughLoadUnloads(int parsedLines) {
        return parsedLines < numberOfLoadUnloadsLinesToProcess;
    }

    private static boolean startsWithNumber(@org.jetbrains.annotations.NotNull String line) {
        char firstChar = line.charAt(0);
        return Character.isDigit(firstChar);
    }

}

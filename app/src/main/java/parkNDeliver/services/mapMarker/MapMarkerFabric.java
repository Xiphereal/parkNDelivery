package parkNDeliver.services.mapMarker;

import android.content.Context;
import com.here.sdk.core.GeoCoordinates;
import parkNDeliver.data.Client;
import parkNDeliver.data.CoordinatesReader;
import parkNDeliver.data.LoadUnload;
import java.util.LinkedList;
import java.util.List;

public class MapMarkerFabric {

    private static Context context;

    public static void setApplicationContext(Context applicationContext) {
        context = applicationContext;
    }

    public static List<ClientMapMarker> generateAllClients() {
        List<ClientMapMarker> clientsMapMarker = new LinkedList<>();
        List<Client> clientsReceived = CoordinatesReader.getClients();

        for (Client client : clientsReceived) {
            clientsMapMarker.add(generateClient(client));
        }

        return clientsMapMarker;
    }

    public List<LoadUnloadMapMarker> generateAllLoadUnloads() {
        List<LoadUnloadMapMarker> loadUnloadMapMarkers = new LinkedList<>();
        List<LoadUnload> loadUnloadsReceived = CoordinatesReader.getLoadUnloads();

        for (LoadUnload loadUnload : loadUnloadsReceived) {
            loadUnloadMapMarkers.add(generateLoadUnload(loadUnload));
        }

        return loadUnloadMapMarkers;
    }


    private static ClientMapMarker generateClient(Client client) {
        GeoCoordinates coordinates = getGeoCoordinates(client);
        ClientMapMarker clientMapMarker = new ClientMapMarker(coordinates, context);
        clientMapMarker.setImage();
        return clientMapMarker;
    }

    private LoadUnloadMapMarker generateLoadUnload(LoadUnload loadUnload) {
        GeoCoordinates coordinates = getGeoCoordinates(loadUnload);
        LoadUnloadMapMarker loadUnloadMapMarker = new LoadUnloadMapMarker(coordinates, context);
        loadUnloadMapMarker.setImage();
        return loadUnloadMapMarker;
    }

    private static GeoCoordinates getGeoCoordinates(Client client) {
       return new GeoCoordinates(client.getLatitude(), client.getLongitude());
    }

    private GeoCoordinates getGeoCoordinates(LoadUnload loadUnload) {
        return new GeoCoordinates(loadUnload.getLatitude(), loadUnload.getLongitude());
    }
}

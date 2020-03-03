package parkNDeliver.services.mapMarker;

import android.content.Context;
import com.here.android.mpa.common.GeoCoordinate;
import parkNDeliver.data.Client;
import parkNDeliver.data.CoordinatesReader;
import parkNDeliver.data.LoadUnload;
import java.util.LinkedList;
import java.util.List;

public class MapMarkerFabric {

    private static Context context;
//    private static MapImage clientMapImage;
//    private static MapImage loadUnloadMapImage;


    public static void setApplicationContext(Context applicationContext) {
        context = applicationContext;
    }

//    public static void setClientMapImageResource(MapImage mapImage) {
//        clientMapImage = mapImage;
//    }

//    public static List<ClientMapMarker> generateAllClients() {
//        List<ClientMapMarker> clientsMapMarker = new LinkedList<>();
//        List<Client> clientsReceived = CoordinatesReader.getClients(20);
//
//        for (Client client : clientsReceived) {
//            clientsMapMarker.add(generateClient(client));
//        }
//
//        return clientsMapMarker;
//    }

    public List<LoadUnloadMapMarker> generateAllLoadUnloads() {
        List<LoadUnloadMapMarker> loadUnloadMapMarkers = new LinkedList<>();
        List<LoadUnload> loadUnloadsReceived = CoordinatesReader.getLoadUnloads();

        for (LoadUnload loadUnload : loadUnloadsReceived) {
            loadUnloadMapMarkers.add(generateLoadUnload(loadUnload));
        }

        return loadUnloadMapMarkers;
    }


//    private static ClientMapMarker generateClient(Client client) {
//        GeoCoordinate coordinates = getGeoCoordinates(client);
//        ClientMapMarker clientMapMarker = new ClientMapMarker(coordinates, context, clientMapImage);
//        clientMapMarker.setImage();
//        return clientMapMarker;
//    }

    private LoadUnloadMapMarker generateLoadUnload(LoadUnload loadUnload) {
        GeoCoordinate coordinates = getGeoCoordinates(loadUnload);
        LoadUnloadMapMarker loadUnloadMapMarker = new LoadUnloadMapMarker(coordinates, context);
        loadUnloadMapMarker.setImage();
        return loadUnloadMapMarker;
    }

    private static GeoCoordinate getGeoCoordinates(Client client) {
       return new GeoCoordinate(client.getLatitude(), client.getLongitude());
    }

    private GeoCoordinate getGeoCoordinates(LoadUnload loadUnload) {
        return new GeoCoordinate(loadUnload.getLatitude(), loadUnload.getLongitude());
    }
}

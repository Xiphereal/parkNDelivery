package parkNDeliver.services.mapMarker;

import android.content.Context;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import parkNDeliver.data.Client;
import parkNDeliver.data.CoordinatesReader;
import parkNDeliver.data.LoadUnload;
import java.util.LinkedList;
import java.util.List;

public class MapMarkerFabric {

    private static Image clientImage;
    private static Image loadUnloadImage;

    public static List<ClientMapMarker> generateAllClients() {
        List<ClientMapMarker> clientMapMarkers = new LinkedList<>();
        List<Client> requestedClients = CoordinatesReader.getClients(100);

        for (Client client : requestedClients) {
            clientMapMarkers.add(generateClient(client));
        }

        return clientMapMarkers;
    }

    public static List<LoadUnloadMapMarker> generateAllLoadUnloads() {
        List<LoadUnloadMapMarker> loadUnloadMapMarkers = new LinkedList<>();
        List<LoadUnload> requestedLoadUnloads = CoordinatesReader.getLoadUnloads(20);

        for (LoadUnload loadUnload : requestedLoadUnloads) {
            loadUnloadMapMarkers.add(generateLoadUnload(loadUnload));
        }

        return loadUnloadMapMarkers;
    }

    public static void setClientImageResource(Image image) {
        clientImage = image;
    }

    public static void setLoadUnloadImageResource(Image image) {
        loadUnloadImage = image;
    }

    private static ClientMapMarker generateClient(Client client) {
        GeoCoordinate coordinate = getGeoCoordinate(client);
        ClientMapMarker clientMapMarker = new ClientMapMarker(coordinate, clientImage);
        clientMapMarker.setImage();
        return clientMapMarker;
    }

    private static LoadUnloadMapMarker generateLoadUnload(LoadUnload loadUnload) {
        GeoCoordinate coordinates = getGeoCoordinate(loadUnload);
        LoadUnloadMapMarker loadUnloadMapMarker = new LoadUnloadMapMarker(coordinates, loadUnloadImage);
        loadUnloadMapMarker.setImage();
        return loadUnloadMapMarker;
    }

    private static GeoCoordinate getGeoCoordinate(Client client) {
       return new GeoCoordinate(client.getLatitude(), client.getLongitude());
    }

    private static GeoCoordinate getGeoCoordinate(LoadUnload loadUnload) {
        return new GeoCoordinate(loadUnload.getLatitude(), loadUnload.getLongitude());
    }
}

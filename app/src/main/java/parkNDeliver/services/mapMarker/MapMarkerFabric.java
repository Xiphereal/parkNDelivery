package parkNDeliver.services.mapMarker;

import android.content.Context;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapviewlite.MapMarker;
import parkNDeliver.data.Client;

import java.util.List;

public class MapMarkerFabric {

    private static Context context;

    public static void setApplicationContext(Context applicationContext) {
        context = applicationContext;
    }

    //public List<ClientMapMarker> generateAllClients() {
        //Iterar sobre la lista
            //generateClient(i);
    // }

    private ClientMapMarker generateClient(Client client) {
        GeoCoordinates coordinates = getGeoCoordinates(client);
        ClientMapMarker clientMapMarker = new ClientMapMarker(coordinates, context);
        clientMapMarker.setImage();
        return clientMapMarker;
    }

    private GeoCoordinates getGeoCoordinates(Client client) {
       return new GeoCoordinates(client.getLatitude(), client.getLongitude());
    }
}

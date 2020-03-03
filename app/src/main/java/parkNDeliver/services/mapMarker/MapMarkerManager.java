package parkNDeliver.services.mapMarker;

import com.here.android.mpa.mapping.AndroidXMapFragment;

import java.util.List;

public class MapMarkerManager {

    public void populateWithClientsMapMarkers(AndroidXMapFragment mapFragment) {
        //MAP MAKER MANAGER
        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();
        for(ClientMapMarker clientMapMarker : clients) {
            mapFragment.getMap().addMapObject(clientMapMarker.getMapMarker());
        }
    }
}

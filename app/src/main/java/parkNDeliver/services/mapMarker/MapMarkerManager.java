package parkNDeliver.services.mapMarker;

import com.here.android.mpa.mapping.AndroidXMapFragment;

import java.util.List;

public class MapMarkerManager {

    public void populateWithClientsMapMarkers(AndroidXMapFragment mapFragment) {
        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();
        for(ClientMapMarker clientMapMarker : clients) {
            mapFragment.getMap().addMapObject(clientMapMarker.getMapMarker());
        }
    }

    public void populateWithLoadUnloadsMapMarkers(AndroidXMapFragment mapFragment) {
        List<LoadUnloadMapMarker> loadUnloads = MapMarkerFabric.generateAllLoadUnloads();
        for(LoadUnloadMapMarker loadUnloadMapMarker : loadUnloads) {
            mapFragment.getMap().addMapObject(loadUnloadMapMarker.getMapMarker());
        }
    }
}

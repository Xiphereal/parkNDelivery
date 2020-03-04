package parkNDeliver.services.mapMarker;

import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapPolygon;
import parkNDeliver.services.isochrone.Isochrone;

import java.util.List;

public class MapMarkerManager {

    public void populateWithClientsMapMarkers(AndroidXMapFragment mapFragment) {
        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();
        for(ClientMapMarker clientMapMarker : clients) {
            MapMarker mapMarker = clientMapMarker.getMapMarker();
            mapFragment.getMap().addMapObject(mapMarker);
        }
    }

    public void populateWithLoadUnloadsMapMarkers(AndroidXMapFragment mapFragment) {
        List<LoadUnloadMapMarker> loadUnloads = MapMarkerFabric.generateAllLoadUnloads();
        for(LoadUnloadMapMarker loadUnloadMapMarker : loadUnloads) {
            MapMarker mapMarker = loadUnloadMapMarker.getMapMarker();
            mapFragment.getMap().addMapObject(mapMarker);

            Isochrone isochrone = new Isochrone(10, mapMarker.getCoordinate());
            isochrone.setMapFragment(mapFragment);

        }
    }
}

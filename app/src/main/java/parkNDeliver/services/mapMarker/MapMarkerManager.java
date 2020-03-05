package parkNDeliver.services.mapMarker;

import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapPolygon;
import parkNDeliver.services.isochrone.Isochrone;
import parkNDeliver.services.isochrone.IsochroneManager;

import java.util.List;
import java.util.Map;

public class MapMarkerManager {
    IsochroneManager isochroneManager;

    public MapMarkerManager() {
        isochroneManager = new IsochroneManager();
    }

    public void populateWithClientsMapMarkers(AndroidXMapFragment mapFragment) {

        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();

        for(ClientMapMarker clientMapMarker : clients) {
            drawMapObject(mapFragment, clientMapMarker.getMapMarker());
        }

    }

    public void populateWithLoadUnloadsMapMarkers(AndroidXMapFragment mapFragment) {

        List<LoadUnloadMapMarker> loadUnloads = MapMarkerFabric.generateAllLoadUnloads();

        for(LoadUnloadMapMarker loadUnloadMapMarker : loadUnloads) {
            MapMarker mapMarker = loadUnloadMapMarker.getMapMarker();

            drawMapObject(mapFragment, mapMarker);

            isochroneManager.setIsochronesFor(mapMarker, mapFragment);

        }

    }

    private void drawMapObject(AndroidXMapFragment mapFragment, MapMarker mapMarker) {
        mapFragment.getMap().addMapObject(mapMarker);
    }
}

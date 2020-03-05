package parkNDeliver.services.mapMarker;

import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.MapMarker;
import parkNDeliver.services.isochrone.IsochroneManager;

import java.util.LinkedList;
import java.util.List;

public class MapMarkerManager {
    IsochroneManager isochroneManager;
    List<ParkNDeliverMapMarker> activeParkNDeliverMapMarkers = new LinkedList<>();

    public MapMarkerManager() {
        isochroneManager = new IsochroneManager();
    }

    public void populateWithClientsMapMarkers(AndroidXMapFragment mapFragment) {

        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();

        for(ClientMapMarker clientMapMarker : clients) {
            drawMapObject(mapFragment, clientMapMarker.getMapMarker());

            activeParkNDeliverMapMarkers.add(clientMapMarker);
        }

    }

    public void populateWithLoadUnloadsMapMarkers(AndroidXMapFragment mapFragment) {

        List<LoadUnloadMapMarker> loadUnloads = MapMarkerFabric.generateAllLoadUnloads();

        for(LoadUnloadMapMarker loadUnloadMapMarker : loadUnloads) {
            MapMarker mapMarker = loadUnloadMapMarker.getMapMarker();

            drawMapObject(mapFragment, mapMarker);

            //isochroneManager.setIsochronesFor(loadUnloadMapMarker, mapFragment);

            activeParkNDeliverMapMarkers.add(loadUnloadMapMarker);
        }

    }

    public ParkNDeliverMapMarker getWrapper(MapMarker mapMarker) {

        for (ParkNDeliverMapMarker pndMapMarker: activeParkNDeliverMapMarkers) {
            if (pndMapMarker.getMapMarker().equals(mapMarker)) {
                return pndMapMarker;
            }
        }

        return null;
    }

    private void drawMapObject(AndroidXMapFragment mapFragment, MapMarker mapMarker) {
        mapFragment.getMap().addMapObject(mapMarker);
    }


}

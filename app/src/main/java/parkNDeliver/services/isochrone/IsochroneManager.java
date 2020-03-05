package parkNDeliver.services.isochrone;

import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapMarker;
import org.jetbrains.annotations.NotNull;
import parkNDeliver.services.mapMarker.MapMarkerManager;
import parkNDeliver.services.mapMarker.ParkNDeliverMapMarker;

import java.util.LinkedList;
import java.util.List;

public class IsochroneManager {

    public void setIsochronesFor(ParkNDeliverMapMarker customMapMarker, AndroidXMapFragment mapFragment) {

        MapMarker mapMarker = customMapMarker.getMapMarker();

        List<Isochrone> addedIsochrones = addIsochrones(mapFragment, mapMarker);

        customMapMarker.setActiveIsochrones(addedIsochrones);
    }

    public void removeIsochronesFor(ParkNDeliverMapMarker customMapMarker, Map map) {

        for (Isochrone isochrone : customMapMarker.getIsochrones()) {
            map.removeMapObject(isochrone.getMapPolygon());
        }

        customMapMarker.setActiveIsochrones(null);
    }

    @NotNull
    private List<Isochrone> addIsochrones(AndroidXMapFragment mapFragment, MapMarker mapMarker) {
        List<Isochrone> addedIsochrones = new LinkedList<>();

        addedIsochrones.add(
                IsochroneFabric.generateOutterMostIsochrone(mapMarker, mapFragment)
        );

        addedIsochrones.add(
                IsochroneFabric.generateMiddleIsochrone(mapMarker, mapFragment)
        );

        addedIsochrones.add(
                IsochroneFabric.generateInnerMostIsochrone(mapMarker, mapFragment)
        );

        return addedIsochrones;
    }





}

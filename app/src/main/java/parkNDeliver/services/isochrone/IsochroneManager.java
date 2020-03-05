package parkNDeliver.services.isochrone;

import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.MapMarker;

public class IsochroneManager {

    public void setIsochronesFor(MapMarker mapMarker, AndroidXMapFragment mapFragment) {

        IsochroneFabric.generateOutterMostIsochrone(mapMarker, mapFragment);
        IsochroneFabric.generateMiddleIsochrone(mapMarker, mapFragment);
        IsochroneFabric.generateInnerMostIsochrone(mapMarker, mapFragment);

    }

}

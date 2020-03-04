package parkNDeliver.services.isochrone;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.isoline.*;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapPolygon;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteWaypoint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Isochrone {
    IsolineRouter isolineRouter;
    IsolinePlan isolinePlan;
    AndroidXMapFragment mapFragment;

    public Isochrone(float minutes, GeoCoordinate origin) {
        isolineRouter = new IsolineRouter();
        isolineRouter.setConnectivity(IsolineRouter.Connectivity.ONLINE);

        isolinePlan = new IsolinePlan();
        isolinePlan.setStart(new RouteWaypoint(origin));
        setTime((int) minutes);

        isolinePlan.setRangeType(IsolinePlan.RangeType.TIME);

        configureIsochrone();

        calculateIsochrone();
    }

    private void configureIsochrone() {
        RouteOptions routeOptions = new RouteOptions();
        routeOptions.setTransportMode(RouteOptions.TransportMode.PEDESTRIAN);
        routeOptions.setRouteType(RouteOptions.Type.FASTEST);

        isolinePlan.setRouteOptions(routeOptions);
    }

    public void calculateIsochrone() {
        isolineRouter.calculateIsoline(isolinePlan, new IsolineRouter.Listener() {
            @Override public void onCalculateIsolineFinished(List<Isoline> response,
                                                             IsolineError error) {
                if (error == IsolineError.NONE) {
                    drawIsochrone(response);
                }
            }
        });
    }

    public void setTime(int minutes) {
        ArrayList<Integer> ranges = new ArrayList<>();
        ranges.add(minutes * 60); // range in seconds

        isolinePlan.setRanges(ranges);
    }

    private void drawIsochrone(List<Isoline> response) {
        //Find geoCoordinates of each isoline
        List<List<GeoPolygon>> geoPolygonsSet = new LinkedList<>();
        for (Isoline isoline : response) {
            geoPolygonsSet.add(isoline.getComponents());
        }

        for(List<GeoPolygon> geoPolygons : geoPolygonsSet) {
            for(GeoPolygon geoPolygon : geoPolygons) {
                MapPolygon mapPolygon = new MapPolygon(geoPolygon);
                mapPolygon.setFillColor(255);
                mapFragment.getMap().addMapObject(mapPolygon);
            }
        }
    }

    public void setMapFragment(AndroidXMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }


}

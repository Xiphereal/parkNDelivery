package parkNDeliver.services.isochrone;

import android.graphics.Color;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.MapMarker;

public class IsochroneFabric {
    private static int numberOfIsochronesPerMarker = 3;
    private static float maximunTimeInMinutes = 15;
    private static float lapseInMinutes = 5;

    private static int innerMostColor = Color.argb(60,30, 255, 30);
    private static int middleColor = Color.argb(60,30, 30, 255);
    private static int outterMostColor = Color.argb(60,255, 30, 30);

    public static Isochrone generateOutterMostIsochrone(MapMarker mapMarker, AndroidXMapFragment mapFragment) {
        return generateIsochrone(maximunTimeInMinutes, mapMarker.getCoordinate(), outterMostColor, mapFragment);
    }

    public static Isochrone generateInnerMostIsochrone(MapMarker mapMarker, AndroidXMapFragment mapFragment) {
        int timeInMinutes = (int) (maximunTimeInMinutes - lapseInMinutes * numberOfIsochronesPerMarker);
        return generateIsochrone(timeInMinutes, mapMarker.getCoordinate(), innerMostColor, mapFragment);
    }

    public static Isochrone generateMiddleIsochrone(MapMarker mapMarker, AndroidXMapFragment mapFragment) {
        int timeInMinutes = (int) (maximunTimeInMinutes - lapseInMinutes);
        return generateIsochrone(timeInMinutes, mapMarker.getCoordinate(), middleColor, mapFragment);
    }

    //* GETTERS & SETTERS */
    public static void setNumberOfIsochronesPerMarker(int numberOfIsochronesPerMarker) {
        IsochroneFabric.numberOfIsochronesPerMarker = numberOfIsochronesPerMarker;
    }

    public static void setMaximunTimeInMinutes(float maximunTimeInMinutes) {
        IsochroneFabric.maximunTimeInMinutes = maximunTimeInMinutes;
    }

    public static void setLapseInMinutes(float lapseInMinutes) {
        IsochroneFabric.lapseInMinutes = lapseInMinutes;
    }

    public static void setInnerMostColor(int innerMostColor) {
        IsochroneFabric.innerMostColor = innerMostColor;
    }

    public static void setMiddleColor(int middleColor) {
        IsochroneFabric.middleColor = middleColor;
    }

    public static void setOutterMostColor(int outterMostColor) {
        IsochroneFabric.outterMostColor = outterMostColor;
    }



    private static Isochrone generateIsochrone(float timeInMinutes, GeoCoordinate origin, int color, AndroidXMapFragment mapFragment) {
        Isochrone isochrone = new Isochrone(timeInMinutes, origin);
        isochrone.setIsochroneColor(color);
        isochrone.setMapFragment(mapFragment);
        return isochrone;
    }


}

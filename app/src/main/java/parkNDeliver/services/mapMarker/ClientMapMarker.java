package parkNDeliver.services.mapMarker;

import android.content.Context;
import androidx.annotation.NonNull;
import com.here.android.mpa.common.GeoCoordinate;


public class ClientMapMarker {

    private MapMarker mapMarker;
    private  Context context;
    //MapImage mapImage;

//    public ClientMapMarker(@NonNull GeoCoordinate geoCoordinates, Context context, MapImage mapImage) {
//        mapMarker = new MapMarker(geoCoordinates);
//        this.context = context;
//        this.mapImage = mapImage;
//    }

    public void setImage() {
//        MapMarkerImageStyle mapMarkerImageStyle = new MapMarkerImageStyle();
//        mapMarkerImageStyle.setScale(0.05f);
//        mapMarkerImageStyle.setAnchorPoint(new Anchor2D(0.5f, 1));
//        mapMarker.addImage(mapImage,mapMarkerImageStyle);
    }

    public MapMarker getMapMarker() {
        return mapMarker;
    }

}

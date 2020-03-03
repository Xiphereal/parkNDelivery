package parkNDeliver.services.mapMarker;

import android.content.Context;
import androidx.annotation.NonNull;
import com.example.parkndeliver.R;
import com.here.android.mpa.common.GeoCoordinate;

public class LoadUnloadMapMarker {

    private MapMarker mapMarker;
    private Context context;

    public LoadUnloadMapMarker(@NonNull GeoCoordinate geoCoordinates, Context context) {
        //mapMarker = new MapMarker(geoCoordinates);
        this.context = context;
    }

    public void setImage() {
//        MapImage mapImage = MapImageFactory.fromResource(context.getResources(), R.mipmap.cd);
//        MapMarkerImageStyle mapMarkerImageStyle = new MapMarkerImageStyle();
//        mapMarkerImageStyle.setScale(0.05f);
//        mapMarkerImageStyle.setAnchorPoint(new Anchor2D(0.5f, 1));
//        mapMarker.addImage(mapImage,mapMarkerImageStyle);
    }
}

package parkNDeliver.services.mapMarker;

import android.content.Context;
import androidx.annotation.NonNull;
import com.example.parkndeliver.R;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.mapviewlite.MapImage;
import com.here.sdk.mapviewlite.MapImageFactory;
import com.here.sdk.mapviewlite.MapMarker;
import com.here.sdk.mapviewlite.MapMarkerImageStyle;

public class LoadUnloadMapMarker {

    private MapMarker mapMarker;
    private Context context;

    public LoadUnloadMapMarker(@NonNull GeoCoordinates geoCoordinates, Context context) {
        mapMarker = new MapMarker(geoCoordinates);
        this.context = context;
    }

    public void setImage() {
        MapImage mapImage = MapImageFactory.fromResource(context.getResources(), R.mipmap.cd);
        MapMarkerImageStyle mapMarkerImageStyle = new MapMarkerImageStyle();
        mapMarkerImageStyle.setScale(0.05f);
        mapMarkerImageStyle.setAnchorPoint(new Anchor2D(0.5f, 1));
        mapMarker.addImage(mapImage,mapMarkerImageStyle);
    }
}

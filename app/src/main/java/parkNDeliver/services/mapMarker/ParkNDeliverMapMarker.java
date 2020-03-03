package parkNDeliver.services.mapMarker;

import android.content.Context;
import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.MapMarker;

public abstract class ParkNDeliverMapMarker {
    protected MapMarker mapMarker;
    protected Context context;
    protected Image image;

    protected void initialize(GeoCoordinate geoCoordinate, Context context, Image image) {
        mapMarker = new MapMarker(geoCoordinate);
        this.context = context;
        this.image = image;
    }

    protected void setImage() {
        mapMarker.setIcon(image);
        mapMarker.setSvgIconScaling(0.05f);
        mapMarker.setAnchorPoint(new PointF(0.5f, 1));
    }

    protected MapMarker getMapMarker() {
        return mapMarker;
    }
}

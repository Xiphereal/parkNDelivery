package parkNDeliver.services.mapMarker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.MapMarker;

public abstract class ParkNDeliverMapMarker {
    protected MapMarker mapMarker;
    protected Image image;

    protected void initialize(GeoCoordinate geoCoordinate, Image image) {
        mapMarker = new MapMarker(geoCoordinate);
        this.image = image;
    }

    protected void setImage() {
        mapMarker.setIcon(image);
        mapMarker.setAnchorPoint(new PointF(image.getWidth()/2, image.getHeight()));
    }

    protected MapMarker getMapMarker() {
        return mapMarker;
    }
}

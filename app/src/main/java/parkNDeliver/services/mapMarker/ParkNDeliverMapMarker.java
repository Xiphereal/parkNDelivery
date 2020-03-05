package parkNDeliver.services.mapMarker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.mapping.MapMarker;
import parkNDeliver.services.isochrone.Isochrone;

import java.util.List;

public abstract class ParkNDeliverMapMarker {
    protected MapMarker mapMarker;
    protected Image image;
    protected List<Isochrone> isochrones;

    protected void initialize(GeoCoordinate geoCoordinate, Image image) {
        mapMarker = new MapMarker(geoCoordinate);
        this.image = image;
    }

    protected void setImage() {
        mapMarker.setIcon(image);
        mapMarker.setZIndex(10);
        mapMarker.setAnchorPoint(new PointF(image.getWidth()/2, image.getHeight()));
    }

    public MapMarker getMapMarker() {
        return mapMarker;
    }

    public boolean hasActiveIsochrones() {
        return isochrones != null && !isochrones.isEmpty();
    }

    public List<Isochrone> getIsochrones() {
        return isochrones;
    }

    public void setActiveIsochrones(List<Isochrone> isochrones) {
        this.isochrones = isochrones;
    }

}

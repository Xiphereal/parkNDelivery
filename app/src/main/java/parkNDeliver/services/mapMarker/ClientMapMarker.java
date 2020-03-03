package parkNDeliver.services.mapMarker;

import android.content.Context;
import androidx.annotation.NonNull;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;

public class ClientMapMarker extends ParkNDeliverMapMarker{

    public ClientMapMarker(@NonNull GeoCoordinate geoCoordinate, Context context, Image image) {
        initialize(geoCoordinate, context, image);
    }

}

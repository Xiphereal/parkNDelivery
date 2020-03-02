package parkNDeliver.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.parkndeliver.R;
import com.here.sdk.core.Anchor2D;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.gestures.GestureType;
import com.here.sdk.mapviewlite.*;
import parkNDeliver.services.mapMarker.ClientMapMarker;
import parkNDeliver.services.mapMarker.MapMarkerFabric;

import parkNDeliver.data.CoordinatesReader;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MapViewLite mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CoordinatesReader.setResources(getResources());


        // Get a MapViewLite instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getGestures().disableDefaultAction(GestureType.DOUBLE_TAP);
        mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_TAP);

        MapMarkerFabric.setApplicationContext(this.getApplicationContext());

        //MAP MAKER MANAGER
        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();
        for(ClientMapMarker clientMapMarker : clients) {
            mapView.getMapScene().addMapMarker(clientMapMarker.getMapMarker());
        }

        loadMapScene();
    }

    private void loadMapScene() {
        // Load a scene from the SDK to render the map with a map style.
        mapView.getMapScene().loadScene(MapStyle.NORMAL_DAY, new MapScene.LoadSceneCallback() {
            @Override
            public void onLoadScene(@Nullable MapScene.ErrorCode errorCode) {
                if (errorCode == null) {
                    mapView.getCamera().setTarget(new GeoCoordinates(39.475720, -0.375099));
                    mapView.getCamera().setZoomLevel(14);
                } else {
                    //Log.d(TAG, "onLoadScene failed: " + errorCode.toString());
                }
            }
        });
    }

}

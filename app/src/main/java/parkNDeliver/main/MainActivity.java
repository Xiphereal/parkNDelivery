package parkNDeliver.main;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.parkndeliver.R;
import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.gestures.GestureType;
import com.here.sdk.mapviewlite.MapScene;
import com.here.sdk.mapviewlite.MapStyle;
import com.here.sdk.mapviewlite.MapViewLite;


public class MainActivity extends AppCompatActivity {

    private MapViewLite mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a MapViewLite instance from the layout.
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getGestures().disableDefaultAction(GestureType.DOUBLE_TAP);
        mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_TAP);
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

package parkNDeliver.main;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.parkndeliver.R;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import parkNDeliver.services.mapMarker.ClientMapMarker;
import parkNDeliver.services.mapMarker.MapMarkerFabric;

import parkNDeliver.data.CoordinatesReader;

import java.io.File;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Map map;
    private AndroidXMapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();

//        mapView.onCreate(savedInstanceState);
//        mapView.getGestures().disableDefaultAction(GestureType.DOUBLE_TAP);
//        mapView.getGestures().disableDefaultAction(GestureType.TWO_FINGER_TAP);

//        MapMarkerFabric.setApplicationContext(this.getApplicationContext());
//        MapImage mapImage = MapImageFactory.fromResource(getApplicationContext().getResources(), R.mipmap.pin);
//        MapMarkerFabric.setClientMapImageResource(mapImage);


        //populateWithClientsMapMarkers();

    }

    private void initialize() {
        setContentView(R.layout.activity_main);

        CoordinatesReader.setResources(getResources());

        // Search for the map fragment to finish setup by calling init().
        mapFragment = (AndroidXMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);

        // Set up disk cache path for the map service for this application
        // It is recommended to use a path under your application folder for storing the disk cache
        boolean success = com.here.android.mpa.common.MapSettings.setIsolatedDiskCacheRootPath(
                getApplicationContext().getExternalFilesDir(null) + File.separator + ".here-maps",
                "myIntentName");

        if (success) {
            loadMapScene();
        } else {
            Toast.makeText(getApplicationContext(), "Unable to set isolated disk cache path.", Toast.LENGTH_LONG);
        }
    }

//    private void populateWithClientsMapMarkers() {
//        //MAP MAKER MANAGER
//        List<ClientMapMarker> clients = MapMarkerFabric.generateAllClients();
//        for(ClientMapMarker clientMapMarker : clients) {
//            //mapView.getMapScene().addMapMarker(clientMapMarker.getMapMarker());
//        }
//    }

    private void loadMapScene() {
        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {
                if (error == OnEngineInitListener.Error.NONE) {
                    // retrieve a reference of the map from the map fragment
                    map = mapFragment.getMap();

                    // Set the map center to Valencia region (no animation)
                    map.setCenter(new GeoCoordinate(39.475720, -0.375099, 0.0),
                            Map.Animation.NONE);

                    // Set the zoom level to the average between min and max
                    map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                } else {
                    System.out.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });
    }

}

package parkNDeliver.main;

import android.graphics.Bitmap;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.parkndeliver.R;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.mapping.AndroidXMapFragment;
import com.here.android.mpa.mapping.Map;
import parkNDeliver.services.mapMarker.ClientMapMarker;
import parkNDeliver.services.mapMarker.MapMarkerFabric;

import parkNDeliver.data.CoordinatesReader;
import parkNDeliver.services.mapMarker.MapMarkerManager;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Map map;
    private AndroidXMapFragment mapFragment;
    private MapMarkerManager mapMarkerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize();

        //disableUnwantedGestures();

        MapMarkerFabric.setApplicationContext(this.getApplicationContext());

        //* Get custom images for both clients & loadUnloadPoints and pass them to the MapMarkerFabric. *//
        setMapMarkerImages();

    }

    private void initialize() {
        setContentView(R.layout.activity_main);

        CoordinatesReader.setResources(getResources());


        // Search for the map fragment to finish setup by calling init().
        mapFragment = (AndroidXMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragmentExample);

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
                    //map.setZoomLevel((map.getMaxZoomLevel() + map.getMinZoomLevel()) / 2);
                    map.setZoomLevel(14);

                    populateMap();
                } else {
                    System.err.println("ERROR: Cannot initialize Map Fragment");
                }
            }
        });
    }

    private void populateMap() {
        mapMarkerManager = new MapMarkerManager();
        mapMarkerManager.populateWithClientsMapMarkers(mapFragment);
        mapMarkerManager.populateWithLoadUnloadsMapMarkers(mapFragment);
    }

    private void setMapMarkerImages() {
        Image clientImage = new Image();
        Image loadUnloadImage = new Image();

        try {
            clientImage.setImageResource(R.mipmap.mockup_client_pin);
            loadUnloadImage.setImageResource(R.mipmap.mockup_load_unload_pin);
        } catch (IOException e) {
            System.err.println("Failed to load requested images.");
            e.printStackTrace();
        }

        MapMarkerFabric.setClientImageResource(clientImage);
        MapMarkerFabric.setLoadUnloadImageResource(loadUnloadImage);

    }

    private void disableUnwantedGestures() {
        mapFragment.getMapGesture().setDoubleTapEnabled(false);
        mapFragment.getMapGesture().setTwoFingerTapEnabled(false);
    }

}

package parkNDeliver.data;

public class LoadUnload {

    private float latitude;
    private float longitude;
    private String locationName;

    public LoadUnload(String locationName, float latitude, float longitude) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getId() {
        return locationName;
    }
}

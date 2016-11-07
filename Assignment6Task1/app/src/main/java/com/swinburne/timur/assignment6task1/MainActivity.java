package com.swinburne.timur.assignment6task1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String API_KEY = "AIzaSyDYpMAtC1ZXt6xeQyDVfSHtB3Nd7magALc";
    private static final String FILE_PATH = "/sdcard/assignment6.jpg";

    /**
     * Convert EXIF string to degrees
     * Adapted from http://stackoverflow.com/a/5269531
     * @param exifString Coordinate EXIF String
     * @return Coordinate in degrees
     */
    private Double convertDegree(String exifString) {
        String[] dimensions = exifString.split(",", 3);

        String[] degrees = dimensions[0].split("/", 2);
        Double degree0 = new Double(degrees[0]);
        Double degree1 = new Double(degrees[1]);
        Double degree = degree0/degree1;

        String[] minutes = dimensions[1].split("/", 2);
        Double minute0 = new Double(minutes[0]);
        Double minute1 = new Double(minutes[1]);
        Double minute = minute0/minute1;

        String[] seconds = dimensions[2].split("/", 2);
        Double second0 = new Double(seconds[0]);
        Double second1 = new Double(seconds[1]);
        Double second = second0/second1;

        Double result = new Double(degree + (minute/60) + (second/3600));
        return result;
    }

    /**
     * Returns EXIF string degrees depending on EXIF direction reference
     * @param exifString EXIF GPS string
     * @param exifStringRef EXIF GPS REF string
     * @return Coordinate in degrees
     */
    private Double getDegree(String exifString, String exifStringRef) {
        if (exifStringRef.equals("N") || exifStringRef.equals("E")) {
            return convertDegree(exifString);
        } else {
            return 0 - convertDegree(exifString);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("CLICK", "Loading image");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView latitudeView = (TextView) findViewById(R.id.textViewLatitudeValue);
        TextView longitudeView = (TextView) findViewById(R.id.textViewLongitudeValue);
        final TextView addressView = (TextView) findViewById(R.id.textViewAddressValue);

        try {
            File imageFile = new File(FILE_PATH);
            if (imageFile.exists()) {
                // Load image
                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(View.VISIBLE);

                // Get EXIF data
                ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
                String latitude = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
                String latitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
                String longitude = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
                String longitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

                Log.d("GPS", "{ latitude: " + latitude
                        + ", latitude_ref: " + latitudeRef
                        + ", longitude: " + longitude
                        + ", longitude_ref: " + longitudeRef
                        + " }");

                Double latitudeValue = getDegree(latitude, latitudeRef);
                Double longitudeValue =  getDegree(longitude, longitudeRef);

                // Set TextViews
                latitudeView.setText(latitudeValue.toString());
                longitudeView.setText(longitudeValue.toString());

                Log.d("GPS_CONVERT", "{ latitude: " + latitudeValue.toString()
                        + ", longitude: " + longitudeValue.toString()
                        + " }");

                // Get street address
                String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                        + latitudeValue.toString() + ","
                        + longitudeValue.toString() + "&key="
                        + API_KEY + "&result_type=street_address";

                RequestQueue queue = Volley.newRequestQueue(this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    if (response.getString("status").equals("OK")) {
                                        JSONObject result = response.getJSONArray("results").getJSONObject(0);
                                        addressView.setText(result.getString("formatted_address"));
                                    }
                                } catch (JSONException e) {
                                    Log.e("JSON", "Error parsing JSON data");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", "Error with Google Maps API GET request");
                            }
                        }
                );
                queue.add(jsonObjectRequest);

            }
        } catch (FileNotFoundException e) {
            Log.e("FILE", "File not found");
        } catch (IOException e) {
            Log.e("IO", "Error reading raw file");
        }
    }
}

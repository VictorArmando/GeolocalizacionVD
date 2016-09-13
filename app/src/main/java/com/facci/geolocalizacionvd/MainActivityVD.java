package com.facci.geolocalizacionvd;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.jar.Manifest;

public class MainActivityVD extends Activity {

    LocationManager locManager;

    private double latitud;
    private double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_vd);

        //INICIALIZAR LocManager
        locManager =(LocationManager)getSystemService(LOCATION_SERVICE);

        //OBTENER LA LISTA DE PROVEEDORES
        List<String> listaProvider = locManager.getAllProviders();

        //OBTENER EL PRIMER PROVEEDOR DE LA LISTA
        LocationProvider provider = locManager.getProvider(listaProvider.get(0));

    }


    public  void ActualizarLatlongClick (View v){

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED  ){

        }

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2*60*1000,10,locationListenerGPS);
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            longitud = location.getLongitude();
            latitud = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    EditText txtLongitud = (EditText) findViewById(R.id.txtLongitud);
                    EditText txtLatitud = (EditText) findViewById(R.id.txtLatitud);

                    txtLatitud.setText(latitud+"");
                    txtLongitud.setText(String.valueOf(longitud));
                }
            });

        }

        @Override
        public void onStatusChanged(String provider, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_vd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

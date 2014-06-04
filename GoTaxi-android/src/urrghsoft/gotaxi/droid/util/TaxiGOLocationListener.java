package urrghsoft.gotaxi.droid.util;

import java.util.List;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TaxiGOLocationListener implements LocationListener {
	
	private TextView txtLog;
	private LocationManager locationManager;
	private Geocoder geoCoder;
	private ProgressBar pgBar;
	private TextView txtLat;
	private TextView txtLon;
	private TextView txtEndereco;
	private Button btnChamarTaxi;
	private Location location;
	public TaxiGOLocationListener(
			LocationManager locationManager, 
			Geocoder geoCoder,
			View txtLog, 
			View txtLat,
			View txtLon,
			View txtEndereco,
			View pgBar,
			View btnChamarTaxi) {
		this.locationManager = locationManager;
		
		this.geoCoder = geoCoder;
		this.txtLog = (TextView)txtLog;
		this.pgBar = (ProgressBar)pgBar;
		this.txtLat = (TextView)txtLat;
		this.txtLon = (TextView)txtLon;
		this.txtEndereco =(TextView) txtEndereco;
		this.btnChamarTaxi = (Button)btnChamarTaxi;
	}
	
	public void onLocationChanged(Location location) {
		this.location = location;
		txtLog.setText("Pesquisando local ... ");
		double lat = location.getLatitude();
		double lng = location.getLongitude();
		txtLat.setText("Latitude: " + lat);
		txtLon.setText("Longitude: " + lng);
		locationManager.removeUpdates(this);
		try {
			List<Address> addresses = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			Address a = addresses.get(0);
			txtLog.setText("Endere�o encontrado !");
			txtEndereco.setText(a.getThoroughfare() + " : " +  a.getFeatureName() + " - " + a.getAdminArea() + ", " +  a.getLocality() + " - " + a.getCountryName());
			btnChamarTaxi.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			pgBar.setVisibility(ProgressBar.GONE);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}
	
	public Location getLastLocation(){
		return location;
	}


}

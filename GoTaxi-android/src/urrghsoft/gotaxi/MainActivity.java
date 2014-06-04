package urrghsoft.gotaxi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;

import urrghsoft.gotaxi.droid.util.TaxiGOLocationListener;
import urrghsoft.gotaxi.model.Corrida;
import urrghsoft.gotaxi.model.Passageiro;
import urrghsoft.gotaxi.service.TaxiGOService;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity {
	private static final int ADD_NEW = 0;
	static TaxiGOLocationListener locationListener;
	LocationManager locationManager;
	Geocoder geoCoder;
	String[] items;
	Context c;
	Corrida corrida;
	Timer timer;
	Activity activity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		geoCoder = new Geocoder(this);
		corrida = new Corrida();
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.main);
		activity = this;
		final Button btnLocalizar = (Button) findViewById(R.id.btnLocalizar);
		btnLocalizar.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				listenerLocalizacao();
			}
		});
		final Button btnChamarTaxi = (Button) findViewById(R.id.btnChamarTaxi);
		btnChamarTaxi.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				solicitarUnidade();
			}
		});
		fillPassageiro();
		listenerLocalizacao();
	}

	private void listenerLocalizacao() {
		final ProgressBar pgBar = (ProgressBar)findViewById(R.id.pgBar);
		pgBar.setVisibility(ProgressBar.VISIBLE);
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		locationListener = new TaxiGOLocationListener(
				locationManager, 
				geoCoder,
				findViewById(R.id.txtLog), 
				findViewById(R.id.txtLatitude), 
				findViewById(R.id.txtLongitude), 
				findViewById(R.id.txtEndereco), 
				findViewById(R.id.pgBar),
				findViewById(R.id.btnChamarTaxi)
			);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4,0, locationListener);
	}
	
	private void fillPassageiro(){
		Account[] accounts = AccountManager.get(this).getAccounts();
		
		/*
		 * Caso utilize o emulador, o valor de accounts pode retornar null. Pode ser necessario configurar
		 * uma conta google para retornar o valor.		
		*/
		String id = accounts[0].name;
		TelephonyManager tMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String mPhoneNumber = tMgr.getLine1Number();
		Passageiro passageiro = new Passageiro();
		passageiro.setCelular(mPhoneNumber);
		passageiro.setEmail(id);
		corrida.setPassageiro(passageiro);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem sair = menu.add(0, ADD_NEW, Menu.NONE, R.string.sair);
		sair.setShortcut('0', 's');
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.sair:
			sair();
			return true;
		default:
			return sair();
		}
	}
	private void solicitarUnidade() {
		ProgressDialog progressDialog = ProgressDialog.show(activity, "", "Registrando corrida...", true);
		final TextView txtLog = (TextView)findViewById(R.id.txtLog);
		final TextView lblSolicitacao = (TextView)findViewById(R.id.lblSolicitacao);
		TextView txtEndereco = (TextView)findViewById(R.id.txtEndereco);
		double lat = locationListener.getLastLocation().getLatitude();
		double lng = locationListener.getLastLocation().getLongitude();
		
		corrida.setLatOrigem(lat);
		corrida.setLonOrigem(lng);
		corrida.setLogradouro(txtEndereco.getText().toString());
		
		//Obter a URL padrao, descrita no arquivo taxigo.properties.
		AssetManager manager = activity.getResources().getAssets();
		String url = null;
		try {
			InputStream in = manager.open("taxigo.properties");
			Properties prop = new Properties();
			prop.load(in);
			url = (String) prop.get("URLService");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		final TaxiGOService service = new TaxiGOService(url);
		try {
			Corrida retorno = service.solicitar(corrida);
			final Integer idCorrida = retorno.getId();
			((TextView) findViewById(R.id.lblSolicitacao)).setText("ID Corrida: " + idCorrida + " : Aguardando confirmacao");	
			
			
			
			final Handler mHandler = new Handler();
			
			
			final Runnable updateTask = new Runnable() {
				@Override
				public void run() {
			    	try{
			    		Log.i("timer", "Buscando corrida"); 
			    		txtLog.setText("Verificando solicita��o ...");
			    		
			    		Corrida c = service.consultar(idCorrida);
			    		Log.d("Retorno", c.getStatus());
			    		txtLog.setText("Resposta: " + c.getStatus() );
			    		if(!"Pendente".equalsIgnoreCase(c.getStatus())){
			    			lblSolicitacao.setText(c.getStatus() + "\n" + 
			    					" unidade: " + c.getUnidade().getId()  + "\n" +
			    					" motorista: " + c.getUnidade().getNomeMotorista() + "\n" +
			    					" carro: " + c.getUnidade().getNumero() + " (" + c.getUnidade().getPlaca() + ")");   
			    			mHandler.removeCallbacks(this);
			    			((Button)findViewById(R.id.btnLocalizar)).setVisibility(Button.INVISIBLE);
			    		}else{
			    			//Caso seja necessario aguardar mais, o handler novamente executara em 2 segundos.
			    			mHandler.postDelayed(this, 2000);
			    		}
			    	}catch(Exception e){
			    		Log.d("erro", e.getLocalizedMessage());
			    	}
				}
			};
			
			/*Somente a thread principal pode atualizar os controles da Activity.
			 * Por isso 'e necessario um Handler. O Handler executara o metodo daqui a 2 segundos. 
			 */
			mHandler.postDelayed(updateTask, 2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		progressDialog.dismiss();
		progressDialog.cancel();
	}
	
	//Modo forceps para sair do programa.
	private boolean sair() {
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		return true;
	}

}
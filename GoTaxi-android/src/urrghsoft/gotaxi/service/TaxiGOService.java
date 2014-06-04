package urrghsoft.gotaxi.service;


import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.XMLReader;

import urrghsoft.gotaxi.model.Corrida;
import urrghsoft.gotaxi.xml.CorridaHandler;
import android.location.Location;
import android.util.Log;


public class TaxiGOService {
	
	private HttpClient httpclient = new DefaultHttpClient();
	private String urlService;
	public TaxiGOService(String urlService) {
		this.urlService = urlService;
	}
	public Corrida solicitar(Corrida corrida) throws Exception{
		String xml = getXML(corrida);
		try {
			HttpPut put = new HttpPut(urlService + "/corrida/solicitar");
			ResponseHandler<String> handler = new BasicResponseHandler();
			StringEntity myEntity;

			myEntity = new StringEntity(xml, "UTF-8");
			put.addHeader("Content-Type", "application/xml; charset=UTF-8");
			myEntity.setContentType(put.getFirstHeader("Content-Type"));
			put.setEntity(myEntity);
					
			String result = httpclient.execute(put, handler);
			Log.d("result", result);
			
			return parseXML(result);
		}  catch (Exception e) {
			e.printStackTrace();
			Log.d("ErroTaxiGOService", e.getLocalizedMessage());
		}
		return null;
	}
	
	public Corrida consultar(Integer idCorrida){
		HttpGet get = new HttpGet(urlService + "/corrida/consultar/" + idCorrida);
		ResponseHandler<String> handler = new BasicResponseHandler();
		String result;
		try {
			result = httpclient.execute(get, handler);
			Log.d("result", result);
			return parseXML(result);
		}catch (Exception e) {
			e.printStackTrace();
			Log.d("Erro ao consultar corrida", e.getLocalizedMessage());
		}
		return null;
	}
	
	private Corrida parseXML(String xml) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		XMLReader xr = sp.getXMLReader();
		CorridaHandler passageiroHandler = new CorridaHandler();
		xr.setContentHandler(passageiroHandler);
		sp.parse(new ByteArrayInputStream(xml.getBytes()), passageiroHandler);
		Log.d("handler", passageiroHandler.getCorrida().getId() + "");
		return passageiroHandler.getCorrida();
	}
	
	private String getXML(Corrida corrida){
		 String ret = "<corrida>" + 
		  "<latOrigem>" +  Location.convert(corrida.getLatOrigem(), Location.FORMAT_DEGREES).replace(",",".")+  "</latOrigem> " + 
		  "<lonOrigem>" + Location.convert(corrida.getLonOrigem(), Location.FORMAT_DEGREES).replace(",",".") + "</lonOrigem> " + 
		  "<logradouro>" + corrida.getLogradouro() + "</logradouro> " +
		  "<passageiro>" + 
		  "	<celular>" + corrida.getPassageiro().getCelular().trim() + "</celular> " + 
		  "	<email>"+ corrida.getPassageiro().getEmail().trim() +  "</email> " + 
		  "</passageiro>" + 
		  "</corrida>";
		 
		 return ret;
	}

}

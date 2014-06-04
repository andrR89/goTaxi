package urrghsoft.gotaxi.xml;

import java.util.Calendar;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import urrghsoft.gotaxi.model.Corrida;
import urrghsoft.gotaxi.model.Passageiro;
import urrghsoft.gotaxi.model.Unidade;
import android.text.format.DateFormat;
import android.util.Log;

public class CorridaHandler extends DefaultHandler {
	
	private String lastRootTag;
	private String lastTag;
	private final Corrida corrida;
	
	public CorridaHandler() {
		corrida = new Corrida();
		Passageiro passageiro = new Passageiro();
		corrida.setPassageiro(passageiro);
		Unidade unidade = new Unidade();
		corrida.setUnidade(unidade);
	}
	

	@Override
	public void endDocument() throws SAXException {
		Log.d("endDocument", "endDocument");
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(localName.equalsIgnoreCase("corrida") || localName.equalsIgnoreCase("passageiro") || localName.equalsIgnoreCase("unidade")){
			lastRootTag = localName;
		}
		lastTag = qName;
		Log.d("startElement" , "local: " + localName + " qName: " + qName);
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equalsIgnoreCase("corrida") || localName.equalsIgnoreCase("passageiro") || localName.equalsIgnoreCase("unidade")){
			lastRootTag = "";
		}
		Log.d("endElement" , "local: " + localName + " qName: " + qName);
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		String valor = new String(ch).substring(start, length);
		if(lastTag.equalsIgnoreCase("horaSolicitacao")){
			Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(new Date(valor));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			corrida.setHoraSolicitacao(cal);
		}else if(lastTag.equalsIgnoreCase("id")){
			if(lastRootTag.equalsIgnoreCase("corrida")){
				corrida.setId(Integer.parseInt(valor));
			}else if(lastRootTag.equalsIgnoreCase("unidade")){
				corrida.getUnidade().setId(Integer.parseInt(valor));
			}
		}else if(lastTag.equalsIgnoreCase("latOrigem")){
			corrida.setLatOrigem(Double.parseDouble(valor));
		}else if(lastTag.equalsIgnoreCase("lonOrigem")){
			corrida.setLonOrigem(Double.parseDouble(valor));
		}else if(lastTag.equalsIgnoreCase("celular")){
			corrida.getPassageiro().setCelular(valor);
		}else if(lastTag.equalsIgnoreCase("email")){
			corrida.getPassageiro().setEmail(valor);
		}else if(lastTag.equalsIgnoreCase("qru")){
			corrida.setQru(valor);
		}else if(lastTag.equalsIgnoreCase("status")){
			corrida.setStatus(valor);
		}else if(lastTag.equalsIgnoreCase("nomeMotorista")){
			corrida.getUnidade().setNomeMotorista(valor);
		}else if(lastTag.equalsIgnoreCase("numero")){
			corrida.getUnidade().setNumero(valor);
		}else if(lastTag.equalsIgnoreCase("placa")){
			corrida.getUnidade().setPlaca(valor);
		}else if(lastTag.equalsIgnoreCase("logradouro")){
			corrida.setLogradouro(valor);
		}
		
		Log.d("characters" , "chars: " + valor);
	}
	public Corrida getCorrida() {
		return corrida;
	}
	
	
}

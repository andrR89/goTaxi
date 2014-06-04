package urrghsoft.gotaxi.manager.util;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import urrghsoft.gotaxi.taxigoweb.entidades.Corrida;
import urrghsoft.gotaxi.taxigoweb.entidades.Unidade;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class TaxiGOServices {
	
	private ClientConfig config;
	private Client client;
	WebResource corridaResource;
	public TaxiGOServices() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		corridaResource = client.resource(getBaseURI()).path("rest");
	}
	
	public List<Corrida> getCorridas(){
		List<Corrida> corridasList = null;
		try{
	        Corrida[] corridas = corridaResource.path("corrida").path("pendentes").accept(MediaType.APPLICATION_XML).get(Corrida[].class);
			corridasList = Arrays.asList(corridas);
		}catch(Exception e){
			e.printStackTrace();
		}
		return corridasList;
	}
	
	public List<Unidade> getUnidades(){
		List<Unidade> unidadesList = null;
		try{
	        Unidade[] unidades = corridaResource.path("unidade").path("consultartodas").accept(MediaType.APPLICATION_XML).get(Unidade[].class);
	        unidadesList = Arrays.asList(unidades);
		}catch(Exception e){
			e.printStackTrace();
		}
		return unidadesList;
	}
	
	public void atualizarCorrida(Corrida corrida) throws Exception{
		try{
	        corridaResource.path("corrida").path("atualizar").accept(MediaType.APPLICATION_XML).put(corrida);
		}catch(Exception e){
			throw e;
		}
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/GoTaxi").build();
	}


}

package urrghsoft.gotaxi.rest;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import urrghsoft.gotaxi.controller.CorridaEJB;
import urrghsoft.gotaxi.model.Corrida;

@Path("/corrida")
@RequestScoped
public class CorridaResource {

	@Inject
	private CorridaEJB corridaEjb;

	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/solicitar")
	public Response solicitar(Corrida corrida) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			corrida.setHoraSolicitacao(cal.getTime());
			corrida.setStatus("Pendente");
			corridaEjb.persist(corrida);
			return Response.ok(corrida).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/consultar/{idCorrida}")
	public Response consultar(@PathParam("idCorrida") Integer idCorrida) {
		try {
			Corrida corrida = corridaEjb.find(idCorrida);
			return Response.ok(corrida).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/atualizar")
	public Response atualizar(Corrida corrida) {
		try {
			corridaEjb.merge(corrida);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok("Informacoes de corrida atualizadas !").build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/finalizar/{idCorrida}")
	public Response finalizar(@PathParam("idCorrida") Integer idCorrida) {
		try {
			Corrida corrida = corridaEjb.find(idCorrida);
			corrida.setStatus("Finalizada");
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			corrida.setHoraFinalizacao(cal.getTime());
			corridaEjb.merge(corrida);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		return Response.ok("Finalizacao com sucesso !").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/pendentes")
	public Response obterPendentes() {
		try {
			List<Corrida> pendentes = corridaEjb.getByStatus("Pendente");
			Corrida[] corridas = new Corrida[pendentes.size()];
			for (int i = 0; i < pendentes.size(); i++) {
				corridas[i] = pendentes.get(i);
			}
			return Response.ok(corridas).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@Path("/todos")
	public Response obterTodos() {
		try {
			List<Corrida> pendentes = corridaEjb.findAll();
			Corrida[] corridas = new Corrida[pendentes.size()];
			for (int i = 0; i < pendentes.size(); i++) {
				corridas[i] = pendentes.get(i);
			}
			return Response.ok(corridas).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}

package realstate.api.rest;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import realstate.api.facade.PropertyFacade;
import realstate.api.facade.PropertyFacadeFactory;
import realstate.api.integration.Storage;
import realstate.api.repository.PropertyNotFoundException;

@Path("properties")
public class PropertyService {
	private static Logger log = LogManager.getLogger(PropertyService.class);

	private @Context ServletContext ctx;
	private PropertyFacade facade;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(String body) throws IOException {
		log.info(String.format("create: %s", body));
		String response = getFacade().create(body);
		log.info(String.format("response: %s", response));
		return Response
				.status(Status.CREATED)
				.entity(response)
				.build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@PathParam("id") int id) 
			throws PropertyNotFoundException, IOException {
		log.info(String.format("find: %s", id));
		String response = getFacade().find(id);
		log.info(String.format("response: %s", response));
		return Response.ok(response).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOnBoundarie(@QueryParam("ax") int ax, @QueryParam("ay") int ay, @QueryParam("bx") int bx,
			@QueryParam("by") int by) throws JsonProcessingException, IOException {
		log.info(String.format("findOnBoundarie: %s,%s,%s,%s", ax, ay, bx, by));
		String response = getFacade().findOnBoundarie(ax, ay, bx, by);
		log.info(String.format("response: %s", response));
		return Response.ok(response).build();
	}

	private PropertyFacade getFacade() {
		if (facade == null) {
			facade = new PropertyFacadeFactory(retrieveStorageFromWebContext()).get();
		}
		return facade;
	}

	private Storage retrieveStorageFromWebContext() {
		Storage storage = (Storage) ctx.getAttribute("storage");
		if (storage == null) {
			storage = new Storage();
			ctx.setAttribute("storage", storage);
			log.info("new Storage has been created");
		}
		return storage;
	}
}
package realstate.api.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import realstate.api.repository.PropertyNotFoundException;

@Provider
public class PropertyNotFoundExceptionMapper implements ExceptionMapper<PropertyNotFoundException> {
	private static Logger log = LogManager.getLogger(PropertyNotFoundExceptionMapper.class);
	@Override
	public Response toResponse(PropertyNotFoundException ex) {
		log.error(ex);
		return Response
				.status(Status.NOT_FOUND)
				.entity(ex.getMessage())
				.type("text/plain")
				.build();
	}
}

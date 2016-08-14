package realstate.api.rest.exception.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

@Provider
public class JsonProcessingExceptionMapper implements ExceptionMapper<JsonProcessingException> {
	private static Logger log = LogManager.getLogger(JsonProcessingExceptionMapper.class);
	@Override
	public Response toResponse(JsonProcessingException ex) {
		log.error(ex);
		return Response
				.status(Status.BAD_REQUEST)
				.entity(ex.getMessage())
				.type("text/plain")
				.build();
	}
}
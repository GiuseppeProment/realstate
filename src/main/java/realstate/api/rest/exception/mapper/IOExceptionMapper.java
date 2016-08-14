package realstate.api.rest.exception.mapper;

import java.io.IOException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Provider
public class IOExceptionMapper implements ExceptionMapper<IOException> {
	private static Logger log = LogManager.getLogger(IOExceptionMapper.class);
	@Override
	public Response toResponse(IOException ex) {
		log.error(ex);
		return Response
				.status(Status.INTERNAL_SERVER_ERROR)
				.entity(ex.getMessage())
				.type("text/plain")
				.build();
	}
}
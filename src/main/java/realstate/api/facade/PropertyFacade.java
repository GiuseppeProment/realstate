package realstate.api.facade;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import realstate.api.domain.Boundarie;
import realstate.api.domain.Coordinate;
import realstate.api.domain.Property;
import realstate.api.domain.PropertyBag;
import realstate.api.repository.PropertyNotFoundException;
import realstate.api.repository.PropertyRepository;

public class PropertyFacade {

	private ObjectMapper mapper;
	private PropertyRepository repository;

	public PropertyFacade(PropertyRepository repository) {
		super();
		this.repository = repository;
		mapper = new ObjectMapper();
	}

	public String create(String contents) throws JsonProcessingException, IOException {
		Property property = repository.create(contents);
		return mapper.writeValueAsString(property);
	}

	public String find(int id) throws JsonProcessingException, IOException, PropertyNotFoundException  {
		Property property = repository.find(id);
		return mapper.writeValueAsString(property);
	}

	public String findOnBoundarie(int ax, int ay, int bx, int by) throws JsonProcessingException, IOException  {
		Coordinate upperLeft = new Coordinate(ax, ay);
		Coordinate bottomRight = new Coordinate(bx, by);
		Collection<Property> properties = repository.find(new Boundarie(upperLeft, bottomRight));
		return mapper.writeValueAsString(new PropertyBag(properties));
	}
}

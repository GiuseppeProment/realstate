package realstate.api.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import realstate.api.domain.Boundarie;
import realstate.api.domain.Property;
import realstate.api.integration.Storage;

public class PropertyRepository {

	private ObjectMapper mapper = new ObjectMapper();
	private ProvinceRepository provinceRepository;
	private Storage storage;
	
	public PropertyRepository(ProvinceRepository provinceRepository, Storage storage) {
		super();
		this.provinceRepository = provinceRepository;
		this.storage = storage;
	}
	
	public Property create(String contents) throws JsonProcessingException, IOException  {
			return addNewProperty(mapper.readValue(contents, Property.class));
	}

	public Collection<Property> find(Boundarie boundarie ) throws JsonProcessingException, IOException {
		return getStorage()
			.getProperties()
			.values()
			.parallelStream()
			.filter(p -> boundarie.contains( p.getX(), p.getY() ) )
			.collect(Collectors.toList());
	}

	public Property find(int id) throws JsonProcessingException, IOException, PropertyNotFoundException {
		Property property = getStorage().getProperties().get(id);
		if (property==null) {
			throw new PropertyNotFoundException();
		}
		return property;
	}

	private Property addNewProperty(Property property) throws JsonProcessingException, IOException {
		
		property.setId( getStorage()
				.getProperties()
				.values()
				.parallelStream()
				.mapToInt(p -> p.getId())
				.max()
				.getAsInt() + 1 );
		
		getStorage()
			.getProperties()
			.put(property.getId(), property);
		
		initializeProvincesOnProperty(property);
		
		return property;
	}

	private Storage getStorage() throws JsonProcessingException, IOException {
		if (storage.getProperties().isEmpty()) {
			initializeProperties();
		}
		return storage;
	}

	private void initializeProperties() throws JsonProcessingException, IOException {
			InputStream resource = this.getClass().getResourceAsStream("/properties.json");
			JsonNode root = mapper.readTree( resource );
			for (JsonNode node : root.get("properties")) {
				Property property = mapper.treeToValue(node, Property.class);
				initializeProvincesOnProperty(property);
				storage.getProperties().put( node.get("id").asInt(), property );
			}
	}

	private Property initializeProvincesOnProperty(Property property) throws JsonProcessingException, IOException {
		if (property.getProvinces() == null){
			property.setProvinces( provinceRepository.findOnCoordinates( property.getX(),property.getY() ) );
		}
		return property;
	}

}

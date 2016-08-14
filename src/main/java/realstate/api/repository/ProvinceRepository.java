package realstate.api.repository;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import realstate.api.domain.Province;
import realstate.api.integration.Storage;

public class ProvinceRepository {

	private ObjectMapper mapper;
	private Storage storage;

	public ProvinceRepository(Storage storage) {
		super();
		this.storage = storage;
		mapper = new ObjectMapper();
	}

	public Province[] findOnCoordinates(int x, int y) throws JsonProcessingException, IOException {
		return getProvinces().stream().filter(p -> p.contains(x, y)).toArray(Province[]::new);
	}

	private Collection<Province> getProvinces() throws JsonProcessingException, IOException {
		if (storage.getProvinces().isEmpty()) {
			initializeProvinces();
		}
		return storage.getProvinces();
	}

	private void initializeProvinces() throws JsonProcessingException, IOException {
		JsonNode root = mapper.readTree(this.getClass().getResourceAsStream("/provinces.json"));
		for (Iterator<Entry<String, JsonNode>> iterator = root.fields(); iterator.hasNext();) {
			Entry<String, JsonNode> keyValue = iterator.next();
			Province province = mapper.treeToValue(keyValue.getValue(), Province.class);
			province.setName(keyValue.getKey());
			storage.getProvinces().add(province);
		}
	}

}

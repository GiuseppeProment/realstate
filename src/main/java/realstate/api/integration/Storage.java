package realstate.api.integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import realstate.api.domain.Property;
import realstate.api.domain.Province;

public class Storage {
	
	private Map<Integer, Property> properties = new ConcurrentHashMap<>();
	private Collection<Province> provinces = new ArrayList<>();

	public Collection<Province> getProvinces() {
		return provinces;
	}

	public Map<Integer, Property> getProperties() {
		return properties;
	}
}

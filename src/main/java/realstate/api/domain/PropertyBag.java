package realstate.api.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"foundProperties","properties"})
public class PropertyBag {
	private Property[] properties;
	public PropertyBag(Collection<Property> bag ) {
		super();
		this.properties = bag.stream().toArray(Property[]::new);
	}
	public PropertyBag(Property property) {
		this.properties = new Property[]{property};
	}
	public int getFoundProperties() {
		return properties.length;
	}
	public Property[] getProperties() {
		return properties;
	}
}

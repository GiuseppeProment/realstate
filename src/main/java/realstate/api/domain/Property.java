package realstate.api.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Property {
	
	private int id;
	private String title;
	private int price;
	private String description;
	private int x;
	private int y;
	private int beds;
	private int baths;
	private Province[] provinces;
	private int squareMeters;

	public int getBaths() {
		return baths;
	}

	public int getBeds() {
		return beds;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	@JsonGetter("provinces")
	public String[] getProvinceNames() {
		if (provinces==null) return null;
		return Arrays
				.stream(provinces)
				.map(p -> p.getName())
				.toArray(String[]::new);
	}
	
	@JsonIgnoreProperties
	public Province[] getProvinces() {
		return provinces;
	}
	
	public int getSquareMeters() {
		return squareMeters;
	}

	public String getTitle() {
		return title;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Property setBaths(int baths) throws AttributeRangeException {
		checkAttributeRange(baths, 1, 4, "baths");
		this.baths = baths;
		return this;
	}

	public Property setBeds(int beds) throws AttributeRangeException {
		checkAttributeRange(beds, 1, 5, "beds");
		this.beds = beds;
		return this;
	}

	public Property setDescription(String description) {
		this.description = description;
		return this;
	}

	public Property setId(int id) {
		this.id = id;
		return this;
	}

	public Property setPrice(int price) {
		this.price = price;
		return this;
	}

	public Property setSquareMeters(int squareMeters) throws AttributeRangeException {
		checkAttributeRange(squareMeters, 20, 240, "squareMeters");
		this.squareMeters = squareMeters;
		return this;
	}

	public Property setTitle(String title) {
		this.title = title;
		return this;
	}

	public Property setX(int x) throws AttributeRangeException {
		checkAttributeRange(x, 0, 1400, "Coordinate X");
		this.x = x;
		return this;
	}

	public Property setY(int y) throws AttributeRangeException {
		checkAttributeRange(y, 0, 1000, "Coordinate Y");
		this.y = y;
		return this;
	}
	
	public Property setProvinces(Province[] provinces) {
		this.provinces = provinces;
		return this;
	}

	private void checkAttributeRange(int x, int minimum, int maximum, String attribute) throws AttributeRangeException {
		if ( x < minimum || x > maximum ) {
			throw new AttributeRangeException(String.format("Attribute %s must be between %s and %s",attribute,minimum,maximum));
		}
	}

}

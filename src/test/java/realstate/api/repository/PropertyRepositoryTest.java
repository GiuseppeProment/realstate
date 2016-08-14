package realstate.api.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import realstate.api.domain.Boundarie;
import realstate.api.domain.Coordinate;
import realstate.api.domain.Property;
import realstate.api.integration.Storage;
import realstate.api.repository.PropertyRepository;
import realstate.api.repository.ProvinceRepository;
import realstate.api.util.Fixture;


public class PropertyRepositoryTest {

	private PropertyRepository repository;
	private ObjectMapper jsonMapper;

	@Test
	public void createProperty() throws Exception {
		Property propertyToCreate = 
		new Property()
			.setX(1)
			.setY(1)
			.setBaths(3)
			.setBeds(4)
			.setSquareMeters(50)
			.setPrice(100000)
			.setTitle("teste")
			.setDescription("foo");
		
		Property property = repository.create( jsonMapper.writeValueAsString( propertyToCreate ) );
		
		assertThat(property, notNullValue());
		assertThat(property.getId(),not(is(0)));
		assertThat(property.getBaths(), is(3));
		assertThat(property.getBeds(), is(4));
		assertThat(property.getDescription(), is("foo"));
		assertThat(property.getPrice(), is(100000));
		assertThat(property.getSquareMeters(), is(50));
		assertThat(property.getTitle(), is("teste"));
		assertThat(property.getX(), is(1));
		assertThat(property.getY(), is(1));
		assertThat(property.getProvinces(),notNullValue());
		assertThat(property.getProvinces().length,is(1));
		assertThat(property.getProvinces()[0].getName(),is(Fixture.PROVINCE_SCAVY_NAME));
	}

	@Test
	public void verifyPropertyStorage() throws Exception {
		Property propertyToCreate = 
		new Property()
			.setX(1)
			.setY(1)
			.setBaths(3)
			.setBeds(4)
			.setSquareMeters(70)
			.setPrice(150444)
			.setTitle("Sobrado")
			.setDescription("Ensolarado");
		
		Property propertyCreated = repository.create( jsonMapper.writeValueAsString( propertyToCreate ) );
		Property propertyFound = repository.find( propertyCreated.getId() );
		
		assertThat(propertyFound.getBaths(), is(3));
		assertThat(propertyFound.getBeds(), is(4));
		assertThat(propertyFound.getDescription(), is("Ensolarado"));
		assertThat(propertyFound.getPrice(), is(150444));
		assertThat(propertyFound.getSquareMeters(), is(70));
		assertThat(propertyFound.getTitle(), is("Sobrado"));
		assertThat(propertyFound.getX(), is(1));
		assertThat(propertyFound.getY(), is(1));
		assertThat(propertyFound.getProvinces(),notNullValue());
		assertThat(propertyFound.getProvinces().length,is(1));
		assertThat(propertyFound.getProvinces()[0].getName(),is(Fixture.PROVINCE_SCAVY_NAME));
	}
	
	@Test
	public void findPropertyById() throws Exception {
		Property property = repository.find(13);
		assertThat(property.getBaths(), is(1));
		assertThat(property.getBeds(), is(2));
		assertThat(property.getSquareMeters(), is(45));
		assertThat(property.getX(), is(79));
		assertThat(property.getY(), is(305));
		assertThat(property.getProvinces(), notNullValue());
		assertThat(property.getProvinces().length, is(1));
		assertThat(property.getProvinces()[0].getName(), is(Fixture.PROVINCE_SCAVY_NAME));
	}
	
	@Test
	public void findPropertiesOnArea() throws Exception {
		Coordinate upperLeft=new Coordinate(0, 1000);
		Coordinate bottomRight = new Coordinate(600, 500);
		Collection<Property> properties = repository.find( new Boundarie(upperLeft, bottomRight) );
		assertThat(properties, notNullValue());
		assertThat(properties.size(), is(841));
	}
	
	@Before
	public void initialize() {
		jsonMapper = new ObjectMapper();
		Storage storage = new Storage();
		ProvinceRepository provinceRepository = new ProvinceRepository(storage);
		repository = new PropertyRepository( provinceRepository, storage  );
	}
	
}

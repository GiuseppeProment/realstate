package realstate.api.facade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import realstate.api.domain.Property;
import realstate.api.domain.PropertyBag;
import realstate.api.facade.PropertyFacade;
import realstate.api.integration.Storage;
import realstate.api.repository.PropertyRepository;
import realstate.api.repository.ProvinceRepository;
import realstate.api.util.Fixture;

public class PropertyFacadeTest {
	
	private PropertyFacade facade;
	private PropertyRepository repository;
	private ObjectMapper jsonMapper;

	@Test
	public void create() throws Exception {
		Property pty = 
		new Property()
			.setX(1)
			.setY(1)
			.setBaths(4)
			.setBeds(1)
			.setSquareMeters(50)
			.setPrice(100000)
			.setTitle("teste")
			.setDescription("foo");
		
		String response = facade.create( jsonMapper.writeValueAsString(pty) );
		
		pty.setId(4001)
			.setProvinces(Fixture.ARRAY_PROVINCES_WITH_SCAVY);
		
		assertEquals( jsonMapper.writeValueAsString(pty) ,response);
	}
	
	@Test
	public void findById() throws Exception {
		String response = facade.find( 13 );
		Property expected = 
		new Property()
		    .setId(13)
			.setX(79)
			.setY(305)
			.setBaths(1)
			.setBeds(2)
			.setSquareMeters(45)
			.setProvinces(Fixture.ARRAY_PROVINCES_WITH_SCAVY);
		assertEquals( jsonMapper.writeValueAsString(expected), response );
	}

	@Test
	public void findOnBoundarieRestricted() throws Exception {
		String response = facade.findOnBoundarie(88,521,88,521);
		Property expected = 
		new Property()
		    .setId(1)
			.setX(88)
			.setY(521)
			.setBaths(4)
			.setBeds(5)
			.setSquareMeters(198)
			.setProvinces(Fixture.ARRAY_PROVINCES_WITH_GODE);
		assertEquals( jsonMapper.writeValueAsString( new PropertyBag( expected )), response );
	}
	
	@Test
	public void findOnBoundarieWide() throws Exception {
		String response = facade.findOnBoundarie(0,1000,27,950);
		Collection<Property> ptys = new ArrayList<>();
		ptys.add(
			new Property()
			    .setId(3633)
				.setX(26)
				.setY(966)
				.setBaths(4)
				.setBeds(5)
				.setSquareMeters(112)
				.setProvinces(Fixture.ARRAY_PROVINCES_WITH_GODE));
		ptys.add(
				new Property()
				    .setId(2101)
					.setX(5)
					.setY(987)
					.setBaths(4)
					.setBeds(5)
					.setSquareMeters(226)
					.setProvinces(Fixture.ARRAY_PROVINCES_WITH_GODE));
		ptys.add(
				new Property()
				    .setId(981)
					.setX(21)
					.setY(954)
					.setBaths(2)
					.setBeds(3)
					.setSquareMeters(77)
					.setProvinces(Fixture.ARRAY_PROVINCES_WITH_GODE));
		
		assertEquals( jsonMapper.writeValueAsString( new PropertyBag( ptys )), response );
	}
	
	@Before
	public void initialize() {
		Storage storage = new Storage();
		ProvinceRepository provinceRepository = new ProvinceRepository(storage);
		repository = new PropertyRepository( provinceRepository, storage  );
		facade = new PropertyFacade(repository);
		jsonMapper = new ObjectMapper();
	}

}

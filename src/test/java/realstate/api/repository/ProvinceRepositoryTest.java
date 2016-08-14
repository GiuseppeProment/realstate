package realstate.api.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import realstate.api.domain.Province;
import realstate.api.integration.Storage;
import realstate.api.repository.ProvinceRepository;
import realstate.api.util.Fixture;

public class ProvinceRepositoryTest {

	ProvinceRepository repository;

	@Test
	public void findProvincesOnGivenCoordenates() throws Exception {
		Province[] provinces = repository.findOnCoordinates(0, 0);
		assertThat(provinces,notNullValue());
		assertThat(provinces.length,is(1));
		assertThat(provinces[0].getName(),is(Fixture.PROVINCE_SCAVY_NAME));
	}

	@Test
	public void findOverlapProvincesOnGivenCoordenates() throws Exception {
		Province[] provinces = repository.findOnCoordinates(600, 1000);
		assertThat(provinces,notNullValue());
		assertThat(provinces.length,is(2));
		assertThat(provinces[0].getName(),is(Fixture.PROVINCE_GODE_NAME));
		assertThat(provinces[1].getName(),is(Fixture.PROVINCE_RUJA_NAME));
	}

	@Before
	public void initialize() {
		repository = new ProvinceRepository( new Storage() );
	}
}

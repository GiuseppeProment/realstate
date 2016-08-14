package realstate.api.facade;

import realstate.api.integration.Storage;
import realstate.api.repository.PropertyRepository;
import realstate.api.repository.ProvinceRepository;

public class PropertyFacadeFactory {
	
	private Storage storage;

	public PropertyFacadeFactory(Storage storage) {
		super();
		this.storage = storage;
	}

	public PropertyFacade get() {
		ProvinceRepository provinceRepository = new ProvinceRepository(storage);
		PropertyRepository repository = new PropertyRepository(provinceRepository, storage);
		return new PropertyFacade(repository );
	}
}

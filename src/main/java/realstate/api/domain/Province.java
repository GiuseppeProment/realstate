package realstate.api.domain;

public class Province {
	
	public Province() {
		super();
	}

	public Province( String name ) {
		super();
		this.name = name;
	}

	private String name;
	private Boundarie boundaries;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boundarie getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(Boundarie boundaries) {
		this.boundaries = boundaries;
	}

	public boolean contains(int x, int y) {
		return getBoundaries().contains(x, y);
	}

}

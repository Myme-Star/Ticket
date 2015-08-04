package ticket;

import java.net.URL;
import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PlaceData {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String idx;

	@Persistent
	private String place;
	

	public PlaceData(String idx, String place) {
		super();
		this.idx = idx;
		this.place = place;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdx() {
		return idx;
	}
	
	public String getPlace() {
		return place;
	}


}
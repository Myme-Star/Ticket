package ticket;

import java.net.URL;
import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TicketData {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String username;

	@Persistent
	private String password;

	/*
	@Persistent
	private String comment;

	@Persistent
	private Date datetime;
	*/

	public TicketData(String username, String passowrd) {
		super();
		this.username = username;
		this.password = passowrd;
	//	this.comment = comment;
	//	this.datetime = datetime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String passoword) {
		this.username = passoword;
	}

	/*
	public String getUrl() {
		return password;
	}

	public void setUrl(String url) {
		this.password = url;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	*/
}
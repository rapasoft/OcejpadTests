package sk.erni.rap.jpa.model;

import javax.persistence.*;

/**
 * @author rap
 */
@Entity
@DiscriminatorValue("G")
@AttributeOverride(name = "creationDate", column = @Column(name = "ASSIGNMENT_DATE"))
public class GuestUser extends User {

	@OneToOne
	@JoinColumn(name = "HOST_ID")
	private User host;

	public GuestUser() {
		super();
	}

	public GuestUser(String name, String surname, String dateOfBirth) {
		super(name, surname, dateOfBirth);
	}

	public User getHost() {
		return host;
	}
}

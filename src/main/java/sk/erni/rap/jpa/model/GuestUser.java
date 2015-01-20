package sk.erni.rap.jpa.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author rap
 */
@Entity
@DiscriminatorValue("G")
public class GuestUser extends User {

	public GuestUser() {
		super();
	}

	public GuestUser(String name, String surname, String dateOfBirth) {
		super(name, surname, dateOfBirth);
	}
}

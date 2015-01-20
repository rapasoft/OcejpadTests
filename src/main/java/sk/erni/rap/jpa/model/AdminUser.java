package sk.erni.rap.jpa.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author rap
 */
@Entity
@DiscriminatorValue("A")
public class AdminUser extends User {

	public AdminUser() {
		super();
	}

	public AdminUser(String name, String surname, String dateOfBirth) {
		super(name, surname, dateOfBirth);
	}
}

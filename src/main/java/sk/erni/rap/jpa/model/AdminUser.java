package sk.erni.rap.jpa.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author rap
 */
@Entity
@DiscriminatorValue("A")
@AttributeOverride(name = "creationDate", column = @Column(name = "PROMOTION_DATE"))
public class AdminUser extends User {

	@OneToMany
	@JoinColumn(name = "ADMIN_ID")
	public List<User> administeredUsers;

	public AdminUser() {
		super();
	}

	public AdminUser(String name, String surname, String dateOfBirth) {
		super(name, surname, dateOfBirth);
	}

	public List<User> getAdministeredUsers() {
		return administeredUsers;
	}
}

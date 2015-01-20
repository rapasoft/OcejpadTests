package sk.erni.rap.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * @author rap
 */
@Entity
public class Role {

	@Id
	@GeneratedValue
	public Integer id;

	private String name;
	private String description;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public Role() {
	}

	public Role(String description, String name) {
		this.description = description;
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}
}

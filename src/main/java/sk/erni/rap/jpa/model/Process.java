package sk.erni.rap.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * @author rap
 */
@Entity
public class Process {

	@Id
	@GeneratedValue
	private Integer id;

	@Version
	private Integer version;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}
}

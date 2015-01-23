package sk.erni.rap.jpa.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

/**
 * @author rap
 */
@Embeddable
@Access(AccessType.FIELD)
public class ProcessAttribute {

	private String name;

	private String value;

	private Integer priority;

	public ProcessAttribute() {
	}

	public ProcessAttribute(String name, String value, Integer priority) {
		this.name = name;
		this.value = value;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public Integer getPriority() {
		return priority;
	}

	@Override
	public String toString() {
		return "ProcessAttribute{" +
				"name='" + name + '\'' +
				", value='" + value + '\'' +
				", priority=" + priority +
				'}';
	}
}

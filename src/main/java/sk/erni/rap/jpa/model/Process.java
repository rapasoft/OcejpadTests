package sk.erni.rap.jpa.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

	@ElementCollection
	@CollectionTable(name = "PROCESS_ATTRIBUTES", joinColumns = @JoinColumn(name = "PROCESS_ID"))
	private List<ProcessAttribute> processAttributes = new ArrayList<>();

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

	public List<ProcessAttribute> getProcessAttributes() {
		return processAttributes;
	}

	public void addProcessAttribute(ProcessAttribute processAttribute) {
		processAttributes.add(processAttribute);
	}

	@PrePersist
	public void logPrePersist() {
		System.out.println("PerPresist " + getProcessAttributes());
	}

	@PreUpdate
	public void logPreUpdate() {
		System.out.println("PreUpdate " + getProcessAttributes());
	}

	@PostPersist
	public void logPostPersist() {
		System.out.println("PostPersist" + getProcessAttributes());
	}

	@PostUpdate
	public void logPostUpdate() {
		System.out.println("PostUpdate" + getProcessAttributes());
	}

}

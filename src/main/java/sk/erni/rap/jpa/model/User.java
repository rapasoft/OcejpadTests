package sk.erni.rap.jpa.model;

import javax.persistence.*;
import java.util.*;

/**
 * @author rap
 */
@NamedQuery(
		name = "User.findGuestsWithReaderRole",
		query = "select g from GuestUser g join g.roles r where r.name = 'writer'"
)
@SqlResultSetMapping(
		name = "User.usersWithRoles",
		columns = {
				@ColumnResult(name = "username"),
				@ColumnResult(name = "surname")
		},
		entities = {
				@EntityResult(entityClass = Role.class, fields = {
						@FieldResult(name = "name", column = "rolename"),
						@FieldResult(name = "description", column = "description")
				})
		}
)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("U")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String name;

	private String surname;

	private String dateOfBirth;

	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@ElementCollection
	@CollectionTable(
			name = "NICKNAME",
			joinColumns = @JoinColumn(name = "USER_ID")
	)
	private List<String> nickNames = new ArrayList<>();

	@ElementCollection
	@MapKeyColumn(name = "PHONE_TYPE")
	private Map<String, String> phoneNumbers = new TreeMap<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinTable(
			name = "USER_ROLE",
			joinColumns = @JoinColumn(name = "USER_ID"),
			inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
	)
	private List<Role> roles = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "USER_ROLE")
	@Column(name = "VALID_TO", nullable = true)
	@MapKeyJoinColumn(name = "ROLE_ID")
	private Map<Role, Date> roleValidToDates = new HashMap<>();

	public User(String name, String surname, String dateOfBirth) {
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	@MapKey(name = "value")
	private Map<String, Process> processes = new HashMap<>();

	public User() {
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getSurname() {
		return surname;
	}

	public String getName() {
		return name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<String> getNickNames() {
		return nickNames;
	}

	public Map<String, String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public Map<Role, Date> getRoleValidToDates() {
		return roleValidToDates;
	}

	public Map<String, Process> getProcesses() {
		return processes;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public void addRole(Role role, Date validTo) {
		roles.add(role);
		roleValidToDates.put(role, validTo);
	}

	public void addNickName(String nickname) {
		nickNames.add(nickname);
	}

	public void addPhoneNumber(String type, String phoneNumber) {
		phoneNumbers.put(type, phoneNumber);
	}

}

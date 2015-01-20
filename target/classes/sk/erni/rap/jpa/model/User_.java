package sk.erni.rap.jpa.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile MapAttribute<User, Role, Date> roleValidToDates;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile ListAttribute<User, Role> roles;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> dateOfBirth;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile ListAttribute<User, String> nickNames;
	public static volatile MapAttribute<User, String, String> phoneNumbers;

}


package sk.erni.rap.jpa.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import sk.erni.rap.jpa.dao.UserDao;
import sk.erni.rap.jpa.model.User;
import sk.erni.rap.jpa.util.ContextUtils;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * @author rap
 */
@RunWith(Parameterized.class)
public class AddAndRetrieveUserTest {

	private UserDao userDao;

	@Parameterized.Parameters
	public static Collection<Object[]> parameters() {
		return Arrays.asList(new Object[][]{
				{"John", "Doe", "1970-01-01"},
				{"Jack", "Doe", "1980-01-01"},
				{"Jeff", "Doe", "1990-01-01"}
		});
	}

	public AddAndRetrieveUserTest(String firstName, String surname, String dateOfBirth) {
		this.firstName = firstName;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	private String firstName;

	private String surname;

	private String dateOfBirth;

	@Before
	public void init() {
		Context context = ContextUtils.createContext();
		userDao = ContextUtils.lookupUserDao(context);
	}

	@Test
	public void addAndRetrieveUser() {
		User user = new User(firstName, surname, dateOfBirth);

		userDao.save(user);

		List<User> users = userDao.findByFirstName(firstName);

		Assert.assertFalse(users.isEmpty());
		Assert.assertTrue(users.stream().anyMatch(
				p -> p.getName().equals(firstName) && p.getSurname().equals(surname) && p.getDateOfBirth().equals(dateOfBirth)));
	}

}

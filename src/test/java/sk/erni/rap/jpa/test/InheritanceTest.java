package sk.erni.rap.jpa.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.erni.rap.jpa.dao.UserDao;
import sk.erni.rap.jpa.model.AdminUser;
import sk.erni.rap.jpa.model.GuestUser;
import sk.erni.rap.jpa.model.User;
import sk.erni.rap.jpa.util.ContextUtils;

import javax.naming.Context;
import javax.naming.NamingException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rap
 */
public class InheritanceTest {

	private UserDao userDao;

	@Before
	public void init() throws NamingException {
		Context context = ContextUtils.createContext();
		userDao = ContextUtils.lookupUserDao(context);
	}


	@Test
	public void addAndRetrieveAdmin() {
		User user = new AdminUser("John", "Admin", "1970-01-01");
		userDao.save(user);

		List<User> results = userDao.findAll().stream().filter(p -> p.getSurname().equals("Admin")).collect(Collectors.toList());

		Assert.assertEquals(1, results.size());
		Assert.assertEquals(user.getSurname(), results.get(0).getSurname());
		Assert.assertTrue(results.get(0).getClass().getName().contains("AdminUser"));
	}

	@Test
	public void addAndRetrieveGuest() {
		User user = new GuestUser("John", "Guest", "1970-01-01");
		userDao.save(user);

		List<User> results = userDao.findAll().stream().filter(p -> p.getSurname().equals("Guest")).collect(Collectors.toList());

		Assert.assertEquals(1, results.size());
		Assert.assertEquals(user.getSurname(), results.get(0).getSurname());
		Assert.assertTrue(results.get(0).getClass().getName().contains("GuestUser"));
	}

}

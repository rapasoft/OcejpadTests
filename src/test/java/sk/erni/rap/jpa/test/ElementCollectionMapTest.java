package sk.erni.rap.jpa.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.erni.rap.jpa.dao.UserDao;
import sk.erni.rap.jpa.model.User;
import sk.erni.rap.jpa.util.ContextUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author rap
 */
public class ElementCollectionMapTest {

	private UserDao userDao;

	private UserTransaction userTransaction;

	@Before
	public void init() throws NamingException {
		Context context = ContextUtils.createContext();
		userDao = ContextUtils.lookupUserDao(context);
		userTransaction = ContextUtils.lookupUserTransaction();
	}

	@Test
	public void addPhoneNumbers() throws SystemException, NotSupportedException {
		userTransaction.begin();

		User user = new User("User","Resu","2001-09-11");
		user.addPhoneNumber("Home","1-500-HOME");
		user.addPhoneNumber("Work","1-500-WORK");

		userDao.save(user);

		List<User> usersWithPhoneNumbers = userDao.findAll().stream().filter(p -> !p.getPhoneNumbers().isEmpty()).collect(Collectors.toList());

		Assert.assertFalse(usersWithPhoneNumbers.isEmpty());
		Assert.assertEquals(2, usersWithPhoneNumbers.get(0).getPhoneNumbers().size());

		Map<String, String> phoneNumbers = usersWithPhoneNumbers.get(0).getPhoneNumbers();

		Assert.assertEquals("1-500-HOME", phoneNumbers.get("Home"));
		Assert.assertEquals("1-500-WORK", phoneNumbers.get("Work"));

		userTransaction.rollback();
	}

}

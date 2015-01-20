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
import java.util.stream.Collectors;

/**
 * @author rap
 */
public class ElementCollectionTest {

	private UserDao userDao;

	private UserTransaction userTransaction;

	@Before
	public void init() throws NamingException {
		Context context = ContextUtils.createContext();
		userDao = ContextUtils.lookupUserDao(context);
		userTransaction = ContextUtils.lookupUserTransaction();
	}

	@Test
	public void addNickNames() throws SystemException, NotSupportedException {
		userTransaction.begin();

		User user = new User("User","Resu","2001-09-11");
		user.addNickName("Shorty");
		user.addNickName("J-Shot");

		userDao.save(user);

		List<User> usersWithNicknames = userDao.findAll().stream().filter(p -> !p.getNickNames().isEmpty()).collect(Collectors.toList());

		Assert.assertFalse(usersWithNicknames.isEmpty());
		Assert.assertEquals(2, usersWithNicknames.get(0).getNickNames().size());

		userTransaction.rollback();
	}

}

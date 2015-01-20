package sk.erni.rap.jpa.test;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.erni.rap.jpa.dao.UserDao;
import sk.erni.rap.jpa.model.GuestUser;
import sk.erni.rap.jpa.model.Role;
import sk.erni.rap.jpa.util.ContextUtils;

import javax.naming.Context;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * @author rap
 */
public class NamedQueryTest {

	private UserDao userDao;
	private UserTransaction userTransaction;

	@Before
	public void init() {
		Context context = ContextUtils.createContext();
		userDao = ContextUtils.lookupUserDao(context);
		userTransaction = ContextUtils.lookupUserTransaction();
	}

	@Test
	public void testNamedQuery() throws SystemException, NotSupportedException {
		userTransaction.begin();

		populateUsers();

		List<GuestUser> userList = userDao.findGuestUsersWithWriterRole();

		Assert.assertEquals(2, userList.size());

		Assert.assertTrue(userList.stream().allMatch(p -> p.getRoles().get(0).getName().equals("writer")));

		userTransaction.rollback();
	}

	private void populateUsers() {
		GuestUser guestUser1 = new GuestUser("G1","U1","1981-01-01");
		GuestUser guestUser2 = new GuestUser("G2","U2","1982-01-01");
		GuestUser guestUser3 = new GuestUser("G3","U3","1983-01-01");

		Role writer = new Role("Writer role","writer");

		guestUser1.addRole(writer);
		guestUser3.addRole(writer);

		userDao.save(guestUser1);
		userDao.save(guestUser2);
		userDao.save(guestUser3);
	}

}

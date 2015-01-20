package sk.erni.rap.jpa.test;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.erni.rap.jpa.dao.UserDao;
import sk.erni.rap.jpa.model.*;
import sk.erni.rap.jpa.util.ContextUtils;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.List;

/**
 * @author rap
 */
public class CriteriaQueryTest {

	private UserDao userDao;

	private UserTransaction userTransaction;

	@Before
	public void init() throws NamingException {
		Context context = EJBContainer.createEJBContainer().getContext();
		userDao = ContextUtils.lookupUserDao(context);
		userTransaction = ContextUtils.lookupUserTransaction();
	}

	@Test
	public void testCriteriaQuery() throws SystemException, NotSupportedException {
		userTransaction.begin();

		populateUsersWithRoles();
		List<User> users = userDao.findUserByNameAndRole("Jack", "editor");

		Assert.assertEquals(1, users.size());

		AdminUser adminUser = (AdminUser) users.get(0);

		Assert.assertEquals("editor", adminUser.getRoles().get(0).getName());
		Assert.assertEquals("Jack", adminUser.getName());

		userTransaction.rollback();
	}

	private void populateUsersWithRoles() {
		User admin = new AdminUser("Jack", "Black", "1923-12-12");
		User guest = new GuestUser("Johny", "Deep", "1963-05-23");

		admin.addRole(new Role("Editor role", "editor"));
		guest.addRole(new Role("Viewer role", "viewer"));

		userDao.save(admin);
		userDao.save(guest);
	}

}

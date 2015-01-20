package sk.erni.rap.jpa.test;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import sk.erni.rap.jpa.dao.UserDao;
import sk.erni.rap.jpa.model.AdminUser;
import sk.erni.rap.jpa.model.Role;
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
public class ManyToManyRelationTest {

	private UserDao userDao;

	private UserTransaction userTransaction;

	@Before
	public void init() throws NamingException {
		Context context = ContextUtils.createContext();
		userDao = ContextUtils.lookupUserDao(context);
		userTransaction = ContextUtils.lookupUserTransaction();
	}

	@Test
	public void addUserRole() throws SystemException, NotSupportedException {
		userTransaction.begin();

		User user = new AdminUser("User", "Admin", "1990-01-01");
		Role role = new Role("Editor role for changing something", "Editor");

		user.addRole(role);

		userDao.save(user);

		List<User> users = userDao.findAll().stream().filter(p -> !p.getRoles().isEmpty()).collect(Collectors.toList());

		Assert.assertFalse(users.isEmpty());
		Assert.assertEquals(1, users.get(0).getRoles().size());

		Role savedRole = users.get(0).getRoles().get(0);

		Assert.assertEquals("Editor", savedRole.getName());

		userTransaction.rollback();
	}

}

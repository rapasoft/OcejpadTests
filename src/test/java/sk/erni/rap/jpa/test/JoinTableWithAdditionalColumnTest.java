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
import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rap
 */
public class JoinTableWithAdditionalColumnTest {

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

		Date validTo = new Date(LocalDate.of(2014, 12, 31).toEpochDay());

		user.addRole(role, validTo);

		userDao.save(user);

		List<User> users = userDao.findAll().stream().filter(
				p -> !p.getRoles().isEmpty() && !p.getRoleValidToDates().isEmpty()
		).collect(Collectors.toList());

		Assert.assertFalse(users.isEmpty());

		User savedUser = users.get(0);
		Assert.assertEquals(1, savedUser.getRoles().size());

		Role savedRole = savedUser.getRoles().get(0);

		Assert.assertEquals("Editor", savedRole.getName());
		Assert.assertEquals(validTo, savedUser.getRoleValidToDates().get(savedRole));

		userTransaction.rollback();
	}

}

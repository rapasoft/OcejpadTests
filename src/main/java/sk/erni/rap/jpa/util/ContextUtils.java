package sk.erni.rap.jpa.util;

import sk.erni.rap.jpa.dao.UserDao;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

/**
 * @author rap
 */
public class ContextUtils {

	public static Context createContext() {
		return EJBContainer.createEJBContainer().getContext();
	}

	public static UserDao lookupUserDao(Context context) {
		try {
			return (UserDao) context.lookup("java:global/JpaTest/UserDao");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}

	public static UserTransaction lookupUserTransaction() {
		try {
			return (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}
}

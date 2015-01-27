package sk.erni.rap.jpa.util;

import sk.erni.rap.jpa.dao.ProcessDao;
import sk.erni.rap.jpa.dao.UserDao;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author rap
 */
public class ContextUtils {

	private static String getModuleName() {
		Properties properties = System.getProperties();
		String[] path = properties.getProperty("openejb.home").split("\\\\|/");
		return path[path.length - 1];
	}

	public static Context createContext() {
		return EJBContainer.createEJBContainer().getContext();
	}

	@SuppressWarnings("unchecked")
	public static <T> T lookup(Context context, Class<T> clazz) {
		try {
			return (T) context.lookup("java:global/" + getModuleName() + "/" + clazz.getSimpleName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException();
	}

	public static UserDao lookupUserDao(Context context) {
		return lookup(context, UserDao.class);
	}

	public static ProcessDao lookupProcessDao(Context context) {
		return lookup(context, ProcessDao.class);
	}

	public static UserTransaction lookupUserTransaction() {
		try {
			return (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
		} catch (NamingException e) {
			throw new IllegalStateException(e);
		}
	}
}

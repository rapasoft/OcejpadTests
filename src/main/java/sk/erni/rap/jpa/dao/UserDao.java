package sk.erni.rap.jpa.dao;

import sk.erni.rap.jpa.model.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author rap
 */
@Stateless
public class UserDao {

	@PersistenceContext(name = "openjpa")
	private EntityManager entityManager;

	public List<User> findByFirstName(String firstName) {
		return entityManager.createQuery("select u from User u where u.name = :firstName", User.class)
				.setParameter("firstName", firstName)
				.getResultList();
	}

	public List<User> findAll() {
		return entityManager.createQuery("select u from User u", User.class)
				.getResultList();
	}

	public void save(User user) {
		entityManager.persist(user);
	}

	public List<User> findUserByNameAndRole(String firstName, String role) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

		Root<User> userRoot = criteriaQuery.from(User.class);
		criteriaQuery.where(criteriaBuilder.equal(userRoot.get(User_.name), firstName));
		ListJoin<User, Role> userRoleListJoin = userRoot.join(User_.roles);
		criteriaQuery.where(criteriaBuilder.equal(userRoleListJoin.get(Role_.name), role));

		criteriaQuery.select(userRoot);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	public List<GuestUser> findGuestUsersWithWriterRole() {
		return entityManager.createNamedQuery("User.findGuestsWithReaderRole", GuestUser.class).getResultList();
	}

}

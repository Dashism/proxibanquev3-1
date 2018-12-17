package fr.formation.proxibanquev3.persistence;

import javax.persistence.EntityManager;

/**
 * Implémentation du Design Pattern DAO vers l'API JPA.
 *
 * @param <T> Désigne un type d'entité.
 */
public abstract class AbstractDao<T> implements Dao<T> {

	/**
	 * Mémorisation de l'instance d'EntityManager pour chaque DAO.
	 */
	protected EntityManager em;

	/**
	 * Constructeur par défaut qui récupère une instance d'EntityManager par la
	 * classe MysqlConnection.
	 */
	public AbstractDao() {
		this.em = MySqlConnection.getInstance().getEntityManager();
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public T create(T entity) {
		this.em.getTransaction().begin();
		this.em.persist(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T update(T entity) {
		this.em.getTransaction().begin();
		this.em.merge(entity);
		this.em.getTransaction().commit();
		return entity;
	}

	/**
	 * Lecture d'une entité par son identifiant. Il est nécessaire de connaître
	 * la classe Java qui représente l'entité pour pouvoir lire les bonnes
	 * informations.
	 */
	@SuppressWarnings("unchecked")
	protected T read(Integer id, T entity) {
		T result = null;
		result = (T) this.em.find(entity.getClass(), id);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(Integer id) {
		this.em.getTransaction().begin();
		T entity = this.read(id);
		this.em.remove(entity);
		this.em.getTransaction().commit();
		return true;
	}
}

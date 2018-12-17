package fr.formation.proxibanquev3.persistence;

import java.util.List;

/**
 * Definition d'une interface comportant les 4 m�thodes elementaires de
 * manipulation de donnees CRUD + readALL.
 * 
 * @param <T> classe qui represente le type de donnees manipule par le DAO.
 */
public interface Dao<T> {
	
	/**
	 * Crée une nouvelle entité en base de données.
	 * 
	 * @param entity l'entité à créer.
	 * @return T l'entité avec son identifiant rempli.
	 */
	public T create(T entity);

	/**
	 * Lit les informations d'une entite de BDD
	 * 
	 * @param id l'identifiant d'une entite en base de donnees
	 * @return T l'entit� construite � partir des informations recuperees
	 * 
	 */
	public T read(Integer id);

	/**
	 * Recupere toutes les entit�s (lignes) d'une table
	 * 
	 * @return List<T> la liste de toutes les entites lues.
	 */
	public List<T> readAll();

	/**
	 * met � jour l'entite donne dans la BDD
	 * 
	 * @param entity l'entite � mettre a jour avec les nouvelles informations
	 * @return l'entite mise � jour
	 */
	public T update(T entity);
	
	/**
	 * Supprime définitivement une entité de la base de données.
	 * 
	 * @param id l'identifiant unique de l'entité.
	 * @return boolean vrai si la suppression est un succès, sinon faux.
	 */
	public boolean delete(Integer id);

}

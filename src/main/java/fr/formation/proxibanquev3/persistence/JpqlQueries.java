package fr.formation.proxibanquev3.persistence;


/**
 * Classe contenant sous forme de constantes l'ensemble des requetes necessaires aux manipulations de la base de donnees.
 * 
 * @author Adminl
 *
 */
public class JpqlQueries {
	
	public static final String SELECT_ALL_ACCOUNT = "SELECT a FROM Account a";
	public static final String SELECT_ALL_CARD = "SELECT c FROM Card c";
}

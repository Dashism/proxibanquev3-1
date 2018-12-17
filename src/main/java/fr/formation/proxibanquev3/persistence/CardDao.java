package fr.formation.proxibanquev3.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.formation.proxibanquev3.metier.entity.Card;

public class CardDao extends AbstractDao<Card> {
	
	private static final CardDao INSTANCE = new CardDao();

	public static CardDao getInstance() {
		return CardDao.INSTANCE;
	}

	@Override
	public Card read(Integer id) {
		return this.read(id, new Card());
	}

	@Override
	public List<Card> readAll() {
		List<Card> accounts = new ArrayList<>();
		TypedQuery<Card> query = this.em
				.createQuery(JpqlQueries.SELECT_ALL_CARD, Card.class);
		accounts.addAll(query.getResultList());
		return accounts;
	}

}

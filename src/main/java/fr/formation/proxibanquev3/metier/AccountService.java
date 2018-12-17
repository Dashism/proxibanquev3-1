package fr.formation.proxibanquev3.metier;

import java.time.LocalDate;
import java.util.List;

import fr.formation.proxibanquev3.metier.entity.Account;
import fr.formation.proxibanquev3.metier.entity.Card;
import fr.formation.proxibanquev3.persistence.AccountDao;
import fr.formation.proxibanquev3.persistence.CardDao;

public class AccountService {

	private static final AccountService INSTANCE = new AccountService();

	public static AccountService getInstance() {
		return AccountService.INSTANCE;
	}

	private AccountDao accountDao;
	private CardDao cardDao;

	public AccountService() {
		this.accountDao = AccountDao.getInstance();
		this.cardDao = CardDao.getInstance();
	}

	public List<Account> getAll() {
		return this.accountDao.readAll();
	}

	public boolean linkNewCard(Integer accountId, String type) {
		boolean resultOk = true;
		Account account = this.accountDao.read(accountId);
		// Si le compte avait déjà une carte et qu'elle a expirée.
		if (account.getCard() != null) {
			// On vérifie que la date d'expiration est bien dépassée.
			if (account.getCard().getEndDate().isBefore(LocalDate.now())) {
				// Retirer le lien entre l'ancienne carte et le compte.
				account.setCard(null);
				// Mettre à jour le compte pour que le lien n'existe plus en BDD.
				this.accountDao.update(account);				
			} else {
				// Sinon on indique qu'il ne faut pas créer de carte.
				resultOk = false;
			}
		}
		// Si il est possible d'ajouter une carte.
		if (resultOk) {
			// On prepare la nouvelle carte.
			Card newCard = new Card();
			newCard.setEndDate(LocalDate.now().plusMonths(3));
			newCard.setType(type);
			// On créé la carte en BDD pour avoir un id généré.
			newCard = this.cardDao.create(newCard);
			// On lie la nouvelle carte au compte.
			account.setCard(newCard);
			// On met à jour le compte avec le lien vers la nouvelle carte.
			this.accountDao.update(account);
		}
		return resultOk;
	}
}

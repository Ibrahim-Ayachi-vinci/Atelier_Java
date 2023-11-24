package domaine;

/**
 * L'interface QueryFactory définit une méthode permettant de créer des instances de l'interface Query.
 * Les classes qui implémentent cette interface devraient fournir une implémentation de la méthode
 * getQuery() qui renvoie une instance de l'interface Query avec une configuration spécifique.
 */
public interface QueryFactory {

	/**
	 * Crée et retourne une nouvelle instance de l'interface Query avec une configuration spécifique.
	 *
	 * @return Une nouvelle instance de l'interface Query configurée selon les besoins de l'application.
	 */
	Query getQuery();
}

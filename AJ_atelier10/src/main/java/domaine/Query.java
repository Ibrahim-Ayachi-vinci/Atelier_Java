package domaine;

/**
 * L'interface Query définit les méthodes nécessaires pour représenter une requête.
 * Une implémentation de cette interface devrait encapsuler les détails d'une requête HTTP,
 * tels que l'URL, la méthode de requête (GET ou POST), etc.
 */
public interface Query {

	/**
	 * Obtient l'URL associée à la requête.
	 *
	 * @return L'URL de la requête.
	 */
	String getUrl();

	/**
	 * Définit l'URL associée à la requête.
	 *
	 * @param url Le nouvel URL de la requête.
	 */
	void setUrl(String url);

	/**
	 * Obtient la méthode de requête utilisée (GET ou POST).
	 *
	 * @return La méthode de requête utilisée.
	 */
	QueryMethod getMethod();

	/**
	 * Définit la méthode de requête utilisée (GET ou POST).
	 *
	 * @param method La nouvelle méthode de requête.
	 */
	void setMethod(QueryMethod method);

	/**
	 * L'énumération QueryMethod représente les méthodes de requête possibles,
	 * à savoir GET (requête de récupération) et POST (requête d'envoi de données).
	 */
	public enum QueryMethod {
		GET, POST;
	}

}

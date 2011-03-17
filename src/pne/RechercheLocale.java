package pne;

/**
 * Interface générique permettant de manipuler des algorithmes
 * de recherche locale permettant de trouver un extremum local
 * à partir d'une solution initiale.
 * 
 * @param <Solution> le type des solution manipulées par la
 * 					 recherche locale.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public interface RechercheLocale<Solution>
{
	/**
	 * Retourne la meilleure solution locale disponible
	 * à partir de la solution initiale fournie.
	 * 
	 * @param s la solution initiale.
	 * @return la meilleure solution locale trouvée.
	 */
	public Solution trouverMeilleureSolutionLocale(Solution s);
}

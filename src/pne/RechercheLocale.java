package pne;

/**
 * Interface g�n�rique permettant de manipuler des algorithmes
 * de recherche locale permettant de trouver un extremum local
 * � partir d'une solution initiale.
 * 
 * @param <Solution> le type des solution manipul�es par la
 * 					 recherche locale.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public interface RechercheLocale<Solution>
{
	/**
	 * Retourne la meilleure solution locale disponible
	 * � partir de la solution initiale fournie.
	 * 
	 * @param s la solution initiale.
	 * @return la meilleure solution locale trouv�e.
	 */
	public Solution trouverMeilleureSolutionLocale(Solution s);
}

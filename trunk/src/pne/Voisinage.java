package pne;

/**
 * Interface générique permettant de manipuler des voisinages
 * permettant de générer une solution à partir d'une autre.
 * 
 * @param <Solution> le type des solution manipulées par le voisinage.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public interface Voisinage<Solution>
{
	/**
	 * Retourne une solution appartenant au voisinage de la
	 * solution fournie.
	 * 
	 * @param s la solution de base.
	 * @return une solution située dans le voisinage de la
	 * 		   solution de base.
	 */
	public Solution genererSolution(Solution s);
}

package pne;

/**
 * Interface générique permettant de manipuler des voisinages
 * permettant de générer une solution à partir d'une autre.
 * 
 * @param &lt;Solution&gt; le type des solution manipulées par le voisinage.
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
	
	/**
	 * Retourne la meilleure solution appartenant au
	 * voisinage de la solution fournie.
	 * La solution retournée est la meilleure par rapport
	 * à la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param f la fonction objectif.
	 * @return la meilleure solution située dans le
	 * 		   voisinage de la solution de base.
	 */
	public Solution rechercherMeilleureSolution(Solution s, FonctionObjectif<Solution> f);
}

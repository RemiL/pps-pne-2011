package pne;

/**
 * Interface g�n�rique permettant de manipuler des voisinages
 * permettant de g�n�rer une solution � partir d'une autre.
 * 
 * @param &lt;Solution&gt; le type des solution manipul�es par le voisinage.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public interface Voisinage<Solution>
{
	/**
	 * Retourne une solution appartenant au voisinage de la
	 * solution fournie.
	 * 
	 * @param s la solution de base.
	 * @return une solution situ�e dans le voisinage de la
	 * 		   solution de base.
	 */
	public Solution genererSolution(Solution s);
	
	/**
	 * Retourne la meilleure solution appartenant au
	 * voisinage de la solution fournie.
	 * La solution retourn�e est la meilleure par rapport
	 * � la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param f la fonction objectif.
	 * @return la meilleure solution situ�e dans le
	 * 		   voisinage de la solution de base.
	 */
	public Solution rechercherMeilleureSolution(Solution s, FonctionObjectif<Solution> f);
}

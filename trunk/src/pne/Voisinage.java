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
	 * Retourne la meilleure solution trouvée après une
	 * recherche locale. La recherche est effectuée en
	 * faisant une descente avec au maximum le nombre
	 * d'essais fournis. Elle utilise le voisinage de 
	 * taille 1.
	 * La solution retournée est la meilleure par rapport
	 * à la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param essais la nombre d'essai à effectuer.
	 * @param f la fonction objectif.
	 * @return la meilleure solution locale trouvée
	 * 		   après la descente.
	 */
	public Solution rechercherSolutionLocale(Solution s, int essais, FonctionObjectif<Solution> f);
}

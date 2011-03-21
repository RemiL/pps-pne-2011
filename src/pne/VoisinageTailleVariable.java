package pne;

/**
 * Interface générique permettant de manipuler des voisinages
 * à taille variable permettant de générer une solution à partir
 * d'une autre.
 * 
 * @param &lt;Solution&gt; le type des solution manipulées par le voisinage.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public interface VoisinageTailleVariable<Solution>
{
	/**
	 * Retourne une solution aléatoire appartenant au 
	 * voisinage de taille indiquée de la solution fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage à considérer.
	 * @return une solution située dans le voisinage de taille
	 * 		   k de la solution de base.
	 */
	public Solution genererSolution(Solution s, int k);
	
	/**
	 * Retourne la meilleure solution appartenant au voisinage
	 * de taille indiquée de la solution fournie.
	 * La solution retournée est la meilleure par rapport
	 * à la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage à considérer.
	 * @param f la fonction objectif.
	 * @return la meilleure solution située dans le voisinage
	 * 		   de taille k de la solution de base.
	 */
	public Solution rechercherMeilleureSolution(Solution s, int k, FonctionObjectif<Solution> f);
}

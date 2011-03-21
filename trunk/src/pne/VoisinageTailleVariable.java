package pne;

/**
 * Interface g�n�rique permettant de manipuler des voisinages
 * � taille variable permettant de g�n�rer une solution � partir
 * d'une autre.
 * 
 * @param &lt;Solution&gt; le type des solution manipul�es par le voisinage.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public interface VoisinageTailleVariable<Solution>
{
	/**
	 * Retourne une solution al�atoire appartenant au 
	 * voisinage de taille indiqu�e de la solution fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage � consid�rer.
	 * @return une solution situ�e dans le voisinage de taille
	 * 		   k de la solution de base.
	 */
	public Solution genererSolution(Solution s, int k);
	
	/**
	 * Retourne la meilleure solution appartenant au voisinage
	 * de taille indiqu�e de la solution fournie.
	 * La solution retourn�e est la meilleure par rapport
	 * � la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param k la taille du voisinage � consid�rer.
	 * @param f la fonction objectif.
	 * @return la meilleure solution situ�e dans le voisinage
	 * 		   de taille k de la solution de base.
	 */
	public Solution rechercherMeilleureSolution(Solution s, int k, FonctionObjectif<Solution> f);
}

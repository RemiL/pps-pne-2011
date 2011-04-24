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
	 * Retourne la meilleure solution trouv�e apr�s une
	 * recherche locale. La recherche est effectu�e en
	 * faisant une descente avec au maximum le nombre
	 * d'essais fournis. Elle utilise le voisinage de 
	 * taille 1.
	 * La solution retourn�e est la meilleure par rapport
	 * � la fonction objectif fournie.
	 * 
	 * @param s la solution de base.
	 * @param essais la nombre d'essai � effectuer.
	 * @param f la fonction objectif.
	 * @return la meilleure solution locale trouv�e
	 * 		   apr�s la descente.
	 */
	public Solution rechercherSolutionLocale(Solution s, int essais, FonctionObjectif<Solution> f);
}

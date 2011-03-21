package pne;

/**
 * Classe abstraite impl�mentant l'heuristique de recherche �
 * voisinage variable (Variable Neighbourhood Search ou VNS)
 * de mani�re g�n�rique pour des types de donn�es et de solution
 * quelconque. Cette classe doit ensuite �tre sp�cialis�e par
 * h�ritage aux diff�rents probl�mes auxquels elle peut �tre
 * appliqu�e.
 * 
 * @param <Data> le type des donn�es manipul�es par le VNS.
 * @param <Solution> le type des solution manipul�es par le VNS.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public abstract class VNS<Data, Solution> implements Heuristique<Data, Solution>
{
	/** La fonction objectif. */
	protected FonctionObjectif<Solution> f;
	/** La meilleure valeur de la fonction
	 *  objectif obtenue jusqu'� pr�sent. */
	protected int meilleureValObj;
	/** La meilleure solution obtenue
	 *  jusqu'� pr�sent. */
	protected Solution meilleureSolution;
	/** L'heuristique utilis�e pour le choix
	 *  de la solution initiale. */
	protected Heuristique<Data, Solution> heuristiqueSolInitiale;
	/**	Le num�ro de la structure de voisinage
	 *  � utiliser. */
	protected int k;
	/**	Le nombre de structures de voisinage. */
	protected int kMax;
	
	/**
	 * Cr�e une instance de l'heuristique de recherche � voisinage
	 * variable g�n�rique avec les param�tres de configuration fournis.
	 * 
	 * @param f la fonction objectif.
	 * @param heuristiqueSolInitiale l'heuristique � utiliser pour trouver
	 * 								 la solution initiale.
	 */
	public VNS(FonctionObjectif<Solution> f, Heuristique<Data, Solution> heuristiqueSolInitiale)
	{
		
	}
	
	/**
	 * Calcule une solution la plus optimale possible �
	 * partir des donn�es fournies en utilisant l'heuristique
	 * de recherche � voisinage variable.
	 * 
	 * @param donnees les donn�es auxquelles appliquer l'heuristique.
	 */
	public Solution calculerSolution(Data donnees)
	{
		return null;
	}
	
	/**
	 * Teste si la condition d'arr�t choisie de l'heuristique
	 * VNS est atteinte ou non.
	 * 
	 * @return vrai si et seulement si la condition d'arr�t
	 * 		   de l'heuristique est atteinte.
	 */
	protected abstract boolean estAtteinteConditionArret();
	
	/**
	 * Met � jour la condition d'arr�t choisie de l'heuristique
	 * VNS. Le param�tre permet d'indiquer s'il s'agit de la 
	 * premi�re mise � jour ou non.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected abstract void majConditionArret(boolean init);
	
	/**
	 * Retourne une solution appartenant � la structure courante
	 * de voisinage de la solution courante.
	 * 
	 * @return une solution du voisinage courant.
	 */
	protected abstract Solution choisirSolutionVoisinageCourant();
	
	/**
	 * Retourne la meilleure solution locale � partir
	 * de la solution initiale fournie.
	 * 
	 * @param s la solution initiale de la recherche locale.
	 * @return la meilleure solution locale trouv�e.
	 */
	protected abstract Solution rechercheLocale(Solution s);
}

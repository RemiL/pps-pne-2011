package pne;

/**
 * Classe abstraite implémentant l'heuristique de recherche à
 * voisinage variable (Variable Neighbourhood Search ou VNS)
 * de manière générique pour des types de données et de solution
 * quelconque. Cette classe doit ensuite être spécialisée par
 * héritage aux différents problèmes auxquels elle peut être
 * appliquée.
 * 
 * @param &lt;Data&gt; le type des données manipulées par le VNS.
 * @param &lt;Solution&gt; le type des solution manipulées par le VNS.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public abstract class VNS<Data, Solution> implements Heuristique<Data, Solution>
{
	/** La fonction objectif. */
	protected FonctionObjectif<Solution> f;
	/** L'instance du problème pour lequel on
	 * 	cherche actuellement une solution. */
	protected Data donnees;
	/** La meilleure valeur de la fonction
	 *  objectif obtenue jusqu'à présent. */
	protected int meilleureValObj;
	/** La meilleure solution obtenue
	 *  jusqu'à présent. */
	protected Solution meilleureSolution;
	/** L'heuristique utilisée pour le choix
	 *  de la solution initiale. */
	protected Heuristique<Data, Solution> heuristiqueSolInitiale;
	/**	Le numéro de la structure de voisinage
	 *  à utiliser. */
	protected int k;
	/**	Le nombre de structures de voisinage. */
	protected int kMax;
	
	/**
	 * Crée une instance de l'heuristique de recherche à voisinage
	 * variable générique avec les paramètres de configuration fournis.
	 * 
	 * @param f la fonction objectif.
	 * @param heuristiqueSolInitiale l'heuristique à utiliser pour trouver
	 * 								 la solution initiale.
	 */
	public VNS(FonctionObjectif<Solution> f, Heuristique<Data, Solution> heuristiqueSolInitiale)
	{
		this.f = f;
		this.heuristiqueSolInitiale = heuristiqueSolInitiale;
	}
	
	/**
	 * Calcule une solution la plus optimale possible à
	 * partir des données fournies en utilisant l'heuristique
	 * de recherche à voisinage variable.
	 * 
	 * @param donnees les données auxquelles appliquer l'heuristique.
	 */
	public Solution calculerSolution(Data donnees)
	{
		Solution sol;
		int valObj;
		
		this.donnees = donnees;
		
		initialiserKMax();
		
		// calcul de la solution initiale en fonction des donnees
		meilleureSolution = heuristiqueSolInitiale.calculerSolution(donnees);
		meilleureValObj = f.calculer(meilleureSolution);
		
		majConditionArret(true);
		
		do
		{
			k = 1;
			
			while (k < kMax)
			{
				// on génère une solution aléatoire dans le voisinage k-ième.
				sol = choisirSolutionVoisinageCourant();
				
				// on procède à une recherche locale à partir de cette
				// solution aléatoire pour obtenir un optimum local.
				sol = rechercheLocale(sol);
				
				valObj = f.calculer(sol);
				
				// mouvement
				if (f.estAmelioration(valObj, meilleureValObj))
				{
					meilleureSolution = sol;
					meilleureValObj = valObj;
					k = 1;
				}
				else 
					k++;
			}
			
			majConditionArret(false);
		} while (!estAtteinteConditionArret());
		
		return meilleureSolution;
	}
	
	/**
	 * Initialise kMax.
	 */
	protected abstract void initialiserKMax();
	
	/**
	 * Teste si la condition d'arrêt choisie de l'heuristique
	 * VNS est atteinte ou non.
	 * 
	 * @return vrai si et seulement si la condition d'arrêt
	 * 		   de l'heuristique est atteinte.
	 */
	protected abstract boolean estAtteinteConditionArret();
	
	/**
	 * Met à jour la condition d'arrêt choisie de l'heuristique
	 * VNS. Le paramètre permet d'indiquer s'il s'agit de la 
	 * première mise à jour ou non.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected abstract void majConditionArret(boolean init);
	
	/**
	 * Retourne une solution appartenant à la structure courante
	 * de voisinage de la solution courante.
	 * 
	 * @return une solution du voisinage courant.
	 */
	protected abstract Solution choisirSolutionVoisinageCourant();
	
	/**
	 * Retourne la meilleure solution locale à partir
	 * de la solution initiale fournie.
	 * 
	 * @param s la solution initiale de la recherche locale.
	 * @return la meilleure solution locale trouvée.
	 */
	protected abstract Solution rechercheLocale(Solution s);
}

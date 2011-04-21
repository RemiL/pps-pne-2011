package pne;

/**
 * Classe abstraite implémentant l'heuristique du recuit simulé
 * de manière générique pour des types de données et de solution
 * quelconque. Cette classe doit ensuite être spécialisée par
 * héritage aux différents problèmes auxquels elle peut être
 * appliquée.
 * 
 * @param &lt;Data&gt; le type des données manipulées par le recuit simulé.
 * @param &lt;Solution&gt; le type des solution manipulées par le recuit simulé.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public abstract class RecuitSimule<Data, Solution> implements Heuristique<Data, Solution>
{
	/** Une constante utilisée pour définir
	 *  l'utilisation de l'adaptation automatique
	 *  de la température initiale. */
	public static final double TEMP_INIT_AUTO = -1.0;
	
	/** La température courante du recuit. */
	protected double temp;
	/** La température initiale du recuit. */
	protected double tempInitiale;
	/** La fonction objectif. */
	protected FonctionObjectif<Solution> f;
	/** La meilleure valeur de la fonction
	 *  objectif obtenue jusqu'à présent. */
	protected int meilleureValObj;
	/** La meilleure solution obtenue
	 *  jusqu'à présent. */
	protected Solution meilleureSolution;
	/** Le voisinage choisi pour l'exploration. */
	protected Voisinage<Solution> voisinage;
	/** L'heuristique utilisée pour le choix
	 *  de la solution initiale. */
	protected Heuristique<Data, Solution> heuristiqueSolInitiale;
	protected Data donnees;
	
	/**
	 * Crée une instance de l'heuristique du recuit simulé générique
	 * avec les paramètres de configuration fournis.
	 * 
	 * @param tempInitiale la température initiale.
	 * @param f la fonction objectif.
	 * @param voisinage le voisinage à utiliser.
	 * @param heuristiqueSolInitiale l'heuristique à utiliser pour trouver
	 * 								 la solution initiale.
	 */
	protected RecuitSimule(double tempInitiale, FonctionObjectif<Solution> f, 
						   Voisinage<Solution> voisinage,
						   Heuristique<Data, Solution> heuristiqueSolInitiale)
	{
		this.tempInitiale = tempInitiale;
		this.f = f;
		this.voisinage = voisinage;
		this.heuristiqueSolInitiale = heuristiqueSolInitiale;
	}
	
	/**
	 * Calcule une solution la plus optimale possible à
	 * partir des données fournies en utilisant l'heuristique
	 * du recuit simulé.
	 * 
	 * @param donnees les données auxquelles appliquer l'heuristique.
	 */
	
	/*TODO il faut vérifier cette fonction...j'ai utilise des valeurs temporaires et je sais pas si c'est bon */
	public Solution calculerSolution(Data donnees)
	{
		this.donnees = donnees;
		//calcul de la solution initiale en fonction des donnees
		Solution sol = heuristiqueSolInitiale.calculerSolution(donnees);
		//initialisation de la température initiale
		tempInitiale = determinerTempInitiale();
		
		do{
			//recherche d'une solution voisine à la solution intiiale
			Solution solVoisin = voisinage.genererSolution(sol);
			int delta = f.calculer(solVoisin)- f.calculer(sol);
			//si il y a amélioration de la solution alors on accepte cette solution
			if(f.estAmelioration(f.calculer(solVoisin), f.calculer(sol)))
				sol = solVoisin;
			//si il y a dégradation alors on utilise le critere de metropolis pour voir si on accepte la solution
			else if (verifierAcceptationDegradation(delta))
			{
				sol = solVoisin;
			}
			//mise à jour de T en fonction du schema de refroidissement
			appliquerRefroidissement();
			
		}while(!estAtteinteConditionArret());
		
		return sol;
		
	}
	
	/**
	 * Teste si la condition d'arrêt choisie de l'heuristique
	 * du recuit simulé est atteinte ou non.
	 * 
	 * @return vrai si et seulement si la condition d'arrêt
	 * 		   de l'heuristique est atteinte.
	 */
	protected abstract boolean estAtteinteConditionArret();
	
	/**
	 * Met à jour la condition d'arrêt choisie de l'heuristique
	 * du recuit simulé. Le paramètre permet d'indiquer s'il
	 * s'agit de la première mise à jour ou non.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected abstract void majConditionArret(boolean init);
	
	/**
	 * Teste si la condition de changement de palier de 
	 * température est atteinte ou non.
	 * 
	 * @return vrai si et seulement si la condition de
	 * 		   changement de palier est atteinte.
	 */
	protected abstract boolean estAtteinteConditionChangementPalier();
	
	/**
	 * Met à jour la condition de changement de palier de 
	 * température du recuit simulé. Le paramètre permet 
	 * d'indiquer s'il s'agit de la première mise à jour
	 * ou non.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected abstract void majConditionChangementPalier(boolean init);
	
	/**
	 * Vérifie si une solution dégradant la valeur de la
	 * fonction objectif du delta fourni doit être quand
	 * même acceptée en utilisant le critère de Metropolis
	 * basé sur la distribution de Gibbs-Boltzmann.
	 * 
	 * @param delta le delta dont est dégradée la valeur objectif.
	 * @return vrai si et seulement si la solution est acceptée
	 *         par le critière de Metropolis.
	 */
	private boolean verifierAcceptationDegradation(int delta)
	{
		if(Math.exp(- delta / temp) <= Math.random())
		{
			return true;
		}
		else return false;
	}
	
	/**
	 * Permet de déterminer la température initiale à fixer
	 * pour obtenir un taux d'acceptation compris entre 80%
	 * et 85% lors de la première itération.
	 * 
	 * @return la température initiale à utiliser.
	 */
	/*TODO*/
	private double determinerTempInitiale()
	{
		return 0.0;
	}
	
	/**
	 * Applique le schéma de refroidissement.
	 */
	protected abstract void appliquerRefroidissement();
	
	/**
	 * Retourne la température initiale. Si la valeur
	 * retourné est TEMP_INIT_AUTO, alors l'algorithme
	 * de choix automatique de la température sera
	 * utilisé.
	 * 
	 * @return la température initiale ou TEMP_INIT_AUTO si
	 * 		   cette valeur est automatiquement déterminée.
	 */
	public double getTempInitiale()
	{
		return tempInitiale;
	}
	
	/**
	 * Fixe la température initiale à la valeur fournie.
	 * Si la valeur est TEMP_INIT_AUTO, alors l'algorithme
	 * de choix automatique de la température sera utilisé.
	 * 
	 * @param tempInitiale la température initiale.
	 */
	public void setTempInitiale(double tempInitiale)
	{
		this.tempInitiale = tempInitiale;
	}
	
	/**
	 * Retourne le voisinage actuellement utilisé
	 * pour l'exploration.
	 * 
	 * @return le voisinage actuellement utilisé.
	 */
	public Voisinage<Solution> getVoisinage()
	{
		return voisinage;
	}
	
	/**
	 * Fixe le voisinage à utiliser pour l'exploration
	 * à celui fourni.
	 * 
	 * @param voisinage le voisinage à utiliser.
	 */
	public void setVoisinage(Voisinage<Solution> voisinage)
	{
		this.voisinage = voisinage;
	}

	/**
	 * Retourne l'heuristique actuellement utilisée pour
	 * déterminer la solution initiale.
	 * 
	 * @return l'heuristique utilisée pour déterminer
	 *         la solution initiale.
	 */
	public Heuristique<Data, Solution> getHeuristiqueSolInitiale()
	{
		return heuristiqueSolInitiale;
	}

	/**
	 * Fixe l'heuristique actuellement utilisée pour
	 * déterminer la solution initiale à celle fournie.
	 * 
	 * @param heuristiqueSolInitiale l'heuristique utilisée
	 * 	      pour déterminer la solution initiale.
	 */
	public void setHeuristiqueSolInitiale(Heuristique<Data, Solution> heuristiqueSolInitiale)
	{
		this.heuristiqueSolInitiale = heuristiqueSolInitiale;
	}
	
	/**
	 * Retourne la fonction objectif que l'on souhaite
	 * optimisée.
	 * 
	 * @return la fonction objectif que l'on souhaite
	 * 		   optimisée
	 */
	public FonctionObjectif<Solution> getFonctionObjectif()
	{
		return f;
	}
}

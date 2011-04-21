package pne;

/**
 * Classe abstraite impl�mentant l'heuristique du recuit simul�
 * de mani�re g�n�rique pour des types de donn�es et de solution
 * quelconque. Cette classe doit ensuite �tre sp�cialis�e par
 * h�ritage aux diff�rents probl�mes auxquels elle peut �tre
 * appliqu�e.
 * 
 * @param &lt;Data&gt; le type des donn�es manipul�es par le recuit simul�.
 * @param &lt;Solution&gt; le type des solution manipul�es par le recuit simul�.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public abstract class RecuitSimule<Data, Solution> implements Heuristique<Data, Solution>
{
	/** Une constante utilis�e pour d�finir
	 *  l'utilisation de l'adaptation automatique
	 *  de la temp�rature initiale. */
	public static final double TEMP_INIT_AUTO = -1.0;
	
	/** La temp�rature courante du recuit. */
	protected double temp;
	/** La temp�rature initiale du recuit. */
	protected double tempInitiale;
	/** La fonction objectif. */
	protected FonctionObjectif<Solution> f;
	/** La meilleure valeur de la fonction
	 *  objectif obtenue jusqu'� pr�sent. */
	protected int meilleureValObj;
	/** La meilleure solution obtenue
	 *  jusqu'� pr�sent. */
	protected Solution meilleureSolution;
	/** Le voisinage choisi pour l'exploration. */
	protected Voisinage<Solution> voisinage;
	/** L'heuristique utilis�e pour le choix
	 *  de la solution initiale. */
	protected Heuristique<Data, Solution> heuristiqueSolInitiale;
	protected Data donnees;
	
	/**
	 * Cr�e une instance de l'heuristique du recuit simul� g�n�rique
	 * avec les param�tres de configuration fournis.
	 * 
	 * @param tempInitiale la temp�rature initiale.
	 * @param f la fonction objectif.
	 * @param voisinage le voisinage � utiliser.
	 * @param heuristiqueSolInitiale l'heuristique � utiliser pour trouver
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
	 * Calcule une solution la plus optimale possible �
	 * partir des donn�es fournies en utilisant l'heuristique
	 * du recuit simul�.
	 * 
	 * @param donnees les donn�es auxquelles appliquer l'heuristique.
	 */
	
	/*TODO il faut v�rifier cette fonction...j'ai utilise des valeurs temporaires et je sais pas si c'est bon */
	public Solution calculerSolution(Data donnees)
	{
		this.donnees = donnees;
		//calcul de la solution initiale en fonction des donnees
		Solution sol = heuristiqueSolInitiale.calculerSolution(donnees);
		//initialisation de la temp�rature initiale
		tempInitiale = determinerTempInitiale();
		
		do{
			//recherche d'une solution voisine � la solution intiiale
			Solution solVoisin = voisinage.genererSolution(sol);
			int delta = f.calculer(solVoisin)- f.calculer(sol);
			//si il y a am�lioration de la solution alors on accepte cette solution
			if(f.estAmelioration(f.calculer(solVoisin), f.calculer(sol)))
				sol = solVoisin;
			//si il y a d�gradation alors on utilise le critere de metropolis pour voir si on accepte la solution
			else if (verifierAcceptationDegradation(delta))
			{
				sol = solVoisin;
			}
			//mise � jour de T en fonction du schema de refroidissement
			appliquerRefroidissement();
			
		}while(!estAtteinteConditionArret());
		
		return sol;
		
	}
	
	/**
	 * Teste si la condition d'arr�t choisie de l'heuristique
	 * du recuit simul� est atteinte ou non.
	 * 
	 * @return vrai si et seulement si la condition d'arr�t
	 * 		   de l'heuristique est atteinte.
	 */
	protected abstract boolean estAtteinteConditionArret();
	
	/**
	 * Met � jour la condition d'arr�t choisie de l'heuristique
	 * du recuit simul�. Le param�tre permet d'indiquer s'il
	 * s'agit de la premi�re mise � jour ou non.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected abstract void majConditionArret(boolean init);
	
	/**
	 * Teste si la condition de changement de palier de 
	 * temp�rature est atteinte ou non.
	 * 
	 * @return vrai si et seulement si la condition de
	 * 		   changement de palier est atteinte.
	 */
	protected abstract boolean estAtteinteConditionChangementPalier();
	
	/**
	 * Met � jour la condition de changement de palier de 
	 * temp�rature du recuit simul�. Le param�tre permet 
	 * d'indiquer s'il s'agit de la premi�re mise � jour
	 * ou non.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected abstract void majConditionChangementPalier(boolean init);
	
	/**
	 * V�rifie si une solution d�gradant la valeur de la
	 * fonction objectif du delta fourni doit �tre quand
	 * m�me accept�e en utilisant le crit�re de Metropolis
	 * bas� sur la distribution de Gibbs-Boltzmann.
	 * 
	 * @param delta le delta dont est d�grad�e la valeur objectif.
	 * @return vrai si et seulement si la solution est accept�e
	 *         par le criti�re de Metropolis.
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
	 * Permet de d�terminer la temp�rature initiale � fixer
	 * pour obtenir un taux d'acceptation compris entre 80%
	 * et 85% lors de la premi�re it�ration.
	 * 
	 * @return la temp�rature initiale � utiliser.
	 */
	/*TODO*/
	private double determinerTempInitiale()
	{
		return 0.0;
	}
	
	/**
	 * Applique le sch�ma de refroidissement.
	 */
	protected abstract void appliquerRefroidissement();
	
	/**
	 * Retourne la temp�rature initiale. Si la valeur
	 * retourn� est TEMP_INIT_AUTO, alors l'algorithme
	 * de choix automatique de la temp�rature sera
	 * utilis�.
	 * 
	 * @return la temp�rature initiale ou TEMP_INIT_AUTO si
	 * 		   cette valeur est automatiquement d�termin�e.
	 */
	public double getTempInitiale()
	{
		return tempInitiale;
	}
	
	/**
	 * Fixe la temp�rature initiale � la valeur fournie.
	 * Si la valeur est TEMP_INIT_AUTO, alors l'algorithme
	 * de choix automatique de la temp�rature sera utilis�.
	 * 
	 * @param tempInitiale la temp�rature initiale.
	 */
	public void setTempInitiale(double tempInitiale)
	{
		this.tempInitiale = tempInitiale;
	}
	
	/**
	 * Retourne le voisinage actuellement utilis�
	 * pour l'exploration.
	 * 
	 * @return le voisinage actuellement utilis�.
	 */
	public Voisinage<Solution> getVoisinage()
	{
		return voisinage;
	}
	
	/**
	 * Fixe le voisinage � utiliser pour l'exploration
	 * � celui fourni.
	 * 
	 * @param voisinage le voisinage � utiliser.
	 */
	public void setVoisinage(Voisinage<Solution> voisinage)
	{
		this.voisinage = voisinage;
	}

	/**
	 * Retourne l'heuristique actuellement utilis�e pour
	 * d�terminer la solution initiale.
	 * 
	 * @return l'heuristique utilis�e pour d�terminer
	 *         la solution initiale.
	 */
	public Heuristique<Data, Solution> getHeuristiqueSolInitiale()
	{
		return heuristiqueSolInitiale;
	}

	/**
	 * Fixe l'heuristique actuellement utilis�e pour
	 * d�terminer la solution initiale � celle fournie.
	 * 
	 * @param heuristiqueSolInitiale l'heuristique utilis�e
	 * 	      pour d�terminer la solution initiale.
	 */
	public void setHeuristiqueSolInitiale(Heuristique<Data, Solution> heuristiqueSolInitiale)
	{
		this.heuristiqueSolInitiale = heuristiqueSolInitiale;
	}
	
	/**
	 * Retourne la fonction objectif que l'on souhaite
	 * optimis�e.
	 * 
	 * @return la fonction objectif que l'on souhaite
	 * 		   optimis�e
	 */
	public FonctionObjectif<Solution> getFonctionObjectif()
	{
		return f;
	}
}

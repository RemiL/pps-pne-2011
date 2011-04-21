package pmediane;

import pne.Heuristique;
import pne.VNS;
import pne.VoisinageTailleVariable;

/**
 * Classe spécialisant l'heuristique de recherche à voisinage
 * variable pour traiter le problème de la p-médiane.
 * Ce problème vise à choisir P centres parmi N entités tels que 
 * la somme des distances des entités à leur centre le plus proche
 * soit minimale.
 * Cette spécialisation de l'algorithme utilise une structure
 * de voisinages basée sur un voisinage à taille multiple.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class VNSPMediane extends VNS<DataPMediane, SolutionPMediane>
{
	/** Le nombre d'itérations successives sans amélioration
	 *  de la valeur de la fonction objectif. */
	private int nbIterationsSansAmelioration;
	/** Le nombre maximum d'itérations successives sans 
	 *  amélioration de la valeur de la fonction objectif
	 *  avant arrêt de l'algorithme. */
	private int nbMaxIterationsSansAmelioration;
	/** Le voisinage à taille variable utilisé. */
	private VoisinageTailleVariable<SolutionPMediane> voisinage;
	/** La valeur de la fonction objectif au début de
	 *  l'itération. */
	private int valObjSauvegardee;
	
	/**
	 * Crée une instance de l'heuristique VNS spécialisée pour
	 * traiter le problème de la p-médiane avec les paramètres
	 * fournis.
	 * 
	 * @param nbMaxIterationsSansAmelioration la condition d'arrêt. 										
	 * @param voisinage le voisinage à taille variable devant être
	 * 					utilisé pour implémenter la structure de 
	 * 					voisinages.
	 * @param heuristiqueSolInitiale l'heuristique à utiliser pour trouver
	 * 								 la solution initiale
	 */
	public VNSPMediane(int nbMaxIterationsSansAmelioration,
					   VoisinageTailleVariable<SolutionPMediane> voisinage,
					   Heuristique<DataPMediane, SolutionPMediane> heuristiqueSolInitiale)
	{
		super(new FonctionObjectifPMediane(), heuristiqueSolInitiale);
		this.nbMaxIterationsSansAmelioration = nbMaxIterationsSansAmelioration;
		this.voisinage = voisinage;
	}
	
	/**
	 * Crée une instance de l'heuristique VNS spécialisée pour
	 * traiter le problème de la p-médiane.
	 * Les paramètres utilisés pour le VNS sont ceux par défaut,
	 *  à savoir :
	 *  - une condition d'arrêt fixée à 25 itérations sans amélioration
	 *    de la valeur de la fonction objectif.
	 *  - une structure de voisinages obtenue grâce à un voisinage à taille
	 *    variable basé sur la fermeture de k des centres et la promotion 
	 *    de k des entités en centres.
	 *  - une heuristique gloutonne pour la construction de la solution
	 *    initiale.
	 */
	public VNSPMediane()
	{
		super(new FonctionObjectifPMediane(), new HeuristiqueGloutonnePMediane());
		this.nbMaxIterationsSansAmelioration = 25;
		this.voisinage = new VoisinagePMediane();
	}

	/**
	 * Initialise kMax à au nombre de centres à ouvrir
	 * puisqu'on peut au maximum procéder aux changements
	 * de tous les centres.
	 */
	protected void initialiserKMax()
	{
		kMax = donnees.getNbCentres();
	}
	
	/**
	 * Teste si la condition d'arrêt de l'heuristique est atteinte,
	 * c-'est-à-dire si le nombre d'itérations sans amélioration de
	 * la valeur de la fonction objectif à dépasser la limite autorisée.
	 * 
	 * @return vrai si et seulement si le nombre maximal d'itérations
	 * 		   sans amélioration est dépassé.
	 */
	protected boolean estAtteinteConditionArret()
	{
		return (nbIterationsSansAmelioration == nbMaxIterationsSansAmelioration);
	}
	
	/**
	 * Met à jour la condition d'arrêt en incrémentant si
	 * nécessaire le nombre d'itérations sans amélioration.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected void majConditionArret(boolean init)
	{
		if (init || valObjSauvegardee < meilleureValObj)
			nbIterationsSansAmelioration = 0;
		else
			nbIterationsSansAmelioration++;
		
		valObjSauvegardee = meilleureValObj;
	}
	
	/**
	 * Retourne une solution appartenant au voisinage k courant
	 * de la solution actuelle.
	 * 
	 * @return une solution appartenant au voisinage k de la
	 * 		   solution courante.
	 */
	protected SolutionPMediane choisirSolutionVoisinageCourant()
	{
		return voisinage.genererSolution(meilleureSolution, k);
	}

	/**
	 * Retourne la meilleure solution locale trouvée à partir
	 * de la solution fournie. Dans notre implémentation cela
	 * correspond à une recherche exhaustive dans le voisinage
	 * de taille 1 de la solution fournie.
	 * 
	 * @return une meilleure solution locale trouvée pour la
	 * 		   solution fournie.
	 */
	protected SolutionPMediane rechercheLocale(SolutionPMediane s)
	{
		return voisinage.rechercherMeilleureSolution(s, k, f);
	}
}

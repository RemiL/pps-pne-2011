package pmediane;

import pne.Heuristique;
import pne.VNS;
import pne.VoisinageTailleVariable;

/**
 * Classe sp�cialisant l'heuristique de recherche � voisinage
 * variable pour traiter le probl�me de la p-m�diane.
 * Ce probl�me vise � choisir P centres parmi N entit�s tels que 
 * la somme des distances des entit�s � leur centre le plus proche
 * soit minimale.
 * Cette sp�cialisation de l'algorithme utilise une structure
 * de voisinages bas�e sur un voisinage � taille multiple.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class VNSPMediane extends VNS<DataPMediane, SolutionPMediane>
{
	/** Le nombre d'it�rations successives sans am�lioration
	 *  de la valeur de la fonction objectif. */
	private int nbIterationsSansAmelioration;
	/** Le nombre maximum d'it�rations successives sans 
	 *  am�lioration de la valeur de la fonction objectif
	 *  avant arr�t de l'algorithme. */
	private int nbMaxIterationsSansAmelioration;
	/** Le voisinage � taille variable utilis�. */
	private VoisinageTailleVariable<SolutionPMediane> voisinage;
	/** La valeur de la fonction objectif au d�but de
	 *  l'it�ration. */
	private int valObjSauvegardee;
	
	/**
	 * Cr�e une instance de l'heuristique VNS sp�cialis�e pour
	 * traiter le probl�me de la p-m�diane avec les param�tres
	 * fournis.
	 * 
	 * @param nbMaxIterationsSansAmelioration la condition d'arr�t. 										
	 * @param voisinage le voisinage � taille variable devant �tre
	 * 					utilis� pour impl�menter la structure de 
	 * 					voisinages.
	 * @param heuristiqueSolInitiale l'heuristique � utiliser pour trouver
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
	 * Cr�e une instance de l'heuristique VNS sp�cialis�e pour
	 * traiter le probl�me de la p-m�diane.
	 * Les param�tres utilis�s pour le VNS sont ceux par d�faut,
	 *  � savoir :
	 *  - une condition d'arr�t fix�e � 25 it�rations sans am�lioration
	 *    de la valeur de la fonction objectif.
	 *  - une structure de voisinages obtenue gr�ce � un voisinage � taille
	 *    variable bas� sur la fermeture de k des centres et la promotion 
	 *    de k des entit�s en centres.
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
	 * Initialise kMax � au nombre de centres � ouvrir
	 * puisqu'on peut au maximum proc�der aux changements
	 * de tous les centres.
	 */
	protected void initialiserKMax()
	{
		kMax = donnees.getNbCentres();
	}
	
	/**
	 * Teste si la condition d'arr�t de l'heuristique est atteinte,
	 * c-'est-�-dire si le nombre d'it�rations sans am�lioration de
	 * la valeur de la fonction objectif � d�passer la limite autoris�e.
	 * 
	 * @return vrai si et seulement si le nombre maximal d'it�rations
	 * 		   sans am�lioration est d�pass�.
	 */
	protected boolean estAtteinteConditionArret()
	{
		return (nbIterationsSansAmelioration == nbMaxIterationsSansAmelioration);
	}
	
	/**
	 * Met � jour la condition d'arr�t en incr�mentant si
	 * n�cessaire le nombre d'it�rations sans am�lioration.
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
	 * Retourne la meilleure solution locale trouv�e � partir
	 * de la solution fournie. Dans notre impl�mentation cela
	 * correspond � une recherche exhaustive dans le voisinage
	 * de taille 1 de la solution fournie.
	 * 
	 * @return une meilleure solution locale trouv�e pour la
	 * 		   solution fournie.
	 */
	protected SolutionPMediane rechercheLocale(SolutionPMediane s)
	{
		return voisinage.rechercherMeilleureSolution(s, k, f);
	}
}

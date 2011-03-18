package pmediane;

import pne.FonctionObjectif;
import pne.Heuristique;
import pne.RecuitSimule;
import pne.Voisinage;

/**
 * Classe sp�cialisant l'heuristique du recuit simul� pour traiter
 * le probl�me de la p-m�diane.
 * Ce probl�me vise � choisir P centres parmi N entit�s tels que 
 * la somme des distances des entit�s � leur centre le plus proche
 * soit minimale.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class RSPMediane extends RecuitSimule<DataPMediane, SolutionPMediane>
{
	/** La temp�rature � laquelle on consid�re
	 *  le syst�me comme gel� et on arr�te la
	 *  recherche. */
	private double tempArret;
	/** Le nombre d'it�rations pour le palier courant. */
	private int nbIterationsPalier;
	/** Le nombre maximum d'it�rations par palier. */
	private int nbIterationsMaxPalier;
	/** Le taux de d�croissance de la temp�rature � chaque
	 *  palier utilis� par le sch�ma de refroidissement. */
	private double tauxDecroissanceTemp;
	
	/**
	 * Cr�e une instance de l'heuristique du recuit simul� sp�cialis�
	 * pour traiter le probl�me de la p-m�diane avec les param�tres
	 * fournis.
	 * 
	 * @param tempInitiale la temp�rature initiale.
	 * @param tempArret la temp�rature d'arr�t de la recherche.
	 * @param nbIterationsPalier le nombre d'it�rations par palier.
	 * @param tauxDecroissanteTemp le taux de d�croissance de la temp�rature
	 * 		 					   � chaque palier.
	 * @param voisinage le voisinage � utiliser.
	 * @param heuristiqueSolInitiale l'heuristique � utiliser pour trouver
	 * 								 la solution initiale.
	 */
	public RSPMediane(double tempInitiale, double tempArret, int nbIterationsPalier, double tauxDecroissanteTemp,
			   		  Voisinage<SolutionPMediane> voisinage, Heuristique<DataPMediane, SolutionPMediane> heuristiqueSolInitiale)
	{
		super(tempInitiale, new FonctionObjectifPMediane(), voisinage, heuristiqueSolInitiale);
	}
	
	/**
	 * Cr�e une instance de l'heuristique du recuit simul� sp�cialis�
	 * pour traiter le probl�me de la p-m�diane.
	 * Les param�tres utilis�s pour le recuit sont ceux par d�faut, �
	 * savoir :
	 *  - un ajustement automatique de la temp�rature
	 *  - une temp�rature d'arr�t de 0.001
	 *  - un nombre d'it�rations par paliers �gal au carr� du nombre
	 *    d'entit�s dans le probl�me trait�
	 *  - un taux de d�croissance de la temp�rature de 0.90
	 *  - un voisinage obtenu par fermeture d'un des centres et
	 *    promotion d'une des entit�s en un centre
	 *  - une heuristique gloutonne pour la construction de la solution
	 *    initiale.
	 */
	public RSPMediane()
	{
		
	}
	
	/**
	 * Teste si la condition d'arr�t est atteinte,
	 * c'est-�-dire si la temp�rature d'arr�t o�
	 * le syst�me est consid�r� comme gel� est
	 * atteinte.
	 * 
	 * @return vrai si et seulement si la temp�rature
	 * 		   d'arr�t est atteinte.
	 */
	protected boolean estAtteinteConditionArret()
	{
		return false;
	}

	/**
	 * Met � jour la condition d'arr�t, dans notre
	 * impl�mentation ne fait rien.
	 */
	protected void majConditionArret(boolean init)
	{
		// On ne fait rien ! La mise � jour est faite
		// par l'application du sch�ma de refroidissement.
	}
	
	/**
	 * Teste si la condition de changement de palier est atteinte,
	 * c'est-�-dire si le nombre d'it�rations pour le palier courant
	 * � atteint la limite fix�e.
	 * 
	 * @return vrai si et seulement si la condition de
	 * 		   changement de palier est atteinte.
	 */
	protected boolean estAtteinteConditionChangementPalier()
	{
		return false;
	}

	/**
	 * Met � jour la condition de changement de palier en incr�mentant
	 * le nombre d'it�rations effectu�es.
	 * 
	 * @param init indique s'il s'agit de l'initialisation
	 * 			   de la condition.
	 */
	protected void majConditionChangementPalier(boolean init)
	{
		
	}
	
	/**
	 * Applique le sch�ma de refroidissement suivant :
	 * la temp�rature d�croit selon la suite g�om�trique
	 * T(n+1) = tauxDecroissance * T(n).
	 */
	protected void appliquerRefroidissement()
	{
		
	}
}

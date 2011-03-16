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
		super(tempInitiale, tempArret, nbIterationsPalier,
			  new FonctionObjectifPMediane(), voisinage, heuristiqueSolInitiale);
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
	 * Applique le sch�ma de refroidissement suivant :
	 * la temp�rature d�croit selon la suite g�om�trique
	 * T(n+1) = tauxDecroissance * T(n).
	 */
	protected void appliquerRefroidissement()
	{
		
	}
}

package pmediane;

import pne.FonctionObjectif;
import pne.Heuristique;
import pne.RecuitSimule;
import pne.Voisinage;

/**
 * Classe spécialisant l'heuristique du recuit simulé pour traiter
 * le problème de la p-médiane.
 * Ce problème vise à choisir P centres parmi N entités tels que 
 * la somme des distances des entités à leur centre le plus proche
 * soit minimale.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class RSPMediane extends RecuitSimule<DataPMediane, SolutionPMediane>
{
	/** Le taux de décroissance de la température à chaque
	 *  palier utilisé par le schéma de refroidissement. */
	private double tauxDecroissanceTemp;
	
	/**
	 * Crée une instance de l'heuristique du recuit simulé spécialisé
	 * pour traiter le problème de la p-médiane avec les paramètres
	 * fournis.
	 * 
	 * @param tempInitiale la température initiale.
	 * @param tempArret la température d'arrêt de la recherche.
	 * @param nbIterationsPalier le nombre d'itérations par palier.
	 * @param tauxDecroissanteTemp le taux de décroissance de la température
	 * 		 					   à chaque palier.
	 * @param voisinage le voisinage à utiliser.
	 * @param heuristiqueSolInitiale l'heuristique à utiliser pour trouver
	 * 								 la solution initiale.
	 */
	public RSPMediane(double tempInitiale, double tempArret, int nbIterationsPalier, double tauxDecroissanteTemp,
			   		  Voisinage<SolutionPMediane> voisinage, Heuristique<DataPMediane, SolutionPMediane> heuristiqueSolInitiale)
	{
		super(tempInitiale, tempArret, nbIterationsPalier,
			  new FonctionObjectifPMediane(), voisinage, heuristiqueSolInitiale);
	}
	
	/**
	 * Crée une instance de l'heuristique du recuit simulé spécialisé
	 * pour traiter le problème de la p-médiane.
	 * Les paramètres utilisés pour le recuit sont ceux par défaut, à
	 * savoir :
	 *  - un ajustement automatique de la température
	 *  - une température d'arrêt de 0.001
	 *  - un nombre d'itérations par paliers égal au carré du nombre
	 *    d'entités dans le problème traité
	 *  - un taux de décroissance de la température de 0.90
	 *  - un voisinage obtenu par fermeture d'un des centres et
	 *    promotion d'une des entités en un centre
	 *  - une heuristique gloutonne pour la construction de la solution
	 *    initiale.
	 */
	public RSPMediane()
	{
		
	}
	
	/**
	 * Applique le schéma de refroidissement suivant :
	 * la température décroit selon la suite géométrique
	 * T(n+1) = tauxDecroissance * T(n).
	 */
	protected void appliquerRefroidissement()
	{
		
	}
}

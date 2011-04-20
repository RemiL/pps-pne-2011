package pmediane;

import java.util.Random;

import pne.Heuristique;

/**
 * Classe impl�mentant l'interface Heuristique proposant
 * une heuristique gloutonne pour le probl�me de la p-m�diane
 * reposant sur le principe suivant : commencer par ouvrir le
 * centre qui minimise la distance aux entit�s puis ouvrir le
 * deuxi�me centre qui fait que la fonction objectif est 
 * minimale et ainsi de suite jusqu�� avoir ouvert p centres.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class HeuristiqueGloutonnePMediane implements Heuristique<DataPMediane, SolutionPMediane>
{
	/**
	 * Calcule la solution la plus optimale possible en utilisant
	 * l'heuristique gloutonne � partir des donn�es fournies.
	 * 
	 * @param donnees les donn�es sur lesquelles l'heuristique
	 * 				 doit �tre appliqu�e.
	 * @return la solution la plus optimale possible construite
	 * 		   avec l'heuristique.
	 */
	public SolutionPMediane calculerSolution(DataPMediane donnees)
	{
		int c, centre, meilleurCentre = 0, i;
		int val, valTmp;
		
		int[] dispo = new int[donnees.getNbEntites()];
		int[] centres = new int[donnees.getNbCentres()];
		int[] affectations = null;
		int[] affectationsPrec = new int[donnees.getNbEntites()];
		int[] affectationsTmp = new int[donnees.getNbEntites()];
		int[] affectationsSecondaires = null;
		int[] affectationsSecondairesPrec = new int[donnees.getNbEntites()];
		int[] affectationsSecondairesTmp = new int[donnees.getNbEntites()];
		
		for (i=0; i<dispo.length; i++)
			dispo[i] = i;
		
		// On choisit pour commencer le centre qui minimise
		// la somme des distances � toutes les entit�s.
		val = Integer.MAX_VALUE;
		for (centre=0; centre<dispo.length; centre++)
		{
			valTmp = 0;
			
			for (i=0; i<affectationsTmp.length; i++)
				valTmp += donnees.getDistance(i, centre);

			if (valTmp < val)
			{
				meilleurCentre = centre;
				val = valTmp;
			}
		}
		
		for (i=0; i<affectationsTmp.length; i++)
		{
			affectationsPrec[i] = meilleurCentre;
			affectationsSecondairesPrec[i] = -1;
		}
		dispo[meilleurCentre] = dispo[dispo.length - 1];
		
		// On ouvre ensuite les autres centres
		for (int nbCentres=1; nbCentres<centres.length; nbCentres++)
		{
			val = Integer.MAX_VALUE;
			
			for (c=0; c<dispo.length-nbCentres; c++)
			{
				centre = dispo[c];
				valTmp = 0;
				
				for (i=0; i<affectationsTmp.length; i++)
				{
					if (donnees.getDistance(i, centre) < donnees.getDistance(i, affectationsPrec[i]))
					{
						affectationsSecondairesTmp[i] = affectationsTmp[i];
						affectationsTmp[i] = centre;
					}
					else
					{
						affectationsTmp[i] = affectationsPrec[i];
						
						if (affectationsSecondairesPrec[i] == -1
							|| donnees.getDistance(i, centre) < donnees.getDistance(i, affectationsSecondairesPrec[i]))
							affectationsSecondairesTmp[i] = centre;
					}
					
					valTmp += donnees.getDistance(i, affectationsTmp[i]);
				}
				
				if (valTmp < val)
				{
					meilleurCentre = centre;
					val = valTmp;
					affectations = affectationsTmp.clone();
					affectationsSecondaires = affectationsSecondairesTmp.clone();
				}
			}
			
			dispo[meilleurCentre] = dispo[dispo.length - nbCentres - 1];
			affectationsPrec = affectations.clone();
			affectationsSecondairesPrec = affectationsSecondaires.clone();
		}
		
		return new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
	}
}

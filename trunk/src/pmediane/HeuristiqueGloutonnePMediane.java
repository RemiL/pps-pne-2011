package pmediane;

import pne.Heuristique;

/**
 * Classe implémentant l'interface Heuristique proposant
 * une heuristique gloutonne pour le problème de la p-médiane
 * reposant sur le principe suivant : commencer par ouvrir le
 * centre qui minimise la distance aux entités puis ouvrir le
 * deuxième centre qui fait que la fonction objectif est 
 * minimale et ainsi de suite jusqu’à avoir ouvert p centres.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class HeuristiqueGloutonnePMediane implements Heuristique<DataPMediane, SolutionPMediane>
{
	/**
	 * Calcule la solution la plus optimale possible en utilisant
	 * l'heuristique gloutonne à partir des données fournies.
	 * 
	 * @param donnees les données sur lesquelles l'heuristique
	 * 				 doit être appliquée.
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
		
		// Les entités sont toutes disponibles pour
		// devenir centres au départ.
		for (i=0; i<dispo.length; i++)
			dispo[i] = i;
		
		// On choisit pour commencer le centre qui minimise
		// la somme des distances à toutes les entités.
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
		// On affecte toutes les entités à ce centre.
		for (i=0; i<affectationsTmp.length; i++)
		{
			affectationsPrec[i] = meilleurCentre;
			affectationsSecondairesPrec[i] = -1;
		}
		dispo[meilleurCentre] = dispo[dispo.length - 1];
		centres[0] = meilleurCentre;
		
		// On ouvre ensuite les autres centres en prenant celui
		// qui permet de minimiser la fonction objectif à ce moment.
		for (int nbCentres=1; nbCentres<centres.length; nbCentres++)
		{
			val = Integer.MAX_VALUE;
			
			// On transforme successivement toutes les
			// entités disponibles en centre.
			for (c=0; c<dispo.length-nbCentres; c++)
			{
				centre = dispo[c];
				valTmp = 0;
				
				// On refait les affectations en tenant compte de ce centre.
				for (i=0; i<affectationsTmp.length; i++)
				{
					// Si le centre est plus proche que celui anciennement affecté,
					// il le remplace et l'ancien devient le deuxième meilleur centre.
					if (donnees.getDistance(i, centre) < donnees.getDistance(i, affectationsPrec[i]))
					{
						affectationsSecondairesTmp[i] = affectationsPrec[i];
						affectationsTmp[i] = centre;
					}
					else
					{
						affectationsTmp[i] = affectationsPrec[i];
						
						// Le nouveau centre peut devenir le deuxième meilleur centre.
						if (affectationsSecondairesPrec[i] == -1
							|| donnees.getDistance(i, centre) < donnees.getDistance(i, affectationsSecondairesPrec[i]))
							affectationsSecondairesTmp[i] = centre;
						else
							affectationsSecondairesTmp[i] = affectationsSecondairesPrec[i];
					}
					
					valTmp += donnees.getDistance(i, affectationsTmp[i]);
				}
				
				// Si on améliore la valeur de la fonction objectif
				if (valTmp < val)
				{ // on sélectionne ce centre pour l'instant.
					meilleurCentre = centre;
					val = valTmp;
					affectations = affectationsTmp.clone();
					affectationsSecondaires = affectationsSecondairesTmp.clone();
				}
			}
			
			// On ouvre le meilleur centre trouvé
			dispo[meilleurCentre] = dispo[dispo.length - nbCentres - 1];
			centres[nbCentres] = meilleurCentre;
			// On sauvegarde la solution actuelle
			affectationsPrec = affectations.clone();
			affectationsSecondairesPrec = affectationsSecondaires.clone();
		}
		
		return new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
	}
}

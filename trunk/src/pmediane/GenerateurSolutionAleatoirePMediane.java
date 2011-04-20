package pmediane;

import java.util.Random;

import pne.Heuristique;

/**
 * Classe implémentant l'interface Heuristique générant des
 * solutions aléatoires au problème de la p-médiane.
 * Il ne s'agit pas à proprement parler d'une heuristique
 * puisqu'on n'essaie pas d'optimiser la solution néanmoins
 * il est plus simple de manipuler ce générateur de solution
 * aléatoire à travers la même interface puisqu'elle est adaptée.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class GenerateurSolutionAleatoirePMediane implements Heuristique<DataPMediane, SolutionPMediane>
{
	/**
	 * Génère une solution aléatoire au problème de la p-médiane
	 * correspondant aux données fournies.
	 * 
	 * @param donnees les données pour lesquelles on veut générer
	 * 				  la solution.
	 * @return une solution aléatoire au problème.
	 */
	public SolutionPMediane calculerSolution(DataPMediane donnees)
	{
		Random rand = new Random();
		int i, j;
		
		int[] alea = new int[donnees.getNbEntites()];
		int[] centres = new int[donnees.getNbCentres()];
		int[] affectations = new int[donnees.getNbEntites()];
		int[] affectationsSecondaires = new int[donnees.getNbEntites()];
		
		for (i=0; i<alea.length; i++)
			alea[i] = i;
		
		for (i=0; i<centres.length; i++)
		{
			j = rand.nextInt(alea.length - i);
			centres[i] = alea[j];
			alea[j] = alea[alea.length - i - 1];
		}
		
		for (i=0; i<affectations.length; i++)
		{
			affectations[i] = centres[0];
			affectationsSecondaires[i] = -1;
			
			for (j=1; j<centres.length; j++)
			{
				if (donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectations[i]))
				{
					affectationsSecondaires[i] = affectations[i];
					affectations[i] = centres[j];
				}
				else if (affectationsSecondaires[i] == -1
						 || donnees.getDistance(i, centres[j]) < donnees.getDistance(i, affectationsSecondaires[i]))
					affectationsSecondaires[i] = centres[j];
			}
		}
		
		return new SolutionPMediane(donnees, centres, affectations, affectationsSecondaires);
	}
}

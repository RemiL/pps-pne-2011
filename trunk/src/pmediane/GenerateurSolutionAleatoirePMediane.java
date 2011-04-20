package pmediane;

import java.util.Random;

import pne.Heuristique;

/**
 * Classe impl�mentant l'interface Heuristique g�n�rant des
 * solutions al�atoires au probl�me de la p-m�diane.
 * Il ne s'agit pas � proprement parler d'une heuristique
 * puisqu'on n'essaie pas d'optimiser la solution n�anmoins
 * il est plus simple de manipuler ce g�n�rateur de solution
 * al�atoire � travers la m�me interface puisqu'elle est adapt�e.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class GenerateurSolutionAleatoirePMediane implements Heuristique<DataPMediane, SolutionPMediane>
{
	/**
	 * G�n�re une solution al�atoire au probl�me de la p-m�diane
	 * correspondant aux donn�es fournies.
	 * 
	 * @param donnees les donn�es pour lesquelles on veut g�n�rer
	 * 				  la solution.
	 * @return une solution al�atoire au probl�me.
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

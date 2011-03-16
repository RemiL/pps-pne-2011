package pmediane;

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
		return null;
	}
}

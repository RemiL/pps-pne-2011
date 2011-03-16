package pmediane;

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
		return null;
	}
}

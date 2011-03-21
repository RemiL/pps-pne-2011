package pmediane;

import pne.Heuristique;

/**
 * Classe implémentant un solveur du PLNE de la p-médiane
 * basé sur CPLEX.
 * 
 * @author Bruno Duisit, Rémi Lacroix, Marie Nivet
 */
public class CPLEXPMediane implements Heuristique<PLNEPMediane, SolutionPMediane>
{
	/**
	 * Crée un solveur pour le PLNE de la p-médiane
	 * basé sur CPLEX.
	 */
	public CPLEXPMediane()
	{
		
	}
	
	/**
	 * Retourne la solution au PLNE de la p-médiane fourni
	 * calculé par le solveur CPLEX. Si la résolution échoue
	 * cette méthode retournera null.
	 * 
	 * @param donnees l'instance du problème à résoudre.
	 * @return la solution au PLNE calculée par CPLEX
	 * 		   ou null en cas d'échec.
	 */
	public SolutionPMediane calculerSolution(PLNEPMediane donnees)
	{
		return null;
	}
}

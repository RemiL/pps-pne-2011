package pmediane;

import pne.Heuristique;

/**
 * Classe impl�mentant un solveur du PLNE de la p-m�diane
 * bas� sur CPLEX.
 * 
 * @author Bruno Duisit, R�mi Lacroix, Marie Nivet
 */
public class CPLEXPMediane implements Heuristique<PLNEPMediane, SolutionPMediane>
{
	/**
	 * Cr�e un solveur pour le PLNE de la p-m�diane
	 * bas� sur CPLEX.
	 */
	public CPLEXPMediane()
	{
		
	}
	
	/**
	 * Retourne la solution au PLNE de la p-m�diane fourni
	 * calcul� par le solveur CPLEX. Si la r�solution �choue
	 * cette m�thode retournera null.
	 * 
	 * @param donnees l'instance du probl�me � r�soudre.
	 * @return la solution au PLNE calcul�e par CPLEX
	 * 		   ou null en cas d'�chec.
	 */
	public SolutionPMediane calculerSolution(PLNEPMediane donnees)
	{
		return null;
	}
}

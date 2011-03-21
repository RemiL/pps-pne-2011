package pmediane;

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
		return null;
	}
}
